package com.hailong.bos.config;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.hailong.bos.realm.LoginRealm;


/**
 * 我们的shiro的配置类操作
 * @author Administrator
 *
 */

@Configuration
//@EnableCaching  //开启缓存
public class ShiroConfig {
	
	@Bean
	public Realm getRealm(){
		return new LoginRealm();
	}
	
	//使用ehcache的缓存
	/*@Bean
	public EhCacheCacheManager getCacheManager(){
		EhCacheManagerFactoryBean cacheBean=new EhCacheManagerFactoryBean();
		cacheBean.setConfigLocation(new ClassPathResource("ehcache/ehcache_test.xml"));
		cacheBean.setShared(true);
		EhCacheCacheManager ec=new EhCacheCacheManager();
		ec.setCacheManager(cacheBean.getObject());
		
		return ec;
	}*/
	
	/* @Bean
    public EhCacheManager ehCacheManager(){
       System.out.println("ShiroConfiguration.getEhCacheManager()");
       EhCacheManager cacheManager = new EhCacheManager();
       cacheManager.setCacheManagerConfigFile("classpath:ehcache/ehcache_shiro.xml");
       return cacheManager;
    }*/
	
	//进行注入我们的SecurityManager对象  
	@Bean
	public DefaultWebSecurityManager getSecurityManager(){
		DefaultWebSecurityManager manger=new DefaultWebSecurityManager();
		manger.setRealm(getRealm());
		//manger.setCacheManager(ehCacheManager());  TODO 这里的数据缓存有点问题
		return manger;
	}
	
	//注入我们的Shiro的相关拦截的过滤器操作的相关拦截规则

	
	//进行注入我们的ShiroFilterFactoryBean工厂对象
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager manager){
		ShiroFilterFactoryBean factoryBean=new ShiroFilterFactoryBean();
		
		factoryBean.setSuccessUrl("/index.jsp");
		factoryBean.setLoginUrl("/login.jsp");
		
		//factoryBean.setSuccessUrl("/index.jsp");
	    //factoryBean.setLoginUrl("/login.jsp");
		factoryBean.setUnauthorizedUrl("/unauthorizedUrl.jsp");
		factoryBean.setSecurityManager(manager);
		
		   //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        
        //增加要权限的相关操作
        filterChainDefinitionMap.put("/login.jsp*", "anon"); //表示可以匿名访问这个登录页面，也就是不用进行是否已登录认证
        filterChainDefinitionMap.put("/css/**", "anon"); 
        filterChainDefinitionMap.put("/easyUi/**", "anon"); 
        filterChainDefinitionMap.put("/images/**", "anon"); 
        filterChainDefinitionMap.put("/js/**", "anon"); 
        filterChainDefinitionMap.put("/json/**", "anon"); 
        filterChainDefinitionMap.put("/validatecode.jsp*", "anon"); 
        filterChainDefinitionMap.put("/loginIn.action", "anon");  //loginIn.action 表示的是跳转到登录页面
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/loginOut.action", "anon"); //注销 退出系统
        //"staff-list\" 一定要是字符串才可以，也就是只能用双引号，单引号不可以
        filterChainDefinitionMap.put("/page_base_staff.action", "perms[\"staff-list\"]"); //进行权限认证操作
        filterChainDefinitionMap.put("/**", "authc");//表示需要认证才可以访问  authc 过滤器主要是用来检测当前用户是否已经登录了
        //filterChainDefinitionMap.put("/", "authc"); 
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return factoryBean;
	}
	
	//配置开启我们的Shiro的方法注解的方式进行开发权限授权操作
	//开启方法注解支持权限开发，方法注解底层使用的是动态代理的方式，所以要配置一个自动代理类
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator proxy=new DefaultAdvisorAutoProxyCreator();
		//表示强制使用cglib的代理方式
		proxy.setProxyTargetClass(true);
		return proxy;
	}
	
	//同时配置一个权限授权的切面类(包括了通知+切点(也就是拦截表达式))
	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(){
		return new AuthorizationAttributeSourceAdvisor();
	}

}
