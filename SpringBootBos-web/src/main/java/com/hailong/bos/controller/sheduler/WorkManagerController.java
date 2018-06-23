package com.hailong.bos.controller.sheduler;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hailong.bos.domain.WorkManager;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.service.WorkManagerService;

/**
 * 工作单控件器的相关的操作
 * @author Administrator
 *
 */

@RestController
@RequestMapping("/workManager")
public class WorkManagerController {
	
	@Autowired
	private WorkManagerService workMangerService;
	
	@RequestMapping("/saveWorkManagerDate.action")//saveWorkBillDate
	public Integer saveWorkBillDate(WorkManager workManager,Model model){
		//String json="{success:";
		int json=1;
		try{
			 boolean flag=workMangerService.saveOrUpdate(workManager);
			 if(flag){
				 json=1; //插入成功..
			 }else{
				 //model.addAttribute("success",false);
				 json=0;//不成功，冲突
			 }
		}catch(Exception e){
			json=0;//不成功，冲突
		}
		 return json;
	}
	
	//通过ajax的方式来进行异步校验数据的Id是否已经存在,有点问题，因为我们的datagri没有事件
	@RequestMapping("/getIdByAjaxById.action")
    public String getIdByAjaxById(String id){
		WorkManager js=workMangerService.getWorkMangerServiceById(id);
		if(js==null){
			return null;
		}
		String j=JSON.toJSONString(js);
    	return j;
    }
	
	@RequestMapping("/pageListWorkManager.action")
	public String pageListWorkManager(Integer page,Integer rows,HttpServletResponse response){
		JsonUtils<WorkManager> json=workMangerService.pageList(page, rows, response);
		String js=JSONArray.toJSONString(json);
		return js;
	}
}
