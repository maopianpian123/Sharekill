/**
 * 2016年3月1日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.authentication;

import java.io.IOException;
import java.security.MessageDigest;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.OpenAccountTokenValidateRequest;
import com.taobao.api.response.OpenAccountTokenValidateResponse;

import org.apache.commons.io.IOUtils; 
import org.apache.commons.codec.digest.*; 

/**
 * @author hit_lacus@126.com
 */
public class Validate {
	
	private static String appkey = "23311785";
	
	private static String secret = "693874e4b8d34471e32be0ca561e2521";
	
	private static String url = "http://gw.api.taobao.com/router/rest";
	
	/**
	 * 验证请求参数里的token和signature合法
	 * */
	public static String validate(String tokenRsa,String tokenPlain,String signature,boolean isLogin){
				
		String token;
        try {
	        token = RsaUtil.getRealToken(tokenRsa) + tokenPlain;       
        } catch (Exception e) {
	        e.printStackTrace();
	        return "token-invalid-rsa";
        }
		
        
        UserDetail userDetail = TokenUtil.validate(token);
        
		if(userDetail == null){
			if(!isLogin){
				return "";
			}
		}
		
		long currentTime = System.currentTimeMillis()/60000;
		
		boolean isSuccess = false;
		
		for(int i = 0; i < 4; i++ ){
			try {
	            String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(token + (currentTime - i)));
	            if(signature.equalsIgnoreCase(md5)){
	            	isSuccess = true;
	            	break;
	            }   
            } catch (IOException e) {
	            e.printStackTrace();
            }
		}
		
		if(isSuccess){
			return "ok";
		}else{
			return "signature-invalid";
		}
		
		
	}

}
