/**
 * 2016年2月21日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.opencount;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.OpenAccount;
import com.taobao.api.request.OpenAccountCreateRequest;
import com.taobao.api.request.OpenAccountListRequest;
import com.taobao.api.request.OpenAccountTokenApplyRequest;
import com.taobao.api.request.OpenAccountTokenValidateRequest;
import com.taobao.api.request.OpenAccountUpdateRequest;
import com.taobao.api.response.OpenAccountCreateResponse;
import com.taobao.api.response.OpenAccountListResponse;
import com.taobao.api.response.OpenAccountTokenApplyResponse;
import com.taobao.api.response.OpenAccountTokenValidateResponse;
import com.taobao.api.response.OpenAccountUpdateResponse;

/**
 * @author hit_lacus@126.com
 */
public class OpenCountTest {
	
	private String appkey = "23311785";
	
	private String secret = "693874e4b8d34471e32be0ca561e2521";
	
	private String url = "http://gw.api.taobao.com/router/rest";
	
	
	
	private String token;
	
	//@Test
	public void testCreateCount() throws ApiException{
		
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		OpenAccountCreateRequest req = new OpenAccountCreateRequest();
		List<OpenAccount> list116729 = new ArrayList<OpenAccount>();
		OpenAccount acc = new OpenAccount();
		acc.setLoginId("hitlacus");
		acc.setCreateDeviceId("123456789");
		acc.setAlipayId("hit_lacus@126.com");
		acc.setLocale("zh_CN");
		acc.setBankCardNo("1234 7655 28763 211");
		acc.setIsvAccountId("hitlacus");
		acc.setEmail("hit_lacus@126.com");
		acc.setAvatarUrl("http://image.abc.com/aa.jpg");
		acc.setBankCardOwnerName("baymax");
		acc.setDisplayName("hitlacus");
		//acc.setLoginPwdSalt("WjndM");
		acc.setLoginPwd("123456");
		acc.setOpenId("123456789");
		acc.setMobile("18463101691");
		acc.setCreateLocation("120.146484,30.313617");
		acc.setExtInfos("{\"gender\":\"male\"}");
		acc.setLoginPwdIntensity(1L);
		acc.setType(2L);
		acc.setStatus(1L);
		acc.setLoginPwdEncryption(1L);
		acc.setGender(1L);
		acc.setName("hitlacus");
		acc.setBirthday("20150120");
		acc.setWangwang("hitlacus");
		acc.setWeixin("18463101691");
		acc.setOauthPlateform(1L);
		list116729.add(acc);
		req.setParamList(list116729);
		OpenAccountCreateResponse response = client.execute(req);
		
		
		System.out.println("===testCreateCount");
		System.out.println("- - - - - - ");
		System.out.println(response.getMsg());
		System.out.println(response.getBody());
		System.out.println("- - - - - - ");
		
		
		//4398047494521
	}
	
	
	//@Test
	public void testApplyToken() throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		OpenAccountTokenApplyRequest req = new OpenAccountTokenApplyRequest();
		req.setTokenTimestamp(System.currentTimeMillis());
		req.setOpenAccountId(4398047494521L);
		req.setIsvAccountId("hitlacus");
		req.setUuid("vcghvjhgiuyhuyj");
		req.setLoginStateExpireIn(360000L);
		OpenAccountTokenApplyResponse response = client.execute(req);
		
