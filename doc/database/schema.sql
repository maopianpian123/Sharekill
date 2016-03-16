#######################################################
#######################################################
#######################################################
##############      CREATE TABLE     ##################
##############        建表语句       ##################
#######################################################
#######################################################
#######################################################

#######################################################
#                    数据库说明                       #


#*****************************************************#
# 请按顺序执行以下SQL脚本
# 文件:schema.sql      : 执行建表SQL语句
# 文件:trigger.sql     : 执行建立触发器SQL语句
# 文件:function.sql    : 执行建立函数的SQL语句
# 文件:scheduler.sql   : 执行建立计划任务的SQL语句
# 文件:insert-data.sql : 执行插入测试数据的SQL语句
#*****************************************************#




#*****************************************************#
#共12张表,8个触发器,4个函数,2个计划任务
#*****************************************************#





#    ***表包括****************************************#
#1. 用户                                        : User 
#2. 管理员                                      : Manager
#3. 标签                                        : Tag
#4. 技能                                        : Skill
#5. 评论                                        : Comment
#6. 审查记录                                    : ReviewRecord
#7. 成交记录(技能成功交易了一笔)                : SuccessRecord
#8. 技能用户收藏关联表(用户收藏了一个技能)      : SkillCollection
#9. 技能标签收藏关联表(技能拥有一个标签)        : SkillTag
#10.订单                                        : SkillOrder
#11.技能修改历史(记录每次用户对技能的修改)      : SkilHistory
#12.订单临时表(用来监控订单超时自动取消)        : OrderWithCreateTime



#    ***触发器包括*************************************
#1. manager_before_insert_trigger               : 不允许建立第二个超级管理员           
#2. manager_before_update_trigger               : 不允许修改超级管理员
#3. manager_before_delete_trigger               : 不允许删除超级管理员
#4. comment_before_insert_trigger               : 在添加评论(并且评分时)时,会同步修改技能的评分和用户的评分;增加技能的评论次数
#5. successrecord_before_insert_trigger         : 在添加成功记录之时,会增加技能的成交次数
#6. skillorder_before_insert_trigger            : 检查订单状态是否为未接受,并且在订单临时表插入一条记录(主要记录订单的创建时间)
#7. reviewrecord_before_insert_trigger          : 完成一个审核时,会自动修改相应技能的审核状态(变为已审核或者未审核)
#8. skill_before_update_trigger                 : 修改一个技能会保存一份技能的历史记录 



#    ***函数包括***************************************
#1. 确认订单                                    : confirmOrderFun
#2. 拒绝订单                                    : rejectOrderFun
#3. 订单完成                                    : completeOrderFun
#4. 订单失败                                    : failOrderFun



#    ***计划任务包括***********************************
#1. check_timeout_skillorder_event              : 定时(每十分钟)检查一次有没有需要超时取消的订单,有则取消订单
#2. check_timeout_skillhistory_event            : 定时(每天)检查有没有存在时间过长的技能历史记录,有则删除




#######################################################
#######################################################


drop database if exists share;
create database share;
use share;

# 开启计划任务
SET GLOBAL event_scheduler = ON;

#######################################################
#用户

CREATE TABLE User(
    
    #手机号码
    username bigint,
    #性别
    sex boolean,
    #真名
    realName varchar(50),
    #昵称
    nickName varchar(100),
    #注册时间
    enrollTime timestamp,
    #加密之后的密码
    pwdDigest varchar(300) not null,
    #职业
    job varchar(50) ,
    #所在地
    location varchar(100),
    #自我描述
    selfDesc varchar(300),
    #个人技能评分
    #需要使用触发器更新个人评分
    rank decimal(3,2) default 3.0,
    
    CONSTRAINT pk_user PRIMARY KEY (username)
);

#######################################################
#管理员

CREATE TABLE Manager(
    #真名
    realName varchar(50),
    #加密之后的密码
    pwdDigest varchar(300) not null,
    #身份证件号
    identifyNo varchar(50),
    #注册时间
    enrollTime timestamp,
    #权限等级,1是超级管理员,2是一般业务人员
    #通过触发器不允许创建超过一个超级管理员
    #不允许删除和修改超级管理员
    authority int default 2,
    
    #身份证号是主键
    CONSTRAINT pk_manager PRIMARY KEY (identifyNo)
);


