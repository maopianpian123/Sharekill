drop schema if exists share;
create SCHEMA share;
use share;



CREATE TABLE User(

    username bigint,

    sex boolean,

    realName varchar(50),

    nickName varchar(100),

    enrollTime timestamp,

    pwdDigest varchar(300) not null,

    job varchar(50) default '自由职业者',

    location varchar(100),

    selfDesc varchar(300),

    rank decimal(3,2) default 3.0,

    CONSTRAINT pk_user PRIMARY KEY (username)
);



CREATE TABLE Manager(

    realName varchar(50),

    pwdDigest varchar(300) not null,

    identifyNo varchar(50),

    enrollTime timestamp,

    authority int default 2,
    
    CONSTRAINT pk_manager PRIMARY KEY (identifyNo)
);



CREATE TABLE Tag(

    tagName varchar(30),

    creator varchar(50),

    createTime timestamp,


    
    CONSTRAINT pk_tag PRIMARY KEY (tagName),

    CONSTRAINT fk_tag_creator_provider FOREIGN KEY (creator) 
        REFERENCES Manager(identifyNo) on update cascade on delete set null
);




CREATE TABLE Skill(

    skillId int auto_increment,

    skillName varchar(60) not null,

    skillDesc varchar(300) not null,

    publisher bigint,

    createTime timestamp,
    

    score decimal(3,2) default 3.0,

    price decimal(10,2) not null,

    contact varchar(255) not null,

    successTimes int default 0,

    commentTimes int default 0,

    legal tinyint default 0, 
    
    CONSTRAINT pk_skill PRIMARY KEY (skillId),
    CONSTRAINT fk_skill_user_publisher FOREIGN KEY (publisher) 
        REFERENCES User(username) on delete cascade on update cascade
);




CREATE TABLE Comment(

    commentId int auto_increment,

    reviewer bigint,

    skillId int,

    content varchar(2000) not null,

    createTime timestamp,

    score decimal(3,2) not null,
    
    CONSTRAINT pk_comment PRIMARY KEY (commentId),

    CONSTRAINT fk_comment_user FOREIGN KEY (reviewer) 
        REFERENCES User(username) on delete set null on update cascade,

    CONSTRAINT fk_comment_skill FOREIGN KEY (skillId) 
        REFERENCES Skill(skillId) on delete set null on update cascade
);



CREATE TABLE ReviewRecord(
    

    recordId int auto_increment,

    skillId int,

    reviewer varchar(50),

    reviewTime timestamp, 

    result boolean not null,

    remark varchar(500),
    
    CONSTRAINT pk_reviewrecord PRIMARY KEY (recordId),

    CONSTRAINT fk_reviewrecord_skill FOREIGN KEY (skillId) 
        REFERENCES Skill(skillId) on delete set null on update cascade,

    CONSTRAINT fk_reviewrecord_manager FOREIGN KEY (reviewer) 
        REFERENCES Manager(identifyNo) on delete set null on update cascade
);



CREATE TABLE SuccessRecord(


    recordId int auto_increment,

    provider bigint,

    consumer bigint,

    skillId int,

    finalAmount decimal(10,2) not null,
   
    CONSTRAINT pk_successrecord PRIMARY KEY (recordId),

    CONSTRAINT fk_successrecord_user_provider FOREIGN KEY (provider) 
        REFERENCES User (username) on delete set null on update cascade,

    CONSTRAINT fk_successrecord_user_consumer FOREIGN KEY (consumer) 
        REFERENCES User (username) on delete set null on update cascade,

    CONSTRAINT fk_successrecord_skill FOREIGN KEY (skillId) 
        REFERENCES Skill (skillId) on delete set null on update cascade

);




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



CREATE TABLE SkillTag(

    skillId int,
    
    tag varchar(30),
    
    CONSTRAINT pk_skilltag PRIMARY KEY (skillId,tag),
    
    CONSTRAINT fk_skilltag_skill FOREIGN KEY (skillId) 
        REFERENCES Skill (skillId) on delete cascade on update cascade,
    
    CONSTRAINT fk_skilltag_tag FOREIGN KEY (tag) 
        REFERENCES Tag (tagName) on delete cascade on update cascade
);



CREATE TABLE SkillOrder(


    orderId int auto_increment,

    creator bigint,

    provider bigint,

    skillId int,
    

    status int default 0,
    contact varchar(100),

    remark varchar(300),

    price decimal(10,2) not null,
    

    createTime timestamp,

    receiveTime timestamp,

    completeTime timestamp,

    CONSTRAINT pk_order PRIMARY KEY (orderId),
    
    CONSTRAINT fk_order_user_creator FOREIGN KEY (creator) 
        REFERENCES User (username) on delete cascade on update cascade,
    
    CONSTRAINT fk_order_user_provider FOREIGN KEY (provider) 
        REFERENCES User (username) on delete cascade on update cascade,
    
    CONSTRAINT fk_order_skill FOREIGN KEY (skillId)
        REFERENCES Skill (skillId) on delete cascade on update cascade
        
);



CREATE TABLE SkillHistory(


    historyId int auto_increment,

    skillId int,

    skillName varchar(60) not null,

    skillDesc varchar(300) not null,

    price decimal(10,2) not null,

    updateTime timestamp,
    
    CONSTRAINT pk_skillhistory PRIMARY KEY (historyId),
    
    CONSTRAINT fk_skillhistory_skill FOREIGN KEY (skillId) 
        REFERENCES Skill (skillId) on delete cascade on update cascade
);



CREATE TABLE OrderWithCreateTime(
    
    orderId int,
    createTime timestamp,
    CONSTRAINT pk_orderwithcreatetime PRIMARY KEY (orderId),

    CONSTRAINT fk_orderwithcreatetime_skillorder FOREIGN KEY (orderId) 
        REFERENCES SkillOrder (orderId) on delete cascade on update cascade

);


CREATE TABLE OpenIM(

    username bigint,
    openimAccount varchar(200) not null,
    CONSTRAINT pk_openim PRIMARY KEY (username),

    CONSTRAINT fk_openim_user FOREIGN KEY (username) 
        REFERENCES User (username) on delete cascade on update cascade
);


CREATE TABLE JPush(

    username bigint,
    jpushAccount varchar(200) not null,
    CONSTRAINT pk_jpush PRIMARY KEY (username),

    CONSTRAINT fk_jpush_user FOREIGN KEY (username) 
        REFERENCES User (username) on delete cascade on update cascade
);



CREATE Table Temp(
    log varchar(100),
    username bigint,
    note varchar(200),
    currentTime timestamp
);
