package com.hailong.bos.service;

import java.util.List;

import com.hailong.bos.domain.BCDecidedzone;
import com.hailong.bos.domain.BCStaff;
import com.hailong.bos.domain.BCSubArea;
import com.hailong.bos.domain.json.JsonUtils;

public interface DecidedzoneService {
	//得到所有的Staff数据
	public List<BCStaff>  getStaffAll();
	
	public void saveOrUpdate(BCDecidedzone decidedzone,String subareaId[]);
	
	//得到所有的定区的数据
	public List<BCSubArea> getSubAreaAll();
	
	//进行得到分页数据
	public JsonUtils<BCDecidedzone> getPageList(Integer page,Integer rows);

	//查询我关联定区的数据
	public List<BCSubArea> findAssociationSubarea(String decidedzoneId);
	
	public BCDecidedzone findByDecidedzoneId(String decidedzoneId);
}
