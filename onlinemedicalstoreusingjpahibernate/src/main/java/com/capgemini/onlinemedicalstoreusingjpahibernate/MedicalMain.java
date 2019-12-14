package com.capgemini.onlinemedicalstoreusingjpahibernate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.capgemini.onlinemedicalstoreusingjpahibernate.beans.ProductBean;
import com.capgemini.onlinemedicalstoreusingjpahibernate.controller.RegisterUser;
import com.capgemini.onlinemedicalstoreusingjpahibernate.controller.UserLogin;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.MedicalDAO;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.ProductDAO;
import com.capgemini.onlinemedicalstoreusingjpahibernate.exceptions.ExceptionMethods;
import com.capgemini.onlinemedicalstoreusingjpahibernate.exceptions.MedicalExceptions;
import com.capgemini.onlinemedicalstoreusingjpahibernate.factory.MedicalFactory;

public class MedicalMain {
	static boolean validation = false;
	public static void main(String[] args) {
		ProductDAO productDAO = MedicalFactory.getDAOProductImplInstance();
		
		Scanner scanner = new Scanner(System.in);
		LocalDateTime today = LocalDateTime.now();
		System.out.println("Current DateTime=" + today);

		MedicalDAO medicalDAO = MedicalFactory.getDAOImplInstance();
		
		while(true) {
			System.out.println("\n@@@@@@******WELCOME TO ONLINE MEDICAL STORE******@@@@@@");
			System.out.println(
					"\nPress 1 for Login\nPress 2 to User Registration\nPress 3 to Show Products");
			int choice = 0;
			validation = true;
			while (validation) {
				try {
					String choice1 = scanner.next();
					choice = ExceptionMethods.numberValidation(choice1);
					validation = false;
				} catch (MedicalExceptions e1) {
					e1.getMessage();
				}
			}
			switch (choice) {
			case 1://Login
				new UserLogin().loginUser();
				
			case 2://New User registration
				RegisterUser registerUser = new RegisterUser();
				registerUser.userRegister();
				break;
				
			case 3://Visit as Visitors
				System.out.println("\nPress 'a' to Show All Products\nPress 'b' to Show Category Products");
				String productButton = null;
				validation = true;
				while (validation) {
					try {
						String productButton1 = scanner.nextLine();
						productButton = ExceptionMethods.charValidation(productButton1);
						validation = false;
					} catch (MedicalExceptions e) {
						e.getMessage();
					}
				}
				List<ProductBean> productList = null;
				if (productButton.equals("a")) {
					productList = productDAO.showAllProducts();
					for (ProductBean productBean : productList) {
						System.out.println("---------------------------------------------------------");
						System.out.println("Product ID = " + productBean.getProductId());
						System.out.println("Product Name = " + productBean.getProductName());
						System.out.println("Product Brand = " + productBean.getProductBrand());
						System.out.println("Category = " + productBean.getCategory());
						System.out.println("Price = " + productBean.getPrice());
						System.out.println("Details = " + productBean.getDetails());
						System.out.println("Available = " + productBean.getAvailable());
						System.out.println("---------------------------------------------------------");
					}

				} else if (productButton.equals("b")) {
					System.out.println("Select Category \n1.Ayurvedic\n2.Baby Care");
					int categoryChoice = 0;
					validation = true;
					while (validation) {
						try {
							String categoryChoice1 = scanner.next();
							categoryChoice = ExceptionMethods.numberValidation(categoryChoice1);
							validation = false;
						} catch (MedicalExceptions e1) {
							e1.getMessage();
						}
					}
					String category;
					switch (categoryChoice) {
					case 1:
						category = "Ayurvedic";
						productList = productDAO.categoryProducts(category);
						break;

					case 2:
						category = "Baby Care";
						productList = productDAO.categoryProducts(category);
						break;

					default:
						System.out.println("Please Enter Valid Category");
						break;
					}
				}
				break;
				
			default:
				System.out.println("Please Enter Valid Choice!!!");
				break;
				
			}
		}//End of while(true)
		
	}//End of main method

}//End of class
