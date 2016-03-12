/**
 * 2016年2月20日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.jpush.test;

import org.junit.Test;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @author hit_lacus@126.com
 */
public class NoticeTest {
	
	private static final String appKey       = "a0620010dfff10ab00de1582" ;
	private static final String masterSecret = "989169ec40cd155ad7a3b093" ;
	
	@SuppressWarnings("deprecation")
    @Test
	public void test(){
		
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);

	    // For push, all you need do is to build PushPayload object.
	    PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId("101d855909453bad933","161a3797c8064b666a9"))
                .setNotification(Notification.alert("From hit_lacus@126.com .This message is sent to 101d855909453bad933 and 161a3797c8064b666a9."))
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                .build();
	    System.out.println("===" + payload.toString()+"===");
	    //payload.gson.
	    try {
	        PushResult result = jpushClient.sendPush(payload);
	        System.out.println("*:" + result);

	    } catch (APIConnectionException e) {
	        // Connection error, should retry later
	    	System.err.println("Connection error, should retry later"+e);

	    } catch (APIRequestException e) {
	        // Should review the error, and fix the request
	    	System.err.println("Should review the error, and fix the request"+ e);
	    	System.err.println("HTTP Status: " + e.getStatus());
	    	System.err.println("Error Code: " + e.getErrorCode());
	    	System.err.println("Error Message: " + e.getErrorMessage());
	    }
		
	}

}

//*:{"msg_id":3396022634,"sendno":1582263862}
//*:{"msg_id":1681992403,"sendno":2101945664}
