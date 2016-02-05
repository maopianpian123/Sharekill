#######################################################
#######################################################
#######################################################
#############      CREATE TRIGGER     #################
#######################################################
#######################################################
#######################################################

delimiter $$

#超级管理员只允许建立一个,而且在设置以后不允许修改和删除

create trigger manager_before_insert_trigger 
    before insert on Manager
    for each row 
    begin 
        declare count_super_manager int;
        set count_super_manager = (select count(*) from Manager where authority = 1);
        if(new.authority = 1 && count_super_manager >= 1) then
            insert into table_not_exist values('');      
        end if;
    end;
$$


#不允许删除超级管理员

create trigger manager_before_delete_trigger 
    before delete on Manager
    for each row 
    begin 
        declare count_super_manager int;
        set count_super_manager = (select count(*) from Manager where authority = 1);
        if(old.authority = 1 ) then
            insert into table_not_exist values('');
        end if;
    end;
$$


#不允许修改超级管理员

create trigger manager_before_update_trigger 
    before update on Manager
    for each row 
    begin 
        declare count_super_manager int;
        set count_super_manager = (select count(*) from Manager where authority = 1);
        if(old.authority = 1) then
            insert into table_not_exist values('');
        end if;
    end;
$$


#添加评论会重新计算一个技能的评分,也会重新计算一个人的评分

create trigger comment_after_insert_trigger 
    after insert on Comment
    for each row
    begin
        #声明局部变量
        declare avg_skill decimal(3,2);
        declare avg_user decimal(3,2);
        declare user_id bigint;

	#获取用户的ID
	select cellphone into user_id from User,Skill where Skill.publisher = User.cellphone and Skill.skillId = new.skillId;
	insert into Temp values ('user_id',user_id);
        
        #更新技能的评论次数
        update Skill set Skill.commentTimes = Skill.commentTimes+1 where Skill.skillId=new.skillId; 
        
        #计算并设置技能的平均分
        SELECT AVG(score) into avg_skill FROM Comment WHERE Comment.skillId = new.skillId;
	insert into Temp values ('avg_skill',avg_skill);
        if(avg_skill >= 0 && avg_skill <= 5.0) then
            update Skill set score = avg_skill where Skill.skillId = new.skillId;
        end if;
        
        #计算并设置用户的平均分
        SELECT AVG(score) into avg_user FROM Skill WHERE Skill.publisher = user_id;
	insert into Temp values ('avg_user',avg_user);
        if(avg_user >= 0 && avg_user <= 5.0) then
            update User set rank = avg_user where User.cellphone = user_id;
        end if;         
    end;    
$$


# 插入一条成交记录会导致技能的成交次数自增

create trigger successrecord_before_insert_trigger 
    before insert on SuccessRecord
    for each row
    begin
	update Skill set successTimes = successTimes + 1 where Skill.skillId = new.skillId;
    end;
$$


# 增加订单时,检查状态是否为"未接受";并且在OrderWithCreateTime添加一条记录

create trigger skillorder_after_insert_trigger 
    after insert on SkillOrder
    for each row
    begin
	if(new.status!=0) then
	    insert into table_not_exist values('');
	end if;	
	insert into OrderWithCreateTime values(new.orderId,null);
    end;
$$






# 增加审核记录时,会修改相应技能的审核状态

create trigger reviewrecord_before_insert_trigger 
    before insert on ReviewRecord
    for each row
    begin
	if(new.result=false) then
	    update Skill set Skill.legal = 2 where Skill.skillId = new.skillId;
        else
	    update Skill set Skill.legal = 1 where Skill.skillId = new.skillId;
	end if;
    end;
$$


# 技能在修改时,会自动备份

create trigger skill_before_update_trigger 
    before update on Skill
    for each row
    begin
	insert into SkillHistory values
            (null,new.skillId,new.skillName,new.skillDesc,new.price,null);
    end;
$$


delimiter ;
