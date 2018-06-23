package com.hailong.bos.controller.sheduler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.hailong.bos.domain.NoticeBill;
import com.hailong.bos.domain.User;
import com.hailong.bos.service.NoticeBillService;
import com.hailong.bos.utils.SessionUtils;

/**
 * 知道单的基本的操作
 * 
 * 流程 =》 通知单==》工单==》工单业务单
 * @author Administrator
 */

@RestController
@RequestMapping("/noticeBill")
public class NoticeBillController {

	//注入通知单的服务对象
	@Autowired
	private NoticeBillService noticeBillService;
	
	/**
	 * 跳转到增加的页面
	 * @return
	 */
	@RequestMapping("/addNoticeBill.action")
	public ModelAndView addNoticeBill(){
		return new ModelAndView("qupai/noticebill_add");
	}
	
	
	//进行异步的校验相关的操作
	@RequestMapping("/ajaxCheckCustomerTelephone.action")
	public String ajaxCheckCustomerTelephone(String telephone){
		Object[] object=(Object[]) noticeBillService.CheckCustomerTelephone(telephone);
		Object o=object[0];
		String js=JSONArray.toJSONString(o);
		return js;
	}
	
	//进行根据我们的前端输入的地址来进行自动分单的基本的操作
	@RequestMapping("/autoSaveDivierOrder.action")
	public ModelAndView autoSaveDivierOrder(NoticeBill noticeBill,HttpServletRequest request){
		//设置当前的用户
		User loginUser=SessionUtils.getLoginUser(request);
		noticeBill.setUsers(loginUser);
		noticeBillService.autoDivierOrder(noticeBill);
		return new ModelAndView("qupai/noticebill_add");
	}
	
	//进行分查询操作
}
