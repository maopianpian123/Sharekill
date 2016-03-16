package org.shareskill.dao;

import java.util.List;

import org.shareskill.pojo.Order;
import org.springframework.cache.annotation.Cacheable;
public interface OrderDao {
	@Cacheable("OrderList")
	public List<Order> getAllOrder();
	
	public Order getOrderbyId(int id);
	
	public void insert(Order order)throws Exception;
	
	public void update(int orderId,String contact,String remark,double price)throws Exception;
		
	public void delete(int id)throws Exception;

}
