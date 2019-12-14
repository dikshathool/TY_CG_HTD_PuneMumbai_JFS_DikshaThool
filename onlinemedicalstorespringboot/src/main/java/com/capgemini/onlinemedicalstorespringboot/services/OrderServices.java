package com.capgemini.onlinemedicalstorespringboot.services;

import java.util.List;

import com.capgemini.onlinemedicalstorespringboot.beans.OrderHistoryBean;

public interface OrderServices {
	public boolean payment(int userId, String address);

	public boolean addOrderIntoHistory(OrderHistoryBean orderBean);

	public List<OrderHistoryBean> showOrder(int userId);
}//End of class
