/**
 * 2016年3月16日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.web.test;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
/**
 * @author hit_lacus@126.com
 */
public class MutilpartTest {
	
	
	
    @Test
	public void test() throws ClientProtocolException, IOException{
		
		HttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost("http://127.0.0.1:8080/ShareSkill"
				+ "/api/upload");
		FileBody bin = new FileBody(new File("F:/俞霄翔.jpg"));
		
		HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("uploadfile", bin)
                .build();


		request.setEntity(reqEntity);
		System.out.println("executing request " + request.getRequestLine());
        CloseableHttpResponse response = (CloseableHttpResponse) client.execute(request);
        try {
            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                System.out.println("Response content length: " + resEntity.getContentLength());
            }
            EntityUtils.consume(resEntity);
        } finally {
            response.close();
        }
		
        ((Closeable) client).close();
        
	}

}
