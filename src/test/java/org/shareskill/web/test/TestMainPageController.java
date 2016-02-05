/**
 * 2016年2月3日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.web.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * @author hit_lacus@126.com
 */
public class TestMainPageController {
	
	
	@Test
	public void testMainPage() throws ClientProtocolException, IOException{
		HttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet("http://localhost:8080/ShareSkill/api/mainpage.json");
		//request.
		HttpResponse response = client.execute(request);
		
		System.out.println(response.getStatusLine());
		System.out.println(EntityUtils.toString(response.getEntity(),"UTF-8"));
		
		
		
	}

}
