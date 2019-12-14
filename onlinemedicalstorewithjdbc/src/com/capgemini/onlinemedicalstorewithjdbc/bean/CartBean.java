package com.capgemini.onlinemedicalstorewithjdbc.bean;


public class CartBean {
	private int cartId;
	private int userId;	
	private String userName;
	private int productId;
	private String productName;
	private double price;
	
	//Getters And Setters
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "CartBean [cartId=" + cartId + ", userId=" + userId + ", userName=" + userName + ", productId="
				+ productId + ", productName=" + productName + ", price=" + price + "]";
	}
	
}//End of Class
