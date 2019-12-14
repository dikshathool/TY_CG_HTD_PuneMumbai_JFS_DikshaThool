package com.capgemini.onlinemedicalstorespringboot.dao;

import java.util.List;

import com.capgemini.onlinemedicalstorespringboot.beans.UsersBean;

public interface MedicalDAO {

	public boolean userRegistration(UsersBean userBean);
	
	public List<UsersBean> showAllUsers();

	public UsersBean getUser(int userId);

	public boolean removeUser(int userId);

	boolean updateUser(UsersBean userBean);

	public UsersBean usersLogin(String email, String password);
}// End of Interface
