package com.hailong.bos.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 我们的登录的拦截器的操作,主要是要继承这个HandlerInterceptor接口，然后实现这个拦截器，最后把这个拦截器注入到Spring容器当中
 * 同时还要注册一个拦截器
 * @author Administrator
 *
 */
public class LoginInterceport implements HandlerInterceptor{

	//在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion()主要是在我们的一次请求完成之后进行拦截，同时这个方法主要是用来处理资源的回收清理工作的");
	}
	 //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("postHandle()这个方法在请求处理之后才执行,在视图渲染之前进行拦截执行操作==========");
	}

	 //在请求处理之前进行调用（Controller方法调用之前），也就是调用我们的请求方法之后先调用这个拦截器的方法 
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("拦截器执行了方法preHandle=================================");
		//return false;//如果false，停止流程(也就是停止在这里了，那里也动不了)，api被拦截
		return true;
	}

}
