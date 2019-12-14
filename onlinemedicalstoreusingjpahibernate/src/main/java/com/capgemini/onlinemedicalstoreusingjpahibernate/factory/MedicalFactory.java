package com.capgemini.onlinemedicalstoreusingjpahibernate.factory;

import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.CartDAO;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.CartDAOJpaHibernateImpl;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.MedicalDAO;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.MedicalDAOJpaHibernateImpl;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.MessageDAO;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.MessageDAOJpaHibernateImpl;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.ProductDAO;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.ProductDAOJpaHibernateImpl;
import com.capgemini.onlinemedicalstoreusingjpahibernate.validations.MedicalValidations;
import com.capgemini.onlinemedicalstoreusingjpahibernate.validations.MedicalValidationsImpl;

public class MedicalFactory {
private MedicalFactory() {
		
	}
	public static MedicalDAO getDAOImplInstance() {
		MedicalDAO medicalDAO=new MedicalDAOJpaHibernateImpl();
		return medicalDAO;
	}
	
	public static ProductDAO getDAOProductImplInstance() {
		ProductDAO productDAO = new ProductDAOJpaHibernateImpl();
		return productDAO;
	}
	
	public static CartDAO getDAOCartImplInstance() {
		CartDAO cartDAO = new CartDAOJpaHibernateImpl();
		return cartDAO;
	}
	
	public static MessageDAO getDAOMessageImplInstance() {
		MessageDAO messageDAO = new MessageDAOJpaHibernateImpl();
		return messageDAO;
	}
	
	public static MedicalValidations getMedicalValidations() {
		MedicalValidations medicalValidations = new MedicalValidationsImpl();
		return medicalValidations;
	}
	
}//End of class
