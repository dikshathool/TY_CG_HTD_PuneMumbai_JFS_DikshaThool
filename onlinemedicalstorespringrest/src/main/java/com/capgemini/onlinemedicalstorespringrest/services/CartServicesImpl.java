package com.capgemini.onlinemedicalstorespringrest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.onlinemedicalstorespringrest.beans.CartBean;
import com.capgemini.onlinemedicalstorespringrest.dao.CartDAO;

@Service
public class CartServicesImpl implements CartServices {
	
	@Autowired
	private CartDAO cartDAO;

	@Override
	public boolean addIntoCart(int userId, int productId) {
		return cartDAO.addIntoCart(userId, productId);
	}

	@Override
	public boolean removeFromCart(int userId, int cartId) {
		return cartDAO.removeFromCart(userId, cartId);
	}

	@Override
	public List<CartBean> showAllProducts(int userId) {
		return cartDAO.showAllProducts(userId);
	}

	@Override
	public double totalBill(int userId) {
		return cartDAO.totalBill(userId);
	}

	@Override
	public boolean deleteCart(int userId) {
		return cartDAO.deleteCart(userId);
	}

}//End of Class
