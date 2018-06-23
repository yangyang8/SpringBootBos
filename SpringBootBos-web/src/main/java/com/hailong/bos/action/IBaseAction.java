package com.hailong.bos.action;
/*package www.hailong.bos.controller.action;

import java.lang.reflect.ParameterizedType;

import org.opensaml.profile.action.ActionSupport;

 //这个类主要是演示一下我们的通过泛型的类型来创建出对象


public class IBaseAction<T> implements ModelDriver<T> extends ActionSupport<T>{
		
		private T entity;
		
		//得到相应的Model
		private T getModel(){
			return Model;
		}
		
		public IBaseAction(){
			//先得到泛型接口类型
			ParameterizedType pType=(ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> entityClass=(Class<T>) pType.getActualTypeArguments()[0]; //得到泛型接口当中的参数，也就是<>当中的值
			//通过反射来创建出泛型对象
			entity=entityClass.newInstance();
		}

}
*/