#######################################################
#######################################################
#######################################################
#############      EVENT SCHEDULER     ################
#######################################################
#######################################################
#######################################################

delimiter $$

CREATE EVENT check_timeout_skillorder_event
    # 实际应该每十分钟执行一次,这里为了测试方便,三分钟执行一次
    ON SCHEDULE EVERY '3:0' MINUTE_SECOND 
    DO
    BEGIN
	#更新所有超过一天的未接受的订单状态为超时自动取消
	#为了测试方便,暂设为3分钟,实际为1440分钟(24小时)
	insert into temp values('计划任务在执行中~',1111);
	update SkillOrder set status=1 where SkillOrder.orderId in (
	    select orderId from OrderWithCreateTime where TIMESTAMPDIFF(MINUTE,OrderWithCreateTime.createTime,NOW()) > 3
	
        );
	#删除一部分过时记录,过时标准实际为两天,测试情况下为十分钟
	delete from OrderWithCreateTime where TIMESTAMPDIFF(MINUTE,OrderWithCreateTime.createTime,NOW()) > 10;
    END
$$

# 删除超过50天的技能历史记录

CREATE EVENT check_timeout_skillhistory_event
    ON SCHEDULE EVERY 1 DAY
    DO
    BEGIN
	#删除超过50天的技能历史记录
	delete from SkilHistory where TIMESTAMPDIFF(DAY,SkilHistory.updateTime,NOW()) > 50;
    END
$$










delimiter ;
