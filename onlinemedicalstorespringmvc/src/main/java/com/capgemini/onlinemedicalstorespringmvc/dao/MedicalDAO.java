package com.capgemini.onlinemedicalstorespringmvc.dao;

import java.util.List;

import com.capgemini.onlinemedicalstorespringmvc.beans.AdminBean;
import com.capgemini.onlinemedicalstorespringmvc.beans.ProductBean;
import com.capgemini.onlinemedicalstorespringmvc.beans.UserBean;

public interface MedicalDAO {
	public AdminBean adminLogin(String email, String password);
	public UserBean userLogin(String email, String password);
	public boolean userRegister(UserBean userBean);
	public List<ProductBean> getAllProducts();
	
	//abstract methods after admin Login 
	public List<UserBean> getAllUsers();
	public boolean removeUser(int userId);
	
}//End of MedicalDAO interface
