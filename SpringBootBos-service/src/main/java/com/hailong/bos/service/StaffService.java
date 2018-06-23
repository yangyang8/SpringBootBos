package com.hailong.bos.service;

import java.util.List;

import com.hailong.bos.domain.BCStaff;
import com.hailong.bos.domain.BCStandard;
import com.hailong.bos.domain.json.JsonUtils;

public interface StaffService {
	//保存快递员的方法
	public BCStaff save(BCStaff staff,String sid);
	//查询所有快递员的列表
	public List<BCStaff> list();
	
	//根据分页来查询快递员的数据
	public JsonUtils pageList(Integer startIndex, Integer pageSize);
	
	//根据Id来逻辑删除我们的快递员的数据，也就是把deltag设置成1,1表示为已删除(逻辑)
	public BCStaff operatorById(String ids,String deltag);
	
	//修改快递员的数据
	//1,封装成一个新的对象 ，有点麻烦
	//2,进行再查询一次数据,选择第二种情况 
	//3,根据自己写一个sql语句来做
	public BCStaff update(BCStaff staff,String sid);
	
	//得到所有的标准表当中的数据
	public List<BCStandard> getStandards();
}
