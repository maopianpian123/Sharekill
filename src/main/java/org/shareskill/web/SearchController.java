/**
 * 2016年3月5日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.shareskill.dao.SkillDao;
import org.shareskill.pojo.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hit_lacus@126.com
 */
@RestController
@RequestMapping(value="/api")
public class SearchController {
	
	
	private static Logger log = Logger.getLogger(SearchController.class);
	
	@Autowired
	private SkillDao skilldao;
	
	/**
	 * 
	 * @param tokenRsa
	 * @param tokenPlain
	 * @param signature
	 * @param keyword
	 * @param start
	 * @param count
	 * @return
	 */
	@RequestMapping(value="/search",method=GET,produces="application/json")
	public Map<String,Object> search(
			@RequestParam("tokenrsa") String tokenRsa,
			@RequestParam("tokenplain") String tokenPlain,
			@RequestParam("signature") String signature,
			@RequestParam("keyword") String keyword,
			@RequestParam("start") int start,
			@RequestParam("count") int count,
			@RequestParam("tag") String tag
			){
		
		
		
		Map<String,Object> result = new HashMap<>();
		
		List<Skill> data = skilldao.searchSkill(start, count, tag, keyword);
		
		log.info(data);	
		
		if(data.size() == count){
			result.put("status", "");
			result.put("reason", "");
			result.put("data", data);
		}else{
			result.put("status", "");
			result.put("reason", "");
			result.put("data", data);
		}
		
		
		
		return result;
	}
}
