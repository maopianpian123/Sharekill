/**
 * 2016年2月24日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.authentication;

import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.OpenAccountTokenValidateRequest;
import com.taobao.api.response.OpenAccountTokenValidateResponse;

/**
 * 令牌管理器(阿里百川云账号)
 * @author hit_lacus@126.com
 */
public class TokenUtil {
	
	/**
	 * token和UserDetail的映射
	 */
	private static ConcurrentHashMap<String,UserDetail> tokens = new ConcurrentHashMap<>();
	private static String appkey = "23311785";
	private static String secret = "693874e4b8d34471e32be0ca561e2521";
	private static String url = "http://gw.api.taobao.com/router/rest";
	
	
	//TODO 测试令牌
	static{
		UserDetail ud = new UserDetail();
		tokens.put("9cce93846bf2dc36b80183ff0378677c",ud);
	}
	
	/**
	 * 注册一个token到内存中,使得下次好验证,如果失败则返回
	 * @param token
	 * @return true 注册成功,false 注册失败
	 */
	public static boolean register(String token){
		
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		OpenAccountTokenValidateRequest req = new OpenAccountTokenValidateRequest();
		req.setParamToken(token);
		OpenAccountTokenValidateResponse rsp;
        try {
	        rsp = client.execute(req);
	        
	        if(rsp.isSuccess()){
	        	
	        	UserDetail ud = new UserDetail();
	        	//String isvAccountId = rsp.getData().getData().getIsvAccountId();
	        	String openAccountId = rsp.getData().getData().getOpenAccountId() + "";
	        	String mobile = rsp.getData().getData().getExt().getOpenAccount().getMobile();
	        	ud.setOpenAccountId(openAccountId);
	        	//ud.setIsvAccountId(isvAccountId);
	        	ud.setMobile(mobile);
	        	
	        	tokens.put(token,ud);
	        	return true;
	        }else{
	        	return false;
	        }
        } catch (ApiException e) {
	        return false;
        }
		
	}
	
	/**
	 * 验证token是否存在于我的服务器端
	 * @param token
	 * @return 验证成功则返回一个UserDetail,验证错误则返回一个null
	 */
	public static UserDetail validate(String token){
		return tokens.getOrDefault(token, null);
	}
	
	/**
	 * 设置即时消息的账号
	 * @param imAccount
	 * @return
	 */
	public static UserDetail updateImAccount(String imAccount){
		
		
		return null;
	}
	
	/**
	 * 设置云推送功能的账号
	 * @param pushAccount
	 * @return
	 */
	public static UserDetail updatePushAccount(String pushAccount){
		
		
		return null;
	}
	
	/**
	 * 使得令牌失效
	 * @param token
	 * @return
	 */
	public static boolean invalidateToken(String token){
		if(tokens.remove(token) == null){
			return false;
		}else{
			return true;
		}
	}
	
	

}
