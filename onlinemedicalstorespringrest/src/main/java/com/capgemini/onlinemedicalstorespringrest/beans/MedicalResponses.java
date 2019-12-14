package com.capgemini.onlinemedicalstorespringrest.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

//It will not accepts default values
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class MedicalResponses {
	private int statusCode;
	private String message;
	private String description;
	private UserBean userBean;
	private List<UserBean> userList;
	private ProductBean productBean;
	private List<ProductBean> productList;
	private double price;
	private MessageBean messageBean;
	private List<MessageBean> messageList;
	private List<CartBean> cartList;

	// Getters and Setters
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

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public List<UserBean> getUserList() {
		return userList;
	}

	public void setUserList(List<UserBean> userList) {
		this.userList = userList;
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

}// End of Class
