package com.capgemini.onlinemedicalstorespringboot.dao;

import java.util.List;

import com.capgemini.onlinemedicalstorespringboot.beans.CartBean;

public interface CartDAO {
	public boolean addIntoCart(int userId, int productId);

	public boolean removeFromCart(int userId, int cartId);

	public List<CartBean> showAllProducts(int userId);

	public double totalBill(int userId);

	public boolean deleteCart(int userId);
}//End of interface
