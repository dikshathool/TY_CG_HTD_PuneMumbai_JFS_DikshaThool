package com.capgemini.onlinemedicalstorespringmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
//It is an configuration class
@Configuration
public class MedicalConfig {
	@Bean
	public LocalEntityManagerFactoryBean getEMF() {
		LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("medicalPersistenceUnit");
		
		return factoryBean;
	}//End of getEMF()
}//End of config
