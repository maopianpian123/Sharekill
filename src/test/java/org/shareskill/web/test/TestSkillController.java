package org.shareskill.web.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestSkillController {

	@Test
	public void testSkill() throws ClientProtocolException, IOException{
		HttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost("http://127.0.0.1:8080/ShareSkill"
				+ "/api/skilldelete.json?skillId=21");
		
		HttpResponse response = client.execute(request);
		
		System.out.println(response.getStatusLine());
		System.out.println(EntityUtils.toString(response.getEntity(),"UTF-8"));
	}

}
