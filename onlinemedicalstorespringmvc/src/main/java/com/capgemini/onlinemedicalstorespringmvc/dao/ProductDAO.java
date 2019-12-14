package com.capgemini.onlinemedicalstorespringmvc.dao;

import java.util.List;

import com.capgemini.onlinemedicalstorespringmvc.beans.ProductBean;

public interface ProductDAO {
	// Abstract methods to perform operations on products
	public boolean addProduct(ProductBean productBean);

	public boolean updateProduct(ProductBean productBean);

	public boolean removeProduct(int productId);

	public List<ProductBean> showAllProducts();

	List<ProductBean> categoryProducts(String category);
}// End of Interface
