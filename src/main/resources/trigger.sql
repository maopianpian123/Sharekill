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



create trigger comment_after_insert_trigger 
    after insert on Comment
    for each row
    begin
        declare avg_skill decimal(3,2);
        declare avg_user decimal(3,2);
        declare user_id bigint;

	select username into user_id from User,Skill where Skill.publisher = User.username and Skill.skillId = new.skillId;
	insert into Temp values ('user_id',user_id,null,null);
        
        update Skill set Skill.commentTimes = Skill.commentTimes+1 where Skill.skillId=new.skillId; 
        
        SELECT AVG(score) into avg_skill FROM Comment WHERE Comment.skillId = new.skillId;
	insert into Temp values ('avg_skill',avg_skill,null,null);
        if(avg_skill >= 0 && avg_skill <= 5.0) then
            update Skill set score = avg_skill where Skill.skillId = new.skillId;
        end if;
        
        SELECT AVG(score) into avg_user FROM Skill WHERE Skill.publisher = user_id;
	insert into Temp values ('avg_user',avg_user,null,null);
        if(avg_user >= 0 && avg_user <= 5.0) then
            update User set rank = avg_user where User.username = user_id;
        end if;         
    end;    
$$



create trigger successrecord_before_insert_trigger 
    before insert on SuccessRecord
    for each row
    begin
	update Skill set successTimes = successTimes + 1 where Skill.skillId = new.skillId;
    end;
$$



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



create trigger skill_before_update_trigger 
    before update on Skill
    for each row
    begin
	insert into SkillHistory values
            (null,new.skillId,new.skillName,new.skillDesc,new.price,null);
    end;
$$


delimiter ;
