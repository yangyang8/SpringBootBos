package com.hailong.bos.config.db;

import java.beans.PropertyVetoException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//表明是一个配置类

@Configuration
@EnableTransactionManagement  //事务的管理操作
@EnableCaching  //启动缓存功能
public class C3P0SecondaryDataSourceConfig {
	
	//进行自动的映射配置
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${activi.spring.datasource.username}")
	private String username;
	@Value("${activi.spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;
	
	@Bean("secondaryDataSource")
	public ComboPooledDataSource getC3P0DataSource(){
		ComboPooledDataSource dataSources=new ComboPooledDataSource();
		
		try {
			dataSources.setPassword(password);
			dataSources.setDriverClass(driverClassName);
			dataSources.setJdbcUrl(url);
			dataSources.setUser(username);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		return dataSources;
	}
	


}
