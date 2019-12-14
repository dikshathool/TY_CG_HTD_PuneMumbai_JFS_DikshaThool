package com.capgemini.onlinemedicalstorewithjdbc.controller;

import java.util.List;
import java.util.Scanner;

import com.capgemini.onlinemedicalstorewithjdbc.bean.ProductBean;
import com.capgemini.onlinemedicalstorewithjdbc.bean.UsersBean;
import com.capgemini.onlinemedicalstorewithjdbc.dao.CartDAO;
import com.capgemini.onlinemedicalstorewithjdbc.dao.MedicalDAO;
import com.capgemini.onlinemedicalstorewithjdbc.dao.ProductDAO;
import com.capgemini.onlinemedicalstorewithjdbc.factory.MedicalFactory;
import com.capgemini.onlinemedicalstorewithjdbc.main.MedicalMain;

public class UserOperations {
	Scanner scanner = new Scanner(System.in);
	MedicalDAO medicalDAO = MedicalFactory.getDAOImplInstance();
	ProductDAO productDAO = MedicalFactory.getProductImplInstance();
	CartDAO cartDAO = MedicalFactory.getCartImplInstance();
	//MessageDAO messageDAO = MedicalFactory.getDAOMessageImplInstance();

	// Method to perform operation by user after login
	public void afterUserLogin(int userId) {
		System.out.println(
				"\nPress 1 to See Your Profile\nPress 2 to See Products\nPress 3 to Place Order\nPress 4 To See Discussion Board\nPress 5 to Logout");
		int choiceButton = scanner.nextInt();

		switch (choiceButton) {
		case 1:// See Profile
			UsersBean userBean = medicalDAO.getUser(userId);
			System.out.println("-------------------------------------------------------");
			System.out.println("User Id = " + userBean.getUserId());
			System.out.println("User Name = " + userBean.getUserName());
			System.out.println("Email = " + userBean.getEmail());
			System.out.println("Mobile Number = " + userBean.getMobileNumber());
			System.out.println("Role = "+userBean.getUserType());
			System.out.println("-------------------------------------------------------");
			System.out.println("\nPress 1 to Reset Password\nPress 2 to Go Back");
			int button = scanner.nextInt();
			if (button == 1) {
				System.out.println("Enter Your Mobile Number");
				long mobile = scanner.nextLong();
				System.out.println("Enter Your new Password");
				String password = scanner.next();
				medicalDAO.updateUserPassword(mobile, password);
				afterUserLogin(userId);
			} else {
				afterUserLogin(userId);
			}
			break;

		case 2:// See All Products
			System.out.println("\nPress 'a' to Show All Products\nPress 'b' to Show Category Products");
			char productButton = scanner.next().charAt(0);
			List<ProductBean> productList = null;
			if (productButton == 'a') {
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

			} else if (productButton == 'b') {
				System.out.println("Select Category \n1.Ayurvedic\n2.Baby Care");
				int choice = scanner.nextInt();
				String category;
				switch (choice) {
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
			afterUserLogin(userId);
			break;

		case 3:// Place Order
			new CartOperations().cartOperation(userId);
			afterUserLogin(userId);
			break;

		case 4:// Discussion Board
			while (true) {
//				System.out.println("\nEnter 1 to write query\nEnter 2 to see response\nEnter 3 to Go Back");
//				int queryButton = scanner.nextInt();
//				scanner.nextLine();
//				if (queryButton == 1) {
//					String messageType = "Question";
//					System.out.println("Enter Your Query");
//					String message = scanner.nextLine();
//					if (messageDAO.sendMessage(userId, message, messageType)) {
//						System.out.println("---------Query Sent--------");
//					} else {
//						System.err.println("Query Not Sent");
//					}
//				} else if (queryButton == 2) {
//					List<MessageBean> messageBean2 = messageDAO.getResponse(userId);
//					List<MessageBean> messageQuery = messageDAO.getMessage();
//					for (MessageBean messageBean : messageBean2) {
//						System.out.println("-------------------------------------------------------");
//						System.out.println("Type = " + messageBean.getMessageType());
//						System.out.println("Response = " + messageBean.getMessage());
//						System.out.println("-------------------------------------------------------");
//					}
//					afterUserLogin(userId);
//				} else if (queryButton == 3) {
//					afterUserLogin(userId);
//				} else {
//					System.out.println("Please Enter Mentioned Option");
//				}
			}//End of while()

		case 5:// Logout
			new MedicalMain().main(null);
			break;

		default:
			System.out.println("Please Enter Valid Choice!!!");
			break;
		}
	}// End of afterUserLogin()

}// End of Class