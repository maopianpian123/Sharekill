/**
 * 2016年3月2日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.shareskill.authentication.RsaUtil;
import org.shareskill.authentication.TokenUtil;
import org.shareskill.authentication.Validate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用来管理注册,登录,登出,忘记密码等功能
 * @author hit_lacus@126.com
 */
@RestController
@RequestMapping(value="/api")
public class AccountController {
	
	private static Logger log = Logger.getLogger(AccountController.class);
	
	private String appkey = "23311785";
	
	private String secret = "693874e4b8d34471e32be0ca561e2521";
	
	private String url = "http://gw.api.taobao.com/router/rest";
	
	
	
	
	@RequestMapping(value="/register",method=POST,produces="application/json")
	public Map<String,Object> register(
			@RequestParam("tokenrsa") String tokenRsa,
			@RequestParam("tokenplain") String tokenPlain,
			@RequestParam("signature") String signature){
		
		Map<String,Object> result = new HashMap<>();
			
		
		return result;
	}
	
	
	/**
	 * 登录方法
	 * @param tokenRsa
	 * @param tokenPlain
	 * @param signature
	 * @return
	 */
	@RequestMapping(value="/login",method=POST,produces="application/json")
	public Map<String,Object> login(
			@RequestParam("tokenrsa") String tokenRsa,
			@RequestParam("tokenplain") String tokenPlain,
			@RequestParam("signature") String signature){
		
		log.info("tokenrsa = " + tokenRsa);
		log.info("tokenPlain = " + tokenPlain);
		log.info("signature = " + signature);
		
		Map<String,Object> result = new HashMap<>();
		String validateResult = Validate.validate(tokenRsa, tokenPlain, signature,true);
		log.info("validateResult = " + validateResult);
		if(!validateResult.equalsIgnoreCase("ok")){
			result.put("status", "fail");
			result.put("reason", validateResult);
			return result;
		}
		
		String token = "";
		
        try {
	        token = RsaUtil.getRealToken(tokenRsa) + tokenPlain;
	        log.info("token = " + token);
        } catch (Exception e) {
        	result.put("status", "fail");
			result.put("reason", "unknown-reason");
			return result;
        }
		
		boolean loginSuccess = TokenUtil.register(token);
		if(loginSuccess){
			result.put("status", "success");
			result.put("reason", "");
			return result;
		}else{
			result.put("status", "fail");
			result.put("reason", "cannot-register");
			return result;
		}
		
	}
	
	/**
	 * 
	 * @param tokenRsa
	 * @param tokenPlain
	 * @param signature
	 * @return
	 */
	@RequestMapping(value="/logout",method=POST,produces="application/json")
	public Map<String,Object> logout(
			@RequestParam("tokenrsa") String tokenRsa,
			@RequestParam("tokenplain") String tokenPlain,
			@RequestParam("signature") String signature){
		
		Map<String,Object> result = new HashMap<>();
		
		String validateResult = Validate.validate(tokenRsa, tokenPlain, signature,false);
		
		if(!validateResult.equalsIgnoreCase("ok")){
			result.put("status", "fail");
			result.put("reason", validateResult);
			return result;
		}
		
		String token = "";
		
        try {
	        token = RsaUtil.getRealToken(tokenRsa) + tokenPlain;
        } catch (Exception e) {
        	result.put("status", "fail");
			result.put("reason", "unknown-reason");
			return result;
        }
		
		boolean invalidSuccess = TokenUtil.invalidateToken(token);
		if(invalidSuccess){
			result.put("status", "success");
			result.put("reason", "");
			return result;
		}else{
			result.put("status", "fail");
			result.put("reason", "cannot-register");
			return result;
		}
	}
	
	
	/**
	 * 
	 * @param tokenRsa
	 * @param tokenPlain
	 * @param signature
	 * @param oldToken
	 * @return
	 */
	@RequestMapping(value="/forgetPassword",method=POST,produces="application/json")
	public Map<String,Object> forgetPassword(
			@RequestParam("tokenrsa") String tokenRsa,
			@RequestParam("tokenplain") String tokenPlain,
			@RequestParam("signature") String signature,
			@RequestParam("oldtoken") String oldToken){
		
		Map<String,Object> result = new HashMap<>();
		
		String validateResult = Validate.validate(tokenRsa, tokenPlain, signature,false);
		
		if(!validateResult.equalsIgnoreCase("ok")){
			result.put("status", "fail");
			result.put("reason", validateResult);
			return result;
		}
		
		String token = "";
		
        try {
	        token = RsaUtil.getRealToken(tokenRsa) + tokenPlain;
        } catch (Exception e) {
        	result.put("status", "fail");
			result.put("reason", "unknown-reason");
			return result;
        }
		
		boolean invalidSuccess = TokenUtil.invalidateToken(oldToken);
		
		if(invalidSuccess){
			
		}else{
			result.put("status", "fail");
			result.put("reason", "cannot-clear-old-token");
			return result;
		}
		
		
		boolean loginSuccess = TokenUtil.register(token);
		if(loginSuccess){
			result.put("status", "success");
			result.put("reason", "");
			return result;
		}else{
			result.put("status", "fail");
			result.put("reason", "cannot-register");
			return result;
		}		
	}
	
	
	
	
	

}
