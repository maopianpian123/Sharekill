package org.shareskill.web;

import java.util.HashMap;
import java.util.Map;

import org.shareskill.pojo.Skill;
import org.shareskill.pojo.User;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.shareskill.authentication.RsaUtil;
import org.shareskill.authentication.TokenUtil;
import org.shareskill.authentication.Validate;
import org.shareskill.dao.SkillDao;
import org.shareskill.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/api")
public class SkillController {
	@Autowired 
	private SkillDao skillDao;
	@Autowired 
	private UserDao userDao;
	@RequestMapping(value="/skill",method=POST,produces="application/json")
	public Map<String,Object> insert(
			@RequestParam("username") long username,
			@RequestParam("price") double price,
			@RequestParam("contactWay") String contactWay, 
			@RequestParam("contact") String contact1,
			@RequestParam("desc") String desc, 
			@RequestParam("skillname") String skillname )throws Exception{
			Map<String,Object> result = new HashMap<>();
			String contact=contactWay+","+contact1;
			if(username==0)
			{
				result.put("status", "fail");
				result.put("reason", "user-no-exist");
				return result;
			}
			else if(price<2||price>10)
			{
			result.put("status", "fail");
			result.put("reason", "price-invalid");
			return result;
			}
		 else if(contact.equals(""))
		 {
			 result.put("status", "fail");
				result.put("reason", "contact-invaild");
				return result;
		 }
			User user=userDao.getUserById(username);
			Skill skill=new Skill(0, skillname, desc, 0, 0, 0, contact, price, 0, user);
			skillDao.insert(skill,username);
			result.put("status", "success");
			result.put("reason", "");
			return result;
}
	

	@RequestMapping(value="/update",method=POST,produces="application/json")
	public Map<String,Object> update(
			@RequestParam("skillId") int skillId,
			@RequestParam("username") long username,
			@RequestParam("price") double price,
			@RequestParam("contactWay") String contactWay, 
			@RequestParam("contact") String contact1,
			@RequestParam("desc") String desc, 
			@RequestParam("skillname") String skillname 
			) throws Exception{
		String contact=contactWay+","+contact1;
		Map<String,Object> result = new HashMap<>();
		 if(price<2||price>10)
			{
			result.put("status", "fail");
			result.put("reason", "price-invalid");
			return result;
			}
		 else if(contact.equals(""))
		 {
			 result.put("status", "fail");
				result.put("reason", "contact-invaild");
				return result;
		 }
		 User user=userDao.getUserById(username);
		Skill skill=new Skill(skillId, skillname, desc, 0, 0, 0, contact, price, 0, user);
		 skillDao.update(skill);
			result.put("status", "success");
			result.put("reason", "");
			return result;
	}

	@RequestMapping(value="/delete",method=POST,produces="application/json")
	public Map<String,Object> delete(
			@RequestParam("skillId") int skillId) throws Exception{
			Map<String,Object> result = new HashMap<>();
			skillDao.delete(skillId);
			result.put("status", "success");
			result.put("reason", "");
			return result;
}
}
