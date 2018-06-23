package com.hailong.bos.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hailong.bos.domain.User;


/**
 * 和Session相关的工具类的方法
 * @author Administrator
 *
 */
public class SessionUtils {
	
	//获取Session对象
	public static HttpSession getSession(HttpServletRequest request){
		HttpSession session=request.getSession(true);
		if(session.isNew()){
			return null;
		}
		return session;
	}
	
	//获取登录对象
	public static User getLoginUser(HttpServletRequest request){
		HttpSession session=getSession(request);
		return (User) session.getAttribute("currentUser");
	}
}
