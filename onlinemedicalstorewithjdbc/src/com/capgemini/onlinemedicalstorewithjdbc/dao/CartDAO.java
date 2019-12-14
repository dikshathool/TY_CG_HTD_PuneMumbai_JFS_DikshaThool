package com.capgemini.onlinemedicalstorewithjdbc.dao;

import java.util.List;

import com.capgemini.onlinemedicalstorewithjdbc.bean.CartBean;

public interface CartDAO {
	public boolean addIntoCart(int userId, int productId);
	List<CartBean> showCartProducts();
	public boolean removeCartProduct(int userId, int cartId);
	public double totalBill(int userId);
	public boolean deleteCart(int userId);
}
