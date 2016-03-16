package org.shareskill.dao;

import java.util.List;

import org.shareskill.pojo.Manager;
import org.springframework.cache.annotation.Cacheable;
public interface ManagerDao {
	@Cacheable("ManagerList")
	public List<Manager> getAllManager();
	
	public Manager getManagerByid(String id);
	
	public void insert(Manager manager)throws Exception;
	
	public void update(Manager manager)throws Exception;
		
	public void delete(String id)throws Exception;

}
