package com.capgemini.onlinemedicalstorespringrest.dao;

import java.util.List;
import com.capgemini.onlinemedicalstorespringrest.beans.AdminBean;
import com.capgemini.onlinemedicalstorespringrest.beans.UserBean;

public interface MedicalDAO {
	public AdminBean adminLogin(String email, String password);

	public UserBean userLogin(String email, String password);

	public boolean registerUser(UserBean userBean);

	public List<UserBean> showAllUsers();

	public UserBean getUser(int userId);

	public boolean removeUser(int userId);

	boolean updateUser(UserBean userBean);
}//End of Interface
