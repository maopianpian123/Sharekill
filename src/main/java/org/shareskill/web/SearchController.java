/**
 * 2016年3月5日
 * https://git.oschina.net/hit_lacus/ShareSkill
 */
package org.shareskill.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.shareskill.dao.SkillDao;
import org.shareskill.pojo.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	
	
	@RequestMapping(value="/download",method=GET)
	public void download(HttpServletResponse res) throws IOException{
		
		
		OutputStream os = res.getOutputStream();  
	    try {  
	        res.reset();  
	        res.setHeader("Content-Disposition", "attachment; filename=dict.txt");  
	        res.setContentType("application/octet-stream; charset=utf-8");  
	        os.write(FileUtils.readFileToByteArray(new File("C:/Users/Administrator/Desktop/temp/test.jpg")));  
	        os.flush();  
	    } finally {  
	        if (os != null) {  
	            os.close();  
	        }  
	    } 
		
		
		
	}
	
	@RequestMapping(value="/upload",method=POST)
	public String upload(
			@RequestPart("uploadfile") byte[] uploadfile,
			Errors errors
			) {
		//log.info("您上传了" + uploadfile.getSize());
		//log.info("您上传了" + uploadfile.getOriginalFilename());
		File local = new File("F:/test.jpg");
		
		if(!local.getParentFile().exists()) {  
            //如果目标文件所在的目录不存在，则创建父目录  
            System.out.println("目标文件所在目录不存在，准备创建它！");  
            if(!local.getParentFile().mkdirs()) {  
                System.out.println("创建目标文件所在目录失败！");  
            }  
        }  
		FileOutputStream fos; 
		
		
		if(!local.exists()){
			try {
	            local.createNewFile();
            } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
            }
		}
		
		
        try {
	        fos = new FileOutputStream(local);
	        fos.write(uploadfile);
			fos.close();
        } catch (FileNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		

		return "redirect:/";
	}
	
	
	
}
