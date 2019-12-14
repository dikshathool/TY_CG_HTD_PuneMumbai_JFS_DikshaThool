package com.capgemini.onlinemedicalstorewithjdbc.dao;

import java.util.List;

import com.capgemini.onlinemedicalstorewithjdbc.bean.AdminBean;
import com.capgemini.onlinemedicalstorewithjdbc.bean.UsersBean;

public interface MedicalDAO {
	public AdminBean adminLogin(String email, String password);
	public UsersBean userLogin(String email,String password);
	public boolean registerUser(UsersBean userBean);
	public List<UsersBean> showUsers();
	public boolean removeUser(int userId);
	public boolean updateUserPassword(long mobile, String password);
	public UsersBean getUser(int userId);
}
