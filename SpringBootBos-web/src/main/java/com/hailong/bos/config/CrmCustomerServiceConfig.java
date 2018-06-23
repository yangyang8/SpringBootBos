package com.hailong.bos.config;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 进行注入远程的客户关系数据
 * @author Administrator
 *
 */
@Configuration
public class CrmCustomerServiceConfig {
	
	/**
	 * 注入我们的远程的客户的代理对象
	 * @return
	 */
	@Bean
	public Client getCustomerProxy(){
		JaxWsDynamicClientFactory jax=JaxWsDynamicClientFactory.newInstance();
		Client cilent=jax.createClient("http://192.168.213.1:8080/service/findAllCustomer?wsdl");
		return cilent;
	}

}
