package com.capgemini.onlinemedicalstorespringboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.onlinemedicalstorespringboot.beans.AdminBean;
import com.capgemini.onlinemedicalstorespringboot.beans.UsersBean;
import com.capgemini.onlinemedicalstorespringboot.dao.MedicalDAO;

@Service
public class MedicalServicesImpl implements MedicalServices {
	
	@Autowired
	private MedicalDAO medicalDAO;

	@Override
	public boolean userRegistration(UsersBean userBean) {
		return medicalDAO.userRegistration(userBean);
	}

	@Override
	public List<UsersBean> showAllUsers() {
		return medicalDAO.showAllUsers();
	}

	@Override
	public UsersBean getUser(int userId) {
		return medicalDAO.getUser(userId);
	}

	@Override
	public boolean removeUser(int userId) {
		return medicalDAO.removeUser(userId);
	}

	@Override
	public boolean updateUser(UsersBean userBean) {
		return medicalDAO.updateUser(userBean);
	}

	@Override
	public UsersBean usersLogin(String email, String password) {
		return medicalDAO.usersLogin(email, password);
	}

}//End of Class
