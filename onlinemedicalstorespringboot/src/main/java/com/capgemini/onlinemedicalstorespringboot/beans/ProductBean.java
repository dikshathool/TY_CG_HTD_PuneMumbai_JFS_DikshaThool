package com.capgemini.onlinemedicalstorespringboot.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_data")
public class ProductBean {
	@Id
	@Column(name = "product_id")
	private int productId;
	@Column(name = "product_name")
	private String productName;
	@Column(name = "product_brand")
	private String productBrand;
	@Column
	private String category;
	@Column
	private double price;
	@Column
	private String details;
	@Column
	private String available;
	@Column
	private int quantity;
	@Column(name = "img_url")
	private String imgURL;
	
	//Getters and Setters
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ProductBean [productId=" + productId + ", productName=" + productName + ", productBrand=" + productBrand
				+ ", category=" + category + ", price=" + price + ", details=" + details + ", available=" + available
				+ ", imgURL=" + imgURL + "]";
	}
	
}//End of class
