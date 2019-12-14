package com.capgemini.onlinemedicalstorespringboot.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

//It will not accepts default values
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class MedicalResponses {
	private int statusCode;
	private String message;
	private String description;
	private UsersBean usersBean;
	private List<UsersBean> usersList;
	private ProductBean productBean;
	private List<ProductBean> productList;
	private double price;
	private double totalBill;
	private MessageBean messageBean;
	private List<MessageBean> messageList;
	private List<CartBean> cartList;
	private List<OrderHistoryBean> orderList;
	
	//Getters and Setters
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UsersBean getUsersBean() {
		return usersBean;
	}
	public void setUsersBean(UsersBean usersBean) {
		this.usersBean = usersBean;
	}
	public List<UsersBean> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<UsersBean> usersList) {
		this.usersList = usersList;
	}
	public ProductBean getProductBean() {
		return productBean;
	}
	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}
	public List<ProductBean> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductBean> productList) {
		this.productList = productList;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public MessageBean getMessageBean() {
		return messageBean;
	}
	public void setMessageBean(MessageBean messageBean) {
		this.messageBean = messageBean;
	}
	public List<MessageBean> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<MessageBean> messageList) {
		this.messageList = messageList;
	}
	public List<CartBean> getCartList() {
		return cartList;
	}
	public void setCartList(List<CartBean> cartList) {
		this.cartList = cartList;
	}
	public List<OrderHistoryBean> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<OrderHistoryBean> orderList) {
		this.orderList = orderList;
	}
	public double getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(double totalBill) {
		this.totalBill = totalBill;
	}
	
	
}// End of Class
