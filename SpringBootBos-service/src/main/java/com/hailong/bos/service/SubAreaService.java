package com.hailong.bos.service;

import java.util.List;


import com.hailong.bos.domain.BCRegion;
import com.hailong.bos.domain.BCSubArea;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.domain.stat.StatSubarea;

public interface SubAreaService {
	
	public JsonUtils<BCSubArea> pageList(Integer page,Integer rows);
	
	public JsonUtils<BCSubArea> pageList(Integer page,Integer rows,BCSubArea subarea);
	
	public void saveOrUpdate(BCSubArea subarea);
	
	//删除操作
	public void deleteById(String ids);
	
	//得到区域的当中的所有的数据
	public List<BCRegion> getAllRegions(String q);
	
	//得到数据
	public List<BCSubArea> findAll();
	
	//根据省来查询所有的分区的数据分布情况
	public List<StatSubarea> getStatSubareaGroupByProvice();

}
