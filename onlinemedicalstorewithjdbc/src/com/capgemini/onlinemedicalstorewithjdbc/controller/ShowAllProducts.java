package com.capgemini.onlinemedicalstorewithjdbc.controller;

import java.util.List;

import com.capgemini.onlinemedicalstorewithjdbc.bean.ProductBean;
import com.capgemini.onlinemedicalstorewithjdbc.dao.ProductDAO;
import com.capgemini.onlinemedicalstorewithjdbc.factory.MedicalFactory;

public class ShowAllProducts {
	public static void main(String[] args) {
		ProductDAO dao = MedicalFactory.getProductImplInstance();
		List<ProductBean> list = dao.showAllProducts();
		if(list!=null) {
			for (ProductBean productBean : list) {
				System.out.println("Product ID: " + productBean.getProductId());
				System.out.println("Product Name: " + productBean.getProductName());
				System.out.println("Product Brand: " + productBean.getProductBrand());
				System.out.println("Product Price: " + productBean.getPrice());
				System.out.println("Product Details: " +productBean.getDetails());
				System.out.println("Product Pack Size: "+productBean.getAvailable());
				System.out.println("-----------------------------------------------------");
			} // End of for loop
		} else {
			System.out.println("Something went wrong");
		}
		
	}//End of showProducts
}//End of class
