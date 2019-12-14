package com.capgemini.onlinemedicalstorespringboot.services;

import java.util.List;

import com.capgemini.onlinemedicalstorespringboot.beans.ProductBean;

public interface ProductServices {
	public boolean addProduct(ProductBean productBean);

	public boolean updateProduct(ProductBean productBean);

	public boolean removeProduct(int productId);

	public List<ProductBean> showAllProducts();

	List<ProductBean> categoryProducts(String category);
}// End of Interface
