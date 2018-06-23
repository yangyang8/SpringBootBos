package com.hailong.bos.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 配置了统一的异常处理操作
 * @author Administrator
 *
 */
@ControllerAdvice //表示一有相关的异常就会调用这个类的下面的这些方法
public class NoAuthorizedExceptionHandler {
	
	@ExceptionHandler(AuthorizationException.class)  //一检测到这个异常就调用这个方法
	public void handlerNoAuthorizedException(HttpServletResponse response){
		//进行重定向到没有权限的页面
		try {
			response.sendRedirect("/unauthorizedUrl.jsp");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	

}
