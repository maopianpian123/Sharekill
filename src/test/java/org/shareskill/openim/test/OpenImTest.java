/**
 * 2016年2月19日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.openim.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.ImMsg;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.CloudpushMessageIosRequest;
import com.taobao.api.request.CloudpushNoticeIosRequest;
import com.taobao.api.request.OpenimImmsgPushRequest;
import com.taobao.api.request.OpenimUsersAddRequest;
import com.taobao.api.request.OpenimUsersDeleteRequest;
import com.taobao.api.request.OpenimUsersGetRequest;
import com.taobao.api.response.CloudpushMessageIosResponse;
import com.taobao.api.response.CloudpushNoticeIosResponse;
import com.taobao.api.response.OpenimImmsgPushResponse;
import com.taobao.api.response.OpenimUsersAddResponse;
import com.taobao.api.response.OpenimUsersDeleteResponse;
import com.taobao.api.response.OpenimUsersGetResponse;

/**
 * @author hit_lacus@126.com
 */
public class OpenImTest {
	
	public OpenImTest(){
		
		for(int i = 1; i <= 10; i++){
			Userinfos user = new Userinfos();
			user.setName("user");
			user.setUserid("user" + i);
			user.setAge((long) (12 + i));
			user.setPassword("123456");
			userInfoList.add(user);
		}
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 2; i <= 10; i++){
			sb.append("user" + i);
			userList.add("user" + i );
		}
		userids = sb.toString().substring(0, sb.length()-2);
		
	}
	
	private List<Userinfos> userInfoList = new ArrayList<>();
	
	private String userids = null;
	
	private List<String> userList = new ArrayList<>();
	
	private String appkey = "23311785";
	
	private String secret = "693874e4b8d34471e32be0ca561e2521";
	
	private String url = "http://gw.api.taobao.com/router/rest";
	
	
	//@Test
	public void testAddUser() throws ApiException{	
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		OpenimUsersAddRequest req = new OpenimUsersAddRequest();
		
		
		req.setUserinfos(userInfoList);
		OpenimUsersAddResponse response = client.execute(req);
		System.out.println("=== testAddUser");
		System.out.println("- - - - - - ");
		System.out.println(response.getMsg());
		System.out.println(response.getBody());
		/*for(String str: response.getUidSucc()){
			System.out.println(str);
		}*/
		System.out.println("- - - - - - ");
	}
	
	@Test
	public void testShowUser() throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		OpenimUsersGetRequest req = new OpenimUsersGetRequest();
		req.setUserids(userids);
		OpenimUsersGetResponse response = client.execute(req);
		System.out.println("=== testShowUser");
		System.out.println("- - - - - - ");
		System.out.println(response.getMsg());
		System.out.println(response.getBody());
		System.out.println("- - - - - - ");
	}
	
	//@Test
	public void testDeleteUser() throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		OpenimUsersDeleteRequest req = new OpenimUsersDeleteRequest();
		req.setUserids(userids);
		OpenimUsersDeleteResponse response = client.execute(req);
		System.out.println("=== testDeleteUser");
		System.out.println("- - - - - - ");
		System.out.println(response.getMsg());
		System.out.println(response.getBody());
		System.out.println("- - - - - - ");
	}
	
	@Test
	public void testMessage() throws ApiException{
		
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		OpenimImmsgPushRequest req = new OpenimImmsgPushRequest();
		
		ImMsg obj117189 = new ImMsg();
		obj117189.setFromUser("user1");
		obj117189.setToUsers(userList);
		obj117189.setMsgType(0L);
		obj117189.setContext("这个一个文本消息");
		obj117189.setToAppkey("0");
		obj117189.setMediaAttr("{\"type\":\"amr\",\"playtime\":6}");
		
		req.setImmsg(obj117189);
		OpenimImmsgPushResponse response = client.execute(req);
		System.out.println("=== testMessage");
		System.out.println("- - - - - - ");
		System.out.println(response.getMsg());
		System.out.println(response.getBody());
		System.out.println("- - - - - - ");
	}
	
	@Test
	public void testPushMessage() throws ApiException{
		
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		CloudpushMessageIosRequest req = new CloudpushMessageIosRequest();
		req.setBody("这是一个推送消息,来自享技APP.");
		req.setTarget("all");
		req.setTargetValue("all");
		CloudpushMessageIosResponse response = client.execute(req);
		System.out.println("=== testPushMessage");
		System.out.println("- - - - - - ");
		System.out.println(response.getMsg());
		System.out.println(response.getBody());
		System.out.println("- - - - - - ");	
		
	}
	
	@Test
	public void testPushNotice(){
		
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        CloudpushNoticeIosRequest req=new CloudpushNoticeIosRequest();
        req.setSummary("这是一个推送通知,来自享技APP.");
        req.setTarget("device");
        req.setTargetValue("5bf72828f222445fa03db60de7acc59e");
        req.setEnv("product");
        req.setExt("{\"badge\":1,\"sound\":\"xxxx\"}");
        try {
            CloudpushNoticeIosResponse response = client.execute(req);
            System.out.println("=== testPushNotice");
            System.out.println("- - - - - - ");
    		System.out.println(response.getMsg());
    		System.out.println(response.getBody());
    		System.out.println("- - - - - - ");
            if(response.isSuccess()){
                System.out.println("push  notice is success!");
            }
        }
        catch (Exception e){
            System.out.println("push notice is error!");
        }
	}
	
	
	
	

}
