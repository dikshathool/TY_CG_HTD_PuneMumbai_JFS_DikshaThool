package com.capgemini.onlinemedicalstorespringmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.onlinemedicalstorespringmvc.beans.AdminBean;
import com.capgemini.onlinemedicalstorespringmvc.beans.ProductBean;
import com.capgemini.onlinemedicalstorespringmvc.beans.UserBean;
import com.capgemini.onlinemedicalstorespringmvc.dao.MedicalDAO;
//It is an service class hence used @Service
@Service
public class MedicalServiceImpl implements MedicalService {
	
	@Autowired
	private MedicalDAO medicalDAO;
	
	@Override
	public AdminBean adminLogin(String email, String password) {
		return medicalDAO.adminLogin(email, password);
	}

	@Override
	public UserBean userLogin(String email, String password) {
		return medicalDAO.userLogin(email, password);
	}

	@Override
	public boolean userRegister(UserBean userBean) {
		return medicalDAO.userRegister(userBean);
	}

	@Override
	public List<ProductBean> getAllProducts() {
		return medicalDAO.getAllProducts();
	}

	@Override
	public List<UserBean> getAllUsers() {
		return medicalDAO.getAllUsers();
	}

	@Override
	public boolean removeUser(int userId) {
		return medicalDAO.removeUser(userId);
	}

}//End of service class
