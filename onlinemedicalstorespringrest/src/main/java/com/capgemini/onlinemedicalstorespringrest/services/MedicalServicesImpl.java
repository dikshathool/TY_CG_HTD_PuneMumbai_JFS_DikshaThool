package com.capgemini.onlinemedicalstorespringrest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.onlinemedicalstorespringrest.beans.AdminBean;
import com.capgemini.onlinemedicalstorespringrest.beans.UserBean;
import com.capgemini.onlinemedicalstorespringrest.dao.MedicalDAO;

@Service
public class MedicalServicesImpl implements MedicalServices {
	
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
	public boolean registerUser(UserBean userBean) {
		return medicalDAO.registerUser(userBean);
	}

	@Override
	public List<UserBean> showAllUsers() {
		return medicalDAO.showAllUsers();
	}

	@Override
	public UserBean getUser(int userId) {
		return medicalDAO.getUser(userId);
	}

	@Override
	public boolean removeUser(int userId) {
		return medicalDAO.removeUser(userId);
	}

	@Override
	public boolean updateUser(UserBean userBean) {
		return medicalDAO.updateUser(userBean);
	}

}//End of Class
