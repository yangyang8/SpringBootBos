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
//@ConfigurationProperties(prefix="spring.datasource")
//@ImportResource(locations="classpath:application-db.properties") 是用来引入xml文件的
//@ImportResource
//@PropertySource("classpath:application-db.properties")  //上面的二个都是用来引入xml配置文件的，只有这个才是引入propreties文件
@EnableTransactionManagement  //事务的管理操作
@EnableCaching  //启动缓存功能
public class C3P0DataSourceConfig {
	
	//进行自动的映射配置
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;
	
	@Primary
	@Bean("primaryDataSource")
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
