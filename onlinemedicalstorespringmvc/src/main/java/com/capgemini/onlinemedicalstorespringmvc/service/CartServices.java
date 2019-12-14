package com.capgemini.onlinemedicalstorespringmvc.service;

import java.util.List;

import com.capgemini.onlinemedicalstorespringmvc.beans.CartBean;

public interface CartServices {
	public boolean addIntoCart(int userId, int productId);

	public boolean removeFromCart(int cartId);

	public List<CartBean> showAllProducts();

	public double totalBill(int userId);

	public boolean deleteCart(int userId);
}//End of interface
