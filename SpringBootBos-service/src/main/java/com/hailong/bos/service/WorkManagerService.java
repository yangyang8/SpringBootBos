package com.hailong.bos.service;

import javax.servlet.http.HttpServletResponse;

import com.hailong.bos.domain.WorkManager;
import com.hailong.bos.domain.json.JsonUtils;

/**
 * 工单的服务器层的操作
 * @author Administrator
 *
 */
public interface WorkManagerService {
	public boolean saveOrUpdate(WorkManager workManager);
	
	public WorkManager getWorkMangerServiceById(String id);
	
	//进行分页查询操作
	public JsonUtils<WorkManager> pageList(Integer page,Integer rows,HttpServletResponse response);
}
