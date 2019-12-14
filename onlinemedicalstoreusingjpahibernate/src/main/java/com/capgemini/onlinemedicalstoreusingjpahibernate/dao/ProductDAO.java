package com.capgemini.onlinemedicalstoreusingjpahibernate.dao;

import java.util.List;

import com.capgemini.onlinemedicalstoreusingjpahibernate.beans.ProductBean;

public interface ProductDAO {
	// Abstract methods to perform operations on products
	public boolean addProduct(ProductBean productBean);

	public boolean updateProduct(int productId, double price, String available);

	public boolean removeProduct(int productId);

	public List<ProductBean> showAllProducts();

	List<ProductBean> categoryProducts(String category);

}// End of ProductDAO interface
