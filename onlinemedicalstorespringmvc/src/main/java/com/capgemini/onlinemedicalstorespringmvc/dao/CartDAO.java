package com.capgemini.onlinemedicalstorespringmvc.dao;

import java.util.List;

import com.capgemini.onlinemedicalstorespringmvc.beans.CartBean;

public interface CartDAO {
	public boolean addIntoCart(int userId, int productId);

	public boolean removeFromCart(int cartId);

	public List<CartBean> showAllProducts();

	public double totalBill(int userId);

	public boolean deleteCart(int userId);

}//End of interface