		System.out.println("===testApplyToken");
		System.out.println("- - - - - - ");
		System.out.println(response.getMsg());
		System.out.println(response.getBody());
		System.out.println("- - - - - - ");
	}
	
	
	//@Test
	public void testValidToken() throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		OpenAccountTokenValidateRequest req = new OpenAccountTokenValidateRequest();
		req.setParamToken("iEY3pJOxZOTBxMu6fKLkQFK1D3LLNAYgoUjrJrjhqVCj9fuAMwuigcEVE1IuWujxSnDE2VaF2vlM6D+waAu6Cvjy9MknSAhfvB+xYHpUuzyPPe7ve/vo7S92FOjyBSACllTETV3WEg9xruArw6hCC6HftcNiHxn+e89mqDfd391OA0etnzMSfu0XpnBW1foN/VSN2T7bOuxFbGrB5zh8uShhmydu6dqyMTF5IupeMfvTgXS0Zx9HF4Sz6GY7a3+E9OSsxXoFnlJgZ51CRmyp1OOU4nURLLZTDXVDTX3V5pyEMlBxf1Cq88TCqDx2mO5wx5nESmvMM2sdTwSQNZnciI7BQRCxBOVL/e/RegHB+bsmKpndmglb22UBKC3nw+79sOL85iDGHQrl08qK6Bc492z4srU4ImT8PZS0+ZG4c7k=");
		OpenAccountTokenValidateResponse response = client.execute(req);
		

		System.out.println("===testValidToken");
		System.out.println("- - - - - - ");
		System.out.println(response.getMsg());
		System.out.println(response.getBody());
		System.out.println("- - - - - - ");
	}
	
	
	//@Test
	public void testListUser() throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		OpenAccountListRequest req = new OpenAccountListRequest();
		req.setOpenAccountIds("4398047486972");
		//req.setIsvAccountIds("dasasdasf");
		OpenAccountListResponse response = client.execute(req);
		
		System.out.println("===testValidToken");
		System.out.println("- - - - - - ");
		System.out.println(response.getMsg());
		System.out.println(response.getBody());
		System.out.println("- - - - - - ");
		
	}
	
	@Test
	public void test() throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		OpenAccountUpdateRequest req = new OpenAccountUpdateRequest();
		List<OpenAccount> list116729 = new ArrayList<OpenAccount>();
		OpenAccount obj116729 = new OpenAccount();
		obj116729.setCreateDeviceId("nimeidenimeidenimeidenimeide");
		//obj116729.setLoginId("baymax");
		obj116729.setId(4398047486972l);
		obj116729.setAvatarUrl("http://image.abc.com/nimeide.jpg");
		//4398047486972
		/*
		
		obj116729.setAlipayId("baymax@126.com");
		obj116729.setLocale("zh_CN");
		obj116729.setBankCardNo("1234 7655 28763 211");
		//obj116729.setIsvAccountId("19862");
		//obj116729.setEmail("abc@126.com");
		obj116729.setAvatarUrl("http://image.abc.com/aa1232444444.jpg");
		obj116729.setBankCardOwnerName("baymax");
		obj116729.setDisplayName("baymax");
		obj116729.setLoginPwdSalt("WjndM");
		obj116729.setLoginPwd("xmbuioyfhkuijaamdkoohndmadjidhjifjd");
		//obj116729.setOpenId("xhdjeiyenmkljagjud");
		obj116729.setMobile("17084836002");
		obj116729.setCreateLocation("120.146484,30.313617");
		obj116729.setExtInfos("{\"gender\":\"male\"}");
		obj116729.setLoginPwdIntensity(1L);
		//obj116729.setId(123L);
		obj116729.setType(2L);
		obj116729.setStatus(1L);
		obj116729.setLoginPwdEncryption(1L);
		obj116729.setGender(1L);
		obj116729.setName("baymax");
		obj116729.setBirthday("20150120");
		obj116729.setWangwang("baymax");
		obj116729.setWeixin("1822939822");
		obj116729.setOauthPlateform(1L);*/
		list116729.add(obj116729);
		req.setParamList(list116729);
		OpenAccountUpdateResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
	}
	
	
	@Test
	public void test1() throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		OpenAccountListRequest req = new OpenAccountListRequest();
		req.setOpenAccountIds("4398047486972,4398047489999");
		//req.setIsvAccountIds("123,456");
		OpenAccountListResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
		
	}
	

}
