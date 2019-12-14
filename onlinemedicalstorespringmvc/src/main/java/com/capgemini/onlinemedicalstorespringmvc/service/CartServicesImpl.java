package com.capgemini.onlinemedicalstorespringmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.onlinemedicalstorespringmvc.beans.CartBean;
import com.capgemini.onlinemedicalstorespringmvc.dao.CartDAO;

@Service
public class CartServicesImpl implements CartServices {
	
	@Autowired
	private CartDAO cartDAO;

	@Override
	public boolean addIntoCart(int userId, int productId) {
		return cartDAO.addIntoCart(userId, productId);
	}

	@Override
	public boolean removeFromCart(int cartId) {
		return cartDAO.removeFromCart(cartId);
	}

	@Override
	public List<CartBean> showAllProducts() {
		return cartDAO.showAllProducts();
	}

	@Override
	public double totalBill(int userId) {
		return cartDAO.totalBill(userId);
	}

	@Override
	public boolean deleteCart(int userId) {
		return cartDAO.deleteCart(userId);
	}
	
}//End of class
