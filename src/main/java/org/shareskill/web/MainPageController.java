/**
 * 2016年2月2日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.shareskill.authentication.RsaUtil;
import org.shareskill.authentication.TokenUtil;
import org.shareskill.authentication.ValidateResult;
import org.shareskill.dao.SkillDao;
import org.shareskill.dao.TagDao;
import org.shareskill.pojo.Skill;
import org.shareskill.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.OpenAccountTokenValidateRequest;
import com.taobao.api.response.OpenAccountTokenValidateResponse;

/**
 * @author hit_lacus@126.com
 */
@RestController
@RequestMapping(value="/api")
public class MainPageController {
	
	private static Logger log = Logger.getLogger(MainPageController.class);
	
	@Autowired
	private TagDao tagDao;
	
	@Autowired
	private SkillDao skillDao;
	
	
	private String appkey = "23311785";
	
	private String secret = "693874e4b8d34471e32be0ca561e2521";
	
	private String url = "http://gw.api.taobao.com/router/rest";
	
	
	
	
	@RequestMapping(value="/mainpage",method=GET,produces="application/json")
	public Map<String,Object> mainPage(
			@RequestParam("tokenrsa") String tokenRsa,
			@RequestParam("tokenplain") String tokenPlain
			) throws ApiException {
		//
		Map<String,Object> result = new HashMap<>();
		
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		OpenAccountTokenValidateRequest req = new OpenAccountTokenValidateRequest();
			
		try {
			String realToken = RsaUtil.getRealToken(tokenRsa) + tokenPlain;			
			result.put("realToken",realToken);
			log.info("realToken = " + realToken);
			
	        req.setParamToken(realToken);
        } catch (Exception e) {
	        e.printStackTrace();
        }
		OpenAccountTokenValidateResponse response = client.execute(req);
		
		log.info(response.getBody());	
		result.put("isUsccess",response.isSuccess());	
		result.put("responseBody",response.getBody());
		result.put("data",response.getData());
		result.put("errorcode",response.getErrorCode());
		return result;
	}
	
	
	
	@RequestMapping(value="/skilllist",method=GET,produces="application/json")
	public Map<String,Object> skillList(
			@RequestParam("tag") String tag,
			@RequestParam("start") int start,
			@RequestParam("count") int count,
			@RequestParam("sort") String sort,
			@RequestParam("token") String token
		){
		/*
		ValidateResult tokenRe = TokenUtil.validate(token);
		String username;
		if(tokenRe.isValid()){
			username = tokenRe.getData();
		}else{
			//TODO 返回用户不存在
			return null;
		}
		
		
		List<Tag> tags = tagDao.getAllTag();
		boolean tagFound = false;
		for(Tag t : tags){
			if(t.getName().equalsIgnoreCase(tag)){
				tagFound = true;
			}
		}
		if(!tagFound){
			//TODO 返回标签不存在
		}
		
		
		
		
		
		if(sort.equalsIgnoreCase("")){
			
		}else if(sort.equalsIgnoreCase("")){
			
		}else{
			
		}
		
		
		
		
		
		
		
		return null;*/
		return null;
	}
	
	
	/**
	 * <pre>
	 * 返回随机技能列表,目前设定一次请求数量必须为10
	 * 
	 * 总技能数:	allSkillCount = 1002
	 * 获取结果数:	count = 10
	 * 开始数:		start = 450
	 * 
	 * 因为1002/10 = 100
	 * 除去2个,分为10页,每页100个
	 * pageSize = allSkillCount/10 = 100
	 * 
	 * start必须是能被10整除的正整数,而且小于100*10-10=990
	 * start < (pageSize-1)*10
	 * 
	 * 
	 * 对于每次请求,固定从每个分页中取出指定序号的技能
	 * 序号的规则为当前时间距离1970年元旦的小时数加上start/count,再对pageSize取余
	 * 
	 * 0   + 450/10 + 3 =  48
	 * 100 + 450/10 + 3 = 148
	 * ......
	 * 
	 * </pre>
	 * @param allSkills
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Skill> randomSkill(List<Skill> allSkills,int start,int count){
		if(count!= 10) return null;
		int skillNum = allSkills.size();
		List<Skill> ranSkill = new ArrayList<>();
		int page = skillNum/count;		
		if(start > (page-1) * 10 && start%10 != 0){
			return null;
		}
		
		int randSeed = (int) ( (System.currentTimeMillis()%3_600_000 + start/count)%page);
		for(int i = 0; i < count; i ++){
			ranSkill.add(allSkills.get(i * page + randSeed));
		}
		
		return ranSkill;
	}
	
	
}
