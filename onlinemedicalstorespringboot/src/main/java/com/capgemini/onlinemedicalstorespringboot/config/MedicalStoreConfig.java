package com.capgemini.onlinemedicalstorespringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@Configuration
public class MedicalStoreConfig {
	@Bean
	public LocalEntityManagerFactoryBean getEMF() {
		LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("medicalStorePersistence");
		
		return factoryBean;
	}// End of getEMF()
}//End of Class
