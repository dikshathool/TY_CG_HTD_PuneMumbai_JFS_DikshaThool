package com.capgemini.onlinemedicalstorespringboot.dao;

import java.util.List;

import com.capgemini.onlinemedicalstorespringboot.beans.OrderHistoryBean;

public interface OrderDAO {
	public boolean payment(int userId, String address);

	public boolean addOrderIntoHistory(OrderHistoryBean orderBean);

	public List<OrderHistoryBean> showOrder(int userId);

}// End of class
