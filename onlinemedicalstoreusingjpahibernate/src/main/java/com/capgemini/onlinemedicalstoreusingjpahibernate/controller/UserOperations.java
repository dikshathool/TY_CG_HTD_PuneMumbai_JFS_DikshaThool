package com.capgemini.onlinemedicalstoreusingjpahibernate.controller;

import java.util.List;
import java.util.Scanner;

import javax.xml.bind.ValidationEvent;

import com.capgemini.onlinemedicalstoreusingjpahibernate.MedicalMain;
import com.capgemini.onlinemedicalstoreusingjpahibernate.beans.MessageBean;
import com.capgemini.onlinemedicalstoreusingjpahibernate.beans.ProductBean;
import com.capgemini.onlinemedicalstoreusingjpahibernate.beans.UsersBean;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.CartDAO;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.MedicalDAO;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.MessageDAO;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.ProductDAO;
import com.capgemini.onlinemedicalstoreusingjpahibernate.exceptions.ExceptionMethods;
import com.capgemini.onlinemedicalstoreusingjpahibernate.exceptions.MedicalExceptions;
import com.capgemini.onlinemedicalstoreusingjpahibernate.factory.MedicalFactory;

public class UserOperations {
	Scanner scanner = new Scanner(System.in);
	MedicalDAO medicalDAO = MedicalFactory.getDAOImplInstance();
	ProductDAO productDAO = MedicalFactory.getDAOProductImplInstance();
	CartDAO cartDAO = MedicalFactory.getDAOCartImplInstance();
	MessageDAO messageDAO = MedicalFactory.getDAOMessageImplInstance();
	static boolean validation = false;

	// Method to perform operation by user after login
	public void afterUserLogin(int userId) {
		System.out.println(
				"\nPress 1 to See Your Profile\nPress 2 to See Products\nPress 3 to Place Order\nPress 4 To See Discussion Board\nPress 5 to Logout");
		int choiceButton = 0;
		validation = true;
		while (validation) {
			try {
				String choiceButton1 = scanner.next();
				choiceButton = ExceptionMethods.numberValidation(choiceButton1);
				validation = false;
			} catch (MedicalExceptions e1) {
				e1.getMessage();
			}
		}

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
			int button = 0;
			validation = true;
			while (validation) {
				try {
					String button1 = scanner.next();
					button = ExceptionMethods.numberValidation(button1);
					validation = false;
				} catch (MedicalExceptions e1) {
					e1.getMessage();
				}
			}
			if (button == 1) {
				System.out.println("Enter Your Mobile Number");
				Long mobileNumber = null;
				validation = true;
				while (validation) {
					String mobileNumber1 = scanner.next();
					scanner.nextLine();
					try {
						mobileNumber = ExceptionMethods.contactValidator(mobileNumber1);
						validation = false;
					} catch (MedicalExceptions e) {
						e.getMessage();
					}
				}
				
				System.out.println("Enter Your new Password");
				String password = null;
				validation = true;
				while (validation) {
					String password1 = scanner.nextLine();
					try {
						password = ExceptionMethods.checkPassword(password1);
						validation = false;
					} catch (MedicalExceptions e) {
						e.getMessage();
					}
				}
				medicalDAO.updateUserPassword(mobileNumber, password);
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
				System.out.println("\nEnter 1 to write query\nEnter 2 to see response\nEnter 3 to Go Back");
				int queryButton = 0;
				validation = true;
				while (validation) {
					try {
						String queryButton1 = scanner.next();
						queryButton = ExceptionMethods.numberValidation(queryButton1);
						validation = false;
					} catch (MedicalExceptions e1) {
						e1.getMessage();
					}
				}
				scanner.nextLine();
				if (queryButton == 1) {
					String messageType = "Question";
					System.out.println("Enter Your Query");
					String message = scanner.nextLine();
					if (messageDAO.sendMessage(userId, message, messageType)) {
						System.out.println("---------Query Sent--------");
					} else {
						System.err.println("Query Not Sent");
					}
				} else if (queryButton == 2) {
					List<MessageBean> messageBean2 = messageDAO.getResponse(userId);
					List<MessageBean> messageQuery = messageDAO.getMessage();
					for (MessageBean messageBean : messageBean2) {
						System.out.println("-------------------------------------------------------");
						System.out.println("Type = " + messageBean.getMessageType());
						System.out.println("Response = " + messageBean.getMessage());
						System.out.println("-------------------------------------------------------");
					}
					afterUserLogin(userId);
				} else if (queryButton == 3) {
					afterUserLogin(userId);
				} else {
					System.out.println("Please Enter Mentioned Option");
				}
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
