package org.shareskill.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.shareskill.dao.OrderDao;
import org.shareskill.dao.mapper.OrderRowMapper;
import org.shareskill.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao{
private static Logger log = Logger.getLogger(OrderDaoImpl.class);
	
    OrderRowMapper mapper = new OrderRowMapper();
	
	@Autowired
	private JdbcOperations jdbcOperations;
	
	@Override
	//查询所有订单
	public List<Order> getAllOrder(){
		List<Order> Orders = jdbcOperations.query("select * from order", mapper);
		log.info("Find " + Orders.size() + " Orders.");
		return Orders;
	}
	
	//插入新的评论
	@Override
	public Order getOrderbyId(int id){
	 return jdbcOperations.queryForObject("select * from order where auto_increment = ?", mapper, id);	
	}
	
	@Override
	public void insert(Order order)throws Exception{
		 String sql="insert into order(orderId,creator,provider,skillId,status,contact,remark,price,createTime) values(?,?,?,?,?,?,?,?,?)";  
		 Date createTime = new Date(System.currentTimeMillis());
		 Object obj[]={order.getOrderId(),order.getCreator(),order.getProvider(),order.getSkillId(),
				 order.getStatus(),order.getContact(),order.getRemark(),order.getPrice(),createTime};  
	        try{
		    this.jdbcOperations.update(sql,obj);
			log.info("Order"+order.getOrderId()+ " insert success.");
	    	} catch (Exception e) {
	    		e.printStackTrace();
			}
	
	}
	
	@Override
	public void update(int orderId,String contact,String remark,double price)throws Exception{
		 String sql="update order set contact=?,remark=?,price=?where orderId=?" ;
		 Object obj[]={contact,remark,price,orderId};  
	        try{
		    this.jdbcOperations.update(sql,obj);
			log.info("Order"+orderId+ " update success.");
	    	} catch (Exception e) {
	    		e.printStackTrace();
			}
	}
	
	@Override
		public void delete(int id)throws Exception{
			 String sql="delete from order where orderId="+id;  
			  try{
		        this.jdbcOperations.update(sql);  
		        log.info("order " + id+ " delete success.");
			  } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}
}
