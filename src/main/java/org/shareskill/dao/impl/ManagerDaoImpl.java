package org.shareskill.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.shareskill.dao.ManagerDao;
import org.shareskill.dao.mapper.ManagerRowMapper;
import org.shareskill.pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class ManagerDaoImpl implements ManagerDao{
private static Logger log = Logger.getLogger(ManagerDaoImpl.class);
	
    ManagerRowMapper mapper = new ManagerRowMapper();
	
	@Autowired
	private JdbcOperations jdbcOperations;
	
	//查询指定技能的所有评论
	@Override
	public List<Manager> getAllManager(){
		List<Manager> managers = jdbcOperations.query("select * from manager", mapper);
		log.info("Find " + managers.size() + " managers.");
		return managers;
	}
	
	//查询指定id的管理员
	@Override
	public Manager getManagerByid(String id){
		 return jdbcOperations.queryForObject("select * from manager where identifyNo = ?", mapper, id);	
	}
	
	//添加新的管理员
	@Override
		public void insert(Manager manager)throws Exception{
			 String sql="insert into manager(realName,pwdDigest,identifyNo,enrollTime,authority) values(?,?,?,?,?)";  
			 Date enrollTime = new Date(System.currentTimeMillis());
			 Object obj[]={manager.getRealName(),manager.getPwdDigest(),manager.getIdentifyNo(),enrollTime,2};  
		        try{
			    this.jdbcOperations.update(sql,obj);
				log.info("manager"+manager.getIdentifyNo()+ " insert success.");
		    	} catch (Exception e) {
		    		e.printStackTrace();
				}
		
		}
	
	//修改指定管理员
		@Override
			public void update(Manager manager)throws Exception{
				 String sql="update manager set realName=?,pwdDigest=?,identifyNo=?where identifyNo=?";  
				 Object obj[]={manager.getRealName(),manager.getPwdDigest(),manager.getIdentifyNo(),manager.getIdentifyNo()};  
			        try{
				    this.jdbcOperations.update(sql,obj);
					log.info("manager"+manager.getIdentifyNo()+ "update  success.");
			    	} catch (Exception e) {
			    		e.printStackTrace();
					}
			
			}
		
	@Override
		public void delete(String id)throws Exception{
			 String sql="delete from manager where identifyNo="+id;  
			  try{
		        this.jdbcOperations.update(sql);  
		        log.info("manager " + id+ " delete success.");
			  } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
}
