/**
 * 2016年1月18日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.web.test;

import org.junit.Test;
import org.shareskill.web.TestController;
import org.springframework.test.web.servlet.MockMvc;

import static
org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static
org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static
org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * @author hit_lacus@126.com
 */
public class TestControllerTest {
	
	//@Test
	public void testTestController() throws Exception{
		TestController controller = new TestController();
		MockMvc mockMvc =
				standaloneSetup(controller).build();
		mockMvc.perform(get("/me"))
				.andExpect(view().name("test"));
	}

}
