package com.capgemini.onlinemedicalstorewithjdbc.bean;

public class ProductBean {
	private int productId;
	private String productName;
	private String productBrand;
	private String category;
	private double price;
	private String details;
	private String available;
	
	//Getters and Setters
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
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	@Override
	public String toString() {
		return "productId=" + productId + ", productName=" + productName + ", productBrand=" + productBrand
				+ ", category=" + category + ", price=" + price + ", details=" + details + ", available=" + available;
	}//End of toString()
	
}//End of class
