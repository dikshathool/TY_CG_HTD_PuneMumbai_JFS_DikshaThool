package com.capgemini.onlinemedicalstoreusingjpahibernate.dao;

import java.util.List;

import com.capgemini.onlinemedicalstoreusingjpahibernate.beans.CartBean;

public interface CartDAO {
	public boolean addIntoCart(int userId, int productId);

	public boolean removeFromCart(int userId, int cartId);

	public List<CartBean> showAllProducts();

	public double totalBill(int userId);

	public boolean deleteCart(int userId);
}// end of interface
