import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * 2016年3月1日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */

/**
 * @author hit_lacus@126.com
 */
public class Md5Test {
	
	@Test
	public void test() throws IOException{
		String md5 = DigestUtils.md5Hex(IOUtils.toByteArray("yxx"));
		System.out.println(md5);
	}

}
