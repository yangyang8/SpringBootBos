package com.hailong.bos.service;

import com.hailong.bos.domain.BCStandard;
import com.hailong.bos.domain.json.JsonUtils;

public interface StandardService {
	
	//增加方法
	public BCStandard saveStandard(BCStandard standard);
	//删除方法
	
	//修改方法
	public BCStandard update(BCStandard standard);
	//查询方法
	
	//查询所有方法
	public JsonUtils<BCStandard> findList(Integer startIndex,Integer pageSize);
	
	//进行逻辑删除数据
	public void deleteByLogic(String ids);
}
