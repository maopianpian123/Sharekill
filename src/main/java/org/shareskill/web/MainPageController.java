/**
 * 2016年2月2日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.shareskill.dao.SkillDao;
import org.shareskill.dao.TagDao;
import org.shareskill.pojo.Skill;
import org.shareskill.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hit_lacus@126.com
 */
@RestController
@RequestMapping(value="/api")
public class MainPageController {
	
	@Autowired
	private TagDao tagDao;
	
	@Autowired
	private SkillDao skillDao;
	
	@RequestMapping(value="/mainpage",method=GET,produces="application/json")
	public Map<String,Object> mainPage() {
		List<Tag> tags = tagDao.getAllTag();
		Map<String,Object> result = new HashMap<>();
		result.put("tags", tags);
		
		List<Skill> skills = skillDao.getAllSkill();
		result.put("skills", randomSkill(skills));
		return result;
	}
	
	
	public List<Skill> randomSkill(List<Skill> skills){
		int count = skills.size();
		Set<Integer> rands = new HashSet<>();
		Random random = new Random(System.currentTimeMillis());
		
		while(rands.size() < 10){
			rands.add(random.nextInt(count));
		}
		
		
		List<Skill> ranSkill = new ArrayList<>();
		for(Integer id : rands){
			ranSkill.add(skills.get(id));
		}
		return ranSkill;
	}
	
	
}