#######################################################
#标签

CREATE TABLE Tag(
    #标签名
    tagName varchar(30),
    #创建者
    creator varchar(50),
    #创建时间
    createTime timestamp,

    #
    
    CONSTRAINT pk_tag PRIMARY KEY (tagName),
    #标签必须指定一名管理员作为创建者
    #如果管理员被删除,则creator置空
    CONSTRAINT fk_tag_creator_provider FOREIGN KEY (creator) 
        REFERENCES Manager(identifyNo) on update cascade on delete set null
);



#######################################################
#技能

CREATE TABLE Skill(

    #技能编号
    skillId int auto_increment,
    #技能名
    skillName varchar(60) not null,
    #技能描述
    skillDesc varchar(300) not null,
    #发布者
    publisher bigint,
    #创建日期
    createTime timestamp,
    
    #评分
    score decimal(3,2) default 3.0,
    #单价
    price decimal(10,2) not null,
    #联系方式
    contact varchar(255) not null,
    #成交次数
    #需要添加触发器实现自增
    successTimes int default 0,
    #评论次数
    #需要添加触发器实现自增
    commentTimes int default 0,
    #是否审核通过
    # 0 = 未审核
    # 1 = 审核通过
    # 2 = 审核失败
    legal tinyint default 0, 
    
    CONSTRAINT pk_skill PRIMARY KEY (skillId),
    #技能发布者必须是一个已经存在的用户
    CONSTRAINT fk_skill_user_publisher FOREIGN KEY (publisher) 
        REFERENCES User(username) on delete cascade on update cascade
);



#######################################################
#评论

CREATE TABLE Comment(
    #评论编号
    commentId int auto_increment,
    #评论者
    reviewer bigint,
    #技能编号
    skillId int,
    #评论内容
    content varchar(2000) not null,
    #评论时间
    createTime timestamp,
    #评分
    score decimal(3,2) not null,
    
    CONSTRAINT pk_comment PRIMARY KEY (commentId),
    #评论者必须是一个已经存在的用户
    CONSTRAINT fk_comment_user FOREIGN KEY (reviewer) 
        REFERENCES User(username) on delete set null on update cascade,
    #技能必须存在
    CONSTRAINT fk_comment_skill FOREIGN KEY (skillId) 
        REFERENCES Skill(skillId) on delete set null on update cascade
);


#######################################################
#审查记录

CREATE TABLE ReviewRecord(
    
    #记录编号
    recordId int auto_increment,
    #审查技能
    skillId int,
    #审查者
    reviewer varchar(50),
    #审查时间
    reviewTime timestamp, 
    #审查结果
    result boolean not null,
    #审查备注
    remark varchar(500),
    
    CONSTRAINT pk_reviewrecord PRIMARY KEY (recordId),
    #技能必须已经存在
    CONSTRAINT fk_reviewrecord_skill FOREIGN KEY (skillId) 
        REFERENCES Skill(skillId) on delete set null on update cascade,
    #审查者必须是一名管理员
    CONSTRAINT fk_reviewrecord_manager FOREIGN KEY (reviewer) 
        REFERENCES Manager(identifyNo) on delete set null on update cascade
);

#######################################################
#成交记录

CREATE TABLE SuccessRecord(

    #记录编号
    recordId int auto_increment,
    #技能提供者
    provider bigint,
    #消费者
    consumer bigint,
    #技能编号
    skillId int,
    #实际消费金额
    finalAmount decimal(10,2) not null,
   
    CONSTRAINT pk_successrecord PRIMARY KEY (recordId),
    
    #技能提供者必须是一名已经存在的用户
    CONSTRAINT fk_successrecord_user_provider FOREIGN KEY (provider) 
        REFERENCES User (username) on delete set null on update cascade,
    #消费者必须是一名已经存在的用户
    CONSTRAINT fk_successrecord_user_consumer FOREIGN KEY (consumer) 
        REFERENCES User (username) on delete set null on update cascade,
    #成交的技能必须存在
    CONSTRAINT fk_successrecord_skill FOREIGN KEY (skillId) 
        REFERENCES Skill (skillId) on delete set null on update cascade

);


