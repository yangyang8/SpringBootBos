package com.hailong.bos.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hailong.bos.dao.StandardDao;
import com.hailong.bos.domain.BCStandard;
import com.hailong.bos.domain.json.JsonUtils;
import com.hailong.bos.service.StandardService;

@Transactional
@Service
public class StandardServiceImpl implements StandardService {

	@Autowired
	private StandardDao standardDao;
	
	public BCStandard saveStandard(BCStandard standard) {
		//进行设置当前的数据
		standard.setId(UUID.randomUUID().toString());
		standard.setOpDate(new Date());
		return standardDao.save(standard);
	}

	public JsonUtils<BCStandard> findList(Integer startIndex,Integer pageSize) {
		Pageable page=new PageRequest(startIndex-1, pageSize);
		JsonUtils<BCStandard> json=new JsonUtils<BCStandard>();
		Page pages=standardDao.findByDeltag("0",page);
		Long total=standardDao.count();
		json.setRows(pages.getContent());
		json.setTotal(total);
		return json;
	}

	@Override
	public BCStandard update(BCStandard standard) {
		BCStandard bcs=standardDao.findOne(standard.getId());
		bcs.setMaxWeight(standard.getMaxWeight());
		bcs.setMinWeight(standard.getMinWeight());
		bcs.setOpDate(standard.getOpDate());
		bcs.setName(standard.getName());
		
		return standardDao.save(bcs);
	}

	//进行逻辑删除数据
	public void deleteByLogic(String ids) {
		List<String> list=Arrays.asList(ids.split(","));
		standardDao.updateBatchById("1",list);
	}
}
