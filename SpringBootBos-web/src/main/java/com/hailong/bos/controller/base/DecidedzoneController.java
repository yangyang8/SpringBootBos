package com.hailong.bos.controller.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.endpoint.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.hailong.bos.domain.BCDecidedzone;
import com.hailong.bos.domain.BCStaff;
import com.hailong.bos.domain.BCSubArea;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.service.DecidedzoneService;
import com.hailong.crm.entity.Customer;


@RestController
@RequestMapping("/decidedzone")
public class DecidedzoneController {
	
	//进行得到数据
	@Autowired
	private DecidedzoneService decidedzoneService;
	
	//进行注入我们的远程代理的动态客户端的数据
	@Autowired
	private Client client;
	
	@RequestMapping("/getStaffs.action")
	public String getStaffs(){
		 List<BCStaff> blist=decidedzoneService.getStaffAll();
		 SerializeFilter filter=new SimplePropertyPreFilter(BCStaff.class,
				 new String[]{"id","username"});
		 String json=JSONArray.toJSONString(blist,filter);
		 return json;
	}
	
	//得到所有没有分区的数据
	@RequestMapping("/getSubareas.action")
	public String getSubareas(){
		List<BCSubArea> slist=decidedzoneService.getSubAreaAll();
		
		SerializeFilter filter=new SimplePropertyPreFilter(BCSubArea.class,
				 new String[]{"subareaId","addresskey","position"});
		 
		String j=JSONArray.toJSONString(slist,filter);
		
		return j;
	}
	
	@RequestMapping("/saveSubarea.action")
	public ModelAndView saveSubarea(String subareaId[],BCDecidedzone decidedzone){
		decidedzoneService.saveOrUpdate(decidedzone,subareaId);
		return new ModelAndView("base/decidedzone");
	}
	
	//分页数据
	@RequestMapping("/pageList.action")
	public void pageList(Integer page,Integer rows,HttpServletResponse response){
		JsonUtils<BCDecidedzone> j=decidedzoneService.getPageList(page, rows);
	    //定义一个filter
		SimplePropertyPreFilter filter=new SimplePropertyPreFilter(BCDecidedzone.class,new String[]{"id","username","staff"});
		//进行转换成分区的数据
		String js=JSON.toJSONString(j,filter);
		//进行写出数据
		try {
			response.getWriter().print(js);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//进行查询已经关联的数据
	@RequestMapping("/associationCustomer.action")
	public String associationCustomer(String decidedzoneId){
		try {
			Object[] obj=client.invoke("findCustomerIsAssociation",decidedzoneId);
			String json=JSONArray.toJSONString(obj);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//进行抽取出一个json的基本的数据
	//public SimplePropertyPreFilter filteJSONData()
	
	//进行查询没有关联的数据
	@RequestMapping("/withoutAssociationCustomer.action")
	public String withoutAssociationCustomer(){
		try {
			Object[] obj=client.invoke("findCustomerNoAssociation");
			SimplePropertyPreFilter filter=
					new SimplePropertyPreFilter(Customer.class,new String[]{"id","name","telephone"});
			String json=JSONArray.toJSONString(obj,filter);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/assigncustomerstodecidedzone.action")
	public ModelAndView assigncustomerstodecidedzone(Integer[] customerIds,String id){
		//System.out.println("///////////////");
		try {
			List<Integer> list=Arrays.asList(customerIds);
			client.invoke("saveAssociation",list,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("base/decidedzone");
	}
	
	@RequestMapping("/getAssociationCustomer.action")
	public String getAssociationCustomer(String decidedzoneId){
		try {
			Object[] obj=client.invoke("findCustomerIsAssociation",decidedzoneId);
			//
			//int a=;
			//创建一个数组
			List<Object> list=(List<Object>) obj[0];
			String json=JSONArray.toJSONString(list);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/getAssociationSubarea.action")
	public String getAssociationSubarea(String decidedzoneId){
		
		List<BCSubArea> list=decidedzoneService.findAssociationSubarea(decidedzoneId);
		if(list!=null&&list.size()>0){
			 //定义一个filter
			SimplePropertyPreFilter filter=new SimplePropertyPreFilter(BCDecidedzone.class,
					new String[]{"id","username","addresskey","startnum","endnum","single","position","region"});
			//进行转换成分区的数据
			String js=JSON.toJSONString(list,filter);
			return js;
		}
		return null;
	}
}
