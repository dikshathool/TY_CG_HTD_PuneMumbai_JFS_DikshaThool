package com.capgemini.onlinemedicalstorewithjdbc.controller;

import java.util.List;
import java.util.Scanner;

import com.capgemini.onlinemedicalstorewithjdbc.bean.ProductBean;
import com.capgemini.onlinemedicalstorewithjdbc.dao.ProductDAO;
import com.capgemini.onlinemedicalstorewithjdbc.factory.MedicalFactory;

public class AllOperationsOnProduct {
	Scanner scanner = new Scanner(System.in);
	//Operations on products perform by admin after login
	public void productOperationByAdmin() {
		showCategoryProducts();
		System.out.println("\nPress A to Add Product\nPress B to Update Product\nPress C to Remove Product\nPress D to see selected category products\nPress E to Show All Products");
		char selectedOperation = scanner.next().charAt(0);
		switch (selectedOperation) {
		case 'A':// To Add new Product by admin
			productAdd();
			break;
		case 'B':// To Update details by admin
			productUpdate();
			break;
		case 'C':// To Remove product by admin
			productRemove();
			break;
		case 'D'://To change again category
			showCategoryProducts();
			break;
		case 'E'://To show all products
			showAllProducts();
			break;
		default:
			System.out.println("Enter Correct Operation Number");
			break;
		}
	}//End of productOperationByAdmin()
	
	//To select category of product
	public void showCategoryProducts() {
		Scanner sc=new Scanner(System.in);
		ProductDAO productDAO = MedicalFactory.getProductImplInstance();
		System.out.println("Write Category of Product\nAyurvedic \nBabyCare");
		List<ProductBean> list = productDAO.categoryProducts(sc.nextLine());
		if (list != null)
		{	
			for (ProductBean productBean : list)
			{
				System.out.println(productBean);
				System.out.println("-----------------------------------------------------");
			}//End of for loop
		}
		else
		{
			System.out.println("Something went wrong");
		}
	}//End of showCategoryProducts()
	
	//To add product
	public void productAdd() {
		ProductDAO dao = MedicalFactory.getProductImplInstance();
		ProductBean info = new ProductBean();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Product Id: ");
		info.setProductId(scanner.nextInt());
		scanner.nextLine();
		System.out.println("Enter Product Name: ");
		info.setProductName(scanner.nextLine());
		System.out.println("Enter Product Brand: ");
		info.setProductBrand(scanner.nextLine());
		System.out.println("Enter Product Category: ");
		info.setCategory(scanner.nextLine());
		System.out.println("Enter Product Price: ");
		info.setPrice(scanner.nextDouble());
		scanner.nextLine();
		System.out.println("Enter Product Details: ");
		info.setDetails(scanner.nextLine());
		System.out.println("Enter Product Available or not");
		info.setAvailable(scanner.nextLine());
		if(dao.addProduct(info)) {
			System.out.println("New Product Added Successfully");
		} else {
			System.out.println("Something went wrong");
		}
	}//End of addProduct()
	
	//To Update product
	public void productUpdate() {
		ProductDAO productDAO = MedicalFactory.getProductImplInstance();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Product Id you  want to update : ");
		int productId = scanner.nextInt();
		System.out.println("Enter the new price for this Item");
		double price = scanner.nextDouble();
		System.out.println("Enter Currently Product is In_Stock or Not_In_Stock");
		String available = scanner.next();
		if(productDAO.updateProduct(productId, price, available)) {
			System.out.println("Product Details Updated Successfully");
		}
		else {
		 	System.out.println("Something went wrong");
		}//End of productUpdate()
	}//End of productUpdate()
	
	//To remove product
	public void productRemove() {
		ProductDAO productDAO = MedicalFactory.getProductImplInstance();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Product Id which you want to delete");
		if(productDAO.removeProduct(scanner.nextInt())) {
			System.out.println("Product deleted Sucessfully");
		} else {
			System.out.println("Something went wrong");
		}
	}//End of productRemove()
	
	public void showAllProducts() {
		ProductDAO dao = MedicalFactory.getProductImplInstance();
		List<ProductBean> list = dao.showAllProducts();
		if(list!=null) {
			for (ProductBean productBean : list) {
				System.out.println("Product ID: " + productBean.getProductId());
				System.out.println("Product Name: " + productBean.getProductName());
				System.out.println("Product Brand: " + productBean.getProductBrand());
				System.out.println("Product Category: "+productBean.getCategory());
				System.out.println("Product Price: " + productBean.getPrice());
				System.out.println("Product Details: " +productBean.getDetails());
				System.out.println("Product Pack Size: "+productBean.getAvailable());
				System.out.println("-----------------------------------------------------");
			} // End of for loop
		} else {
			System.out.println("Something went wrong");
		}
		
	}//End of showAllProducts
}//End of class
