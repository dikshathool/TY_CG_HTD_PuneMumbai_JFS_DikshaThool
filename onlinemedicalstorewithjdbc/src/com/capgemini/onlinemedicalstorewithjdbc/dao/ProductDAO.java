package com.capgemini.onlinemedicalstorewithjdbc.dao;

import java.util.List;

import com.capgemini.onlinemedicalstorewithjdbc.bean.ProductBean;

public interface ProductDAO {
	//Abstract methods to perform operations on products
	public boolean addProduct(ProductBean productBean);
	public boolean updateProduct(int productId, double price, String available);
	public boolean removeProduct(int productId);
	List<ProductBean> showAllProducts();
	ProductBean getProductInfo(int productId);
	List<ProductBean> categoryProducts(String category);
}
