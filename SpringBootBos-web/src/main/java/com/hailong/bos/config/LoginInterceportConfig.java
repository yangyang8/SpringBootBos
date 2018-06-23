/*package com.hailong.bos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.hailong.bos.interceptor.LoginInterceptor;

@Configuration
//@EnableWebMvc  在spring boot当中不用加上这个配置，因为我们的spring boot当中默认就开始了spring mvc的基本配置，如果加上了就会出现问题
public class LoginInterceportConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public LoginInterceptor getLoginInterceptor(){
		return new LoginInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//除了/login外的所有的请求都要拦截
		registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/*").excludePathPatterns("/login","/loginIn.action");
	}
	
	
}*/
