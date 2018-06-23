package com.hailong.bos.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 是通用持久层接口操作方法
 * @author Administrator
 *
 * @param <T>
 */

public interface IBaseDao<T> {
	
	//增加操作
	public void insert(T entity);
	//删除操作
	public void delete(T id);
	//修改操作
	public void update(T entity);
	
	//查找操作
	public T get(Serializable id);
	
	//查询全部的操作
	public List<T> find(Map map);

}
