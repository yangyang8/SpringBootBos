/*package com.hailong.bos.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hailong.bos.domain.User;
import com.hailong.bos.utils.SessionUtils;

*//**
 * 我们的登录的拦截器的操作,主要是要继承这个HandlerInterceptor接口，然后实现这个拦截器，最后把这个拦截器注入到Spring容器当中
 * 同时还要注册一个拦截器
 * @author Administrator
 *
 *//*
public class LoginInterceptor implements HandlerInterceptor{

	 //在请求处理之前进行调用（Controller方法调用之前），也就是调用我们的请求方法之后先调用这个拦截器的方法 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//System.out.println("拦截器执行了方法preHandle=================================");
		//return false;//如果false，停止流程(也就是停止在这里了，那里也动不了)，api被拦截
		//查看拦截了那些操作的方法
		System.out.println("拦截了那些请求:"+request.getRequestURI());
		//从Session当中获取登录用户数据
		//HttpSession session=request.getSession();
		//User user =(User) session.getAttribute("currentUser");
		User user =SessionUtils.getLoginUser(request);
		if(user!=null){
			return true;
		}else{
			//是在拦截之前使用重定向到登录页面操作
			System.out.println("尚未登录，调到登录页面");
			response.sendRedirect("/loginIn.action");
			return false;
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}*/
