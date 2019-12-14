package com.capgemini.onlinemedicalstoreusingjpahibernate.dao;

import java.util.List;

import com.capgemini.onlinemedicalstoreusingjpahibernate.beans.UsersBean;

public interface MedicalDAO {
	
	public UsersBean userLogin(String email, String password);

	public boolean registerUser(UsersBean userBean);

	public List<UsersBean> showAllUsers();

	public UsersBean getUser(int userId);

	public boolean removeUser(int userId);

	boolean updateUserPassword(long mobile, String password);
}//End of MedicalDAO 
