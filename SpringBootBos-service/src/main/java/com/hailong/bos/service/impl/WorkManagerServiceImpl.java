package com.hailong.bos.service.impl;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hailong.bos.dao.WorkManagerDao;
import com.hailong.bos.domain.BCSubArea;
import com.hailong.bos.domain.WorkManager;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.service.WorkManagerService;
import com.hailong.bos.utils.JSONUtils;

@Service
@Transactional
public class WorkManagerServiceImpl implements WorkManagerService{
	
	//注入Dao层的操作
	@Autowired
	private WorkManagerDao workManagerDao;
	
	//注入jdbc的模板方法
	//@Autowired
	//private JdbcTemplate template;

	
	public boolean saveOrUpdate(WorkManager workManager) {
		WorkManager wm=getWorkMangerServiceById(workManager.getId());
		boolean flag=true;
		//开启的缓存的话，那么则效率方面就没有多大的问题
		if(wm!=null){
			flag=false; //更新操作
		}else{
			workManagerDao.save(workManager);//如果不成功，这个数据不能变化
		}
		return flag;
	}

	public WorkManager getWorkMangerServiceById(String id) {
		WorkManager work=workManagerDao.findOne(id);
		return work;
	}

	@Override
	public JsonUtils<WorkManager> pageList(Integer page, Integer rows, HttpServletResponse response) {
		//进行查询有多少数据数
		long RecordNum=workManagerDao.count();
		//进行排序的操作
		//Sort sort=new Sort(Direction.DESC,"addresskey");
		//进行创建分页查询条件
		Pageable p=new PageRequest(page-1,rows);
		
		//进行分页查询操作
		Page<WorkManager> info=workManagerDao.findAll(p);
		
		JsonUtils<WorkManager> json=new JsonUtils<WorkManager>(RecordNum,info.getContent());
		
		return json;
		
	}
}
