package com.hailong.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.hailong.bos.dao.base.IBaseDao;

/**
 * 通用的Dao实现层操作的方法实现操作，有二个难点
 * 1，是
 * @author Administrator
 *
 * @param <T>
 */


public class IBaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	//难点一:如何确定T的数据的类型
	private Class<T> entityClass;
	
	//难点二:通过通过注解的方式来注册SessesonFactory对象给HibernateDaoSupport对象
	
	
	//解决难点一:通过构造方法来初始化我们的entityClass对象
	public IBaseDaoImpl(){
		//得到泛型接口,也就是父类接口
		ParameterizedType paramType=(ParameterizedType) this.getClass().getGenericSuperclass();
		//得到泛型的参数类型T,也就是<>当中的内容
		entityClass=(Class<T>) paramType.getActualTypeArguments()[0];
	}
	
	// 解决难点二:通过手动给HibernateDaoSupport对象注入SessesonFactory
	@Resource  //当我们Spring的配置文件中配置了SessionFactory对象时，就会自动执行这个方法，
	public void setMySessionFactory(SessionFactory sessionFacatory){
		this.setSessionFactory(sessionFacatory);
	}
	
	@Override
	public void insert(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public T get(Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public List<T> find(Map map) {
		String queryString="from "+entityClass.getName();
		return (List<T>) this.getHibernateTemplate().find(queryString, map.values());
	}

}
