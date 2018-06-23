/*package com.hailong.bos.config;

import javax.sql.DataSource;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ActivitiConfig {
  
	  @Autowired  
	  @Qualifier("secondaryDataSource")  
	  private DataSource secondaryDataSource;
	  
	  @Autowired  
	  @Qualifier("secondaryTransactionManager")
	  private PlatformTransactionManager transactionManager;
    
    @Bean
    public SpringProcessEngineConfiguration getSpringProcessEngineConfiguration(){
    	SpringProcessEngineConfiguration springProcessEngineConfiguration=new SpringProcessEngineConfiguration();
    	springProcessEngineConfiguration.setDataSource(secondaryDataSource);
    	springProcessEngineConfiguration.setTransactionManager(transactionManager);
    	return springProcessEngineConfiguration;
    }
    
}*/
