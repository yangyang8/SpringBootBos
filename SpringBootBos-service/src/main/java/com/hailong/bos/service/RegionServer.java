package com.hailong.bos.service;

import java.util.List;


import com.hailong.bos.domain.BCRegion;
import com.hailong.bos.domain.json.JsonUtils;

public interface RegionServer {
	
	public JsonUtils<BCRegion> pageList(Integer rows,Integer pageSize);
	
	public void saveOrUpdate(BCRegion region);
	
	//实现批量的插入操作
	public void saveBatch(List<BCRegion> region);
	
	//删除操作
	public void deleteById(String ids);

}
