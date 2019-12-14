package com.capgemini.onlinemedicalstorespringboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.onlinemedicalstorespringboot.beans.OrderHistoryBean;
import com.capgemini.onlinemedicalstorespringboot.dao.OrderDAO;

@Service
public class OrderServicesImpl implements OrderServices {
	@Autowired
	private OrderDAO orderDAO;

	@Override
	public boolean payment(int userId, String address) {
		return orderDAO.payment(userId, address);
	}

	@Override
	public boolean addOrderIntoHistory(OrderHistoryBean orderBean) {
		return orderDAO.addOrderIntoHistory(orderBean);
	}

	@Override
	public List<OrderHistoryBean> showOrder(int userId) {
		return orderDAO.showOrder(userId);
	}
	
	
	

}//End of class
