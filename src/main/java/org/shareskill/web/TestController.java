/**
 * 2016年1月18日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author hit_lacus@126.com
 */
@Controller
@RequestMapping(value="/test")
public class TestController {
	
	@RequestMapping(value="/me", method=GET)
	public String test() {
		return "testpage";
	}

}