#######################################################
#技能收藏

CREATE TABLE SkillCollection(
    
    skillId int,
    collector bigint,
    collectionTime timestamp,
    
    CONSTRAINT pk_skillcollection PRIMARY KEY (skillId,collector),
    
    CONSTRAINT fk_skillcollection_skill FOREIGN KEY (skillId) 
        REFERENCES Skill (skillId) on delete cascade on update cascade,
    
    CONSTRAINT fk_skillcollection_user FOREIGN KEY (collector) 
        REFERENCES User (username) on delete cascade on update cascade

);


#######################################################
#技能标签联系表

CREATE TABLE SkillTag(

    skillId int,
    
    tag varchar(30),
    
    CONSTRAINT pk_skilltag PRIMARY KEY (skillId,tag),
    
    CONSTRAINT fk_skilltag_skill FOREIGN KEY (skillId) 
        REFERENCES Skill (skillId) on delete cascade on update cascade,
    
    CONSTRAINT fk_skilltag_tag FOREIGN KEY (tag) 
        REFERENCES Tag (tagName) on delete cascade on update cascade
);


#######################################################
#订单

CREATE TABLE SkillOrder(

    #订单号
    orderId int auto_increment,
    #发起人
    creator bigint,
    #服务提供者
    provider bigint,
    #相关技能
    skillId int,
    
    #订单状态
    # 0 = 未接受
    # 1 = 超时取消
    # 2 = 卖家拒绝
    # 3 = 卖家接受,待服务
    # 4 = 服务完成
    # 5 = 服务因为某种原因未完成(不包括超时自动取消和卖家拒绝)

    # 订单状态流程图
    # 0 - - - -> 1(END)   :  发起订单 - 超时取消 - 结束       :   通过MySQL计划任务实现
    # 0 - - - -> 2        :  发起订单 - 卖家接受              :   通过MySQL函数实现
    # 0 - - - -> 3(END)   :  发起订单 - 卖家拒绝订单 - 结束   :   通过MySQL函数实现
    # 2 - - - -> 4(END)   :  卖家接受 - 服务完成 - 结束       :   通过MySQL函数实现
    # 2 - - - -> 5(END)   :  卖家接受 - 服务未完成 - 结束     :   通过MySQL函数实现
    status int default 0,
    #发起者联系方式
    contact varchar(100),
    #发起者备注
    remark varchar(300),

    #单价
    price decimal(10,2) not null,
    
    #发起时间
    createTime timestamp,
    #接受时间
    receiveTime timestamp,
    #完成时间
    completeTime timestamp,

    CONSTRAINT pk_order PRIMARY KEY (orderId),
    
    CONSTRAINT fk_order_user_creator FOREIGN KEY (creator) 
        REFERENCES User (username) on delete cascade on update cascade,
    
    CONSTRAINT fk_order_user_provider FOREIGN KEY (provider) 
        REFERENCES User (username) on delete cascade on update cascade,
    
    CONSTRAINT fk_order_skill FOREIGN KEY (skillId)
        REFERENCES Skill (skillId) on delete cascade on update cascade
        
);


#######################################################
#技能修改历史

CREATE TABLE SkillHistory(

    #记录编号
    historyId int auto_increment,
    #技能编号
    skillId int,
    #技能名
    skillName varchar(60) not null,
    #技能描述
    skillDesc varchar(300) not null,
    #单价
    price decimal(10,2) not null,
    #更新时间
    updateTime timestamp,
    
    CONSTRAINT pk_skillhistory PRIMARY KEY (historyId),
    
    CONSTRAINT fk_skillhistory_skill FOREIGN KEY (skillId) 
        REFERENCES Skill (skillId) on delete cascade on update cascade
);


#######################################################
#记录订单创建时间

CREATE TABLE OrderWithCreateTime(
    
    orderId int,
    createTime timestamp,
    CONSTRAINT pk_orderwithcreatetime PRIMARY KEY (orderId),

    CONSTRAINT fk_orderwithcreatetime_skillorder FOREIGN KEY (orderId) 
        REFERENCES SkillOrder (orderId) on delete cascade on update cascade

);


#######################################################
#测试专用表
CREATE Table Temp(
     log varchar(100),
     username bigint
);
