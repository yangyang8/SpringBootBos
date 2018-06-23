package com.hailong.bos.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hailong.bos.dao.RegionDao;
import com.hailong.bos.domain.BCRegion;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.service.RegionServer;

@Transactional
@Service
public class RegionServerImpl implements RegionServer{
	
	@Autowired
	private RegionDao regionDao;
	
	@Override
	public void saveOrUpdate(BCRegion region) {
		String id=region.getId();
		if(id!=null){
			//进行更新操作
			regionDao.save(region);
		}else{
			//进行保存操作
			//实现Id的操作
			long count=regionDao.count();
			id="QY";
			if(count>100){
				id+=count+1;
			}else{
				id+="0"+count+1;
			}
			region.setId(id);
			regionDao.save(region);//可以实现批量插入数据
		}

	}
	
	//进行删除操作，这个删除不是指的是逻辑删除了，而是物理删除了,批量的删除
	public void deleteById(String ids){
		if(ids!=null){
			//批量删除数据
			for(String id:ids.split(",")){
				regionDao.delete(id);
			}
		}else{
			//给用户一个提示id不能为null,其实这里应该封装一个java类，用来处理异常的情况
			System.out.println("id不能为Null");
		}

	}
	
	public void saveBatch(List<BCRegion> region) {
		regionDao.save(region);//可以实现批量插入或批量更新数据
	}


	@Override
	public JsonUtils<BCRegion> pageList(Integer rows, Integer pageSize) {
		//创建一个分页的查询的相关的对象
		Pageable page=new PageRequest(rows-1, pageSize);
		Page<BCRegion> p=regionDao.findAll(page);
		//创建对象进行封装
		JsonUtils<BCRegion> bcr=new JsonUtils<BCRegion>();
		//查询总记录数
		Long count=regionDao.count();
		bcr.setRows(p.getContent());
		bcr.setTotal(count);
		
		return bcr;
	}

}
