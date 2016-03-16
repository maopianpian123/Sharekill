package org.shareskill.web;

import java.util.HashMap;
import java.util.Map;

import org.shareskill.pojo.User;

import static org.springframework.web.bind.annotation.RequestMethod.*;


import org.shareskill.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/api")
public class UserController {

	@Autowired 
	private UserDao userDao;
	@RequestMapping(value="/userregister",method=POST,produces="application/json")
	public Map<String,Object> register(
			@RequestParam("username") long username,
			@RequestParam("getPwd_MD5") String getPwd_MD5
			)throws Exception{
			Map<String,Object> result = new HashMap<>();
			if(username==0)
			{
				result.put("status", "fail");
				result.put("reason", "user-no-exist");
				return result;
			}
			userDao.register(username,getPwd_MD5);
			result.put("status", "success");
			result.put("reason", "");
			return result;
}
	

	@RequestMapping(value="/afterlogin",method=POST,produces="application/json")
	public Map<String,Object> afterlogin(
			@RequestParam("sex") boolean sex,
			@RequestParam("nickname") String nickname
			) throws Exception{
		Map<String,Object> result = new HashMap<>();
		 if(nickname.equals(""))
			{
			result.put("status", "fail");
			result.put("reason", "nickname-no-exist");
			return result;
			}
		 userDao.afterlogin(sex,nickname);
			result.put("status", "success");
			result.put("reason", "");
			return result;
	}

	@RequestMapping(value="/userupdate",method=POST,produces="application/json")
	public Map<String,Object> userupdate(
			@RequestParam(value = "sex", required = false) boolean sex,
			@RequestParam(value = "realName", required = false) String realName,
			@RequestParam(value = "nickName", required = false) String nickName,
			@RequestParam(value = "job", required = false) String job,
			@RequestParam(value = "location", required = false) String location,
			@RequestParam(value = "selfDesc", required = false) String selfDesc,
			@RequestParam(value = "cellphone") long cellphone
			) throws Exception{
			Map<String,Object> result = new HashMap<>();
			if(cellphone==0)
			{
				result.put("status", "fail");
				result.put("reason", "user-no-exist");
				return result;
			}
			User user=new User(sex,cellphone,realName,nickName,job,location,selfDesc);	
			userDao.update(user);
			result.put("status", "success");
			result.put("reason", "");
			return result;
}
}
