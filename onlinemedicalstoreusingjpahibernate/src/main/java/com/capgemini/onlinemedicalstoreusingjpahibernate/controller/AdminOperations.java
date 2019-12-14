package com.capgemini.onlinemedicalstoreusingjpahibernate.controller;

import java.util.List;
import java.util.Scanner;

import com.capgemini.onlinemedicalstoreusingjpahibernate.MedicalMain;
import com.capgemini.onlinemedicalstoreusingjpahibernate.beans.MessageBean;
import com.capgemini.onlinemedicalstoreusingjpahibernate.beans.ProductBean;
import com.capgemini.onlinemedicalstoreusingjpahibernate.beans.UsersBean;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.MedicalDAO;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.MessageDAO;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.ProductDAO;
import com.capgemini.onlinemedicalstoreusingjpahibernate.exceptions.ExceptionMethods;
import com.capgemini.onlinemedicalstoreusingjpahibernate.exceptions.MedicalExceptions;
import com.capgemini.onlinemedicalstoreusingjpahibernate.factory.MedicalFactory;

public class AdminOperations {
	Scanner scanner = new Scanner(System.in);
	MedicalDAO medicalDAO = MedicalFactory.getDAOImplInstance();
	MessageDAO messageDAO = MedicalFactory.getDAOMessageImplInstance();
	static ExceptionMethods exceptionMethods = new ExceptionMethods();
	static boolean validation = false;

	public void afterAdminLogin(UsersBean usersBean) {
		System.out.println(
				"\nPress 1 to perform operation on user\nPress 2 to perform operation on product\nPress 3 to See Discussion Board\nPress 4 to Logout");
		int adminChoice = 0;
		validation = true;
		while (validation) {
			try {
				String adminChoice1 = scanner.next();
				adminChoice = ExceptionMethods.numberValidation(adminChoice1);
				validation = false;
			} catch (MedicalExceptions e1) {
				e1.getMessage();
			}
		}
		if (adminChoice == 1) {
			while (true) {
				System.out.println("Press 1 to Show All Users\nPress 2 to Remove User\nPress 3 to go back to menu");
				int selectedOperation = 0;
				validation = true;
				while (validation) {
					try {
						String selectedOperation1 = scanner.next();
						adminChoice = ExceptionMethods.numberValidation(selectedOperation1);
						validation = false;
					} catch (MedicalExceptions e1) {
						e1.getMessage();
					}
				}
				switch (selectedOperation) {
				case 1:
					// To show all users to admin
					List<UsersBean> userList = medicalDAO.showAllUsers();
					for (UsersBean userBean : userList) {
						System.out.println("-------------------------------------------------------");
						System.out.println("User Id = " + userBean.getUserId());
						System.out.println("User Name = " + userBean.getUserName());
						System.out.println("Email = " + userBean.getEmail());
						System.out.println("Mobile Number = " + userBean.getMobileNumber());
						System.out.println("-------------------------------------------------------");
					}
					break;
				case 2:
					// To remove selected user by admin
					Scanner scanner = new Scanner(System.in);
					System.out.println("Enter the UserId which you want to delete");
					int userId = scanner.nextInt();
					boolean state = medicalDAO.removeUser(userId);
					if (state = true) {
						System.out.println("Remove Successfully");
					} else {
						System.err.println("Selected User Id is Not Present");
					}
					break;

				case 3:// Go back to menu
					afterAdminLogin(usersBean);
					break;

				default:
					System.out.println("Please Enter Valid Option");
					break;
				}// End of switch case 1
			}
		} else if (adminChoice == 2) {
			ProductDAO productDAO = MedicalFactory.getDAOProductImplInstance();
			while (true) {
				System.out.println(
						"\nPress 1 to Add Product\nPress 2 to Update Product\nPress 3 to Remove Product\nPress 4 to show Products\nPress 5 to Go Back");
				int selectedOperation = scanner.nextInt();
				switch (selectedOperation) {

				case 1:// To Add new Product by admin
					ProductBean productBean = new ProductBean();
					System.out.println("Enter the Product Id: ");
					productBean.setProductId(scanner.nextInt());
					scanner.nextLine();
					
					System.out.println("Enter Product Name: ");
					productBean.setProductName(scanner.nextLine());
					System.out.println("Enter Product Brand: ");
					productBean.setProductBrand(scanner.nextLine());
					System.out.println("Enter Product Category: ");
					productBean.setCategory(scanner.nextLine());
					System.out.println("Enter Product Price: ");
					productBean.setPrice(scanner.nextDouble());
					scanner.nextLine();
					System.out.println("Enter Product Details: ");
					productBean.setDetails(scanner.nextLine());
					System.out.println("Enter Product Available or not");
					productBean.setAvailable(scanner.nextLine());
					boolean addState = productDAO.addProduct(productBean);

					if (addState == true) {
						System.out.println("New Product Added Successfully!!!");
					} else {
						System.err.println("Unable To Add Product");
					}
					break;

				case 2:// To Update details by admin
					System.out.println("Enter the Product Id you  want to update : ");
					int productId = scanner.nextInt();
					System.out.println("Enter the new price for this Item");
					double price = scanner.nextDouble();
					System.out.println("Enter Currently Product is In_Stock or Not_In_Stock");
					String available = scanner.next();
					boolean updateState = productDAO.updateProduct(productId, price, available);
					if (updateState == true) {
						System.out.println("Product Updated Successfully");
					} else {
						System.out.println("Unable To Update Product");
					}
					break;

				case 3:// To Remove product by admin
					System.out.println("Enter Product Id Which You Want To Delete");
					int removeProductId = scanner.nextInt();
					boolean removeState = productDAO.removeProduct(removeProductId);
					if (removeState == true) {
						System.out.println("Product Deleted Successfully");
					} else {
						System.err.println("Unable To delete Selected Product");
					}
					break;

				case 4:// To show products
					System.out.println("\nPress 'a' to Show All Products\nPress 'b' to Show Category Products");
					char productButton = scanner.next().charAt(0);
					List<ProductBean> productList = null;
					if (productButton == 'a') {
						productList = productDAO.showAllProducts();
						for (ProductBean productBean1 : productList) {
							System.out.println("---------------------------------------------------------");
							System.out.println("Product ID = " + productBean1.getProductId());
							System.out.println("Product Name = " + productBean1.getProductName());
							System.out.println("Product Brand = " + productBean1.getProductBrand());
							System.out.println("Category = " + productBean1.getCategory());
							System.out.println("Price = " + productBean1.getPrice());
							System.out.println("Details = " + productBean1.getDetails());
							System.out.println("Available = " + productBean1.getAvailable());
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
					break;

				case 5:// Go back
					afterAdminLogin(usersBean);
					break;

				default:
					System.out.println("Enter Correct Operation Character");
					break;
				}

			} // End of while()\

		} else if (adminChoice == 3) {
			List<MessageBean> messageBean = messageDAO.getMessage();
			for (MessageBean bean : messageBean) {
				System.out.println("*****************");
				System.out.println("User Id is "+bean.getUserId());
				System.out.println("Message is "+bean.getMessage());
				System.out.println("*****************");
			}
			System.out.println("select userId whom you want to send reply");
			int userId = scanner.nextInt();
			scanner.nextLine();
			if (userId != 0) {
				String type = "Answer";
				System.out.println("Enter reply");
				String message = scanner.nextLine();
				boolean state = messageDAO.sendResponse(userId, message, type);
				if (state) {
					System.out.println("---------Replied--------");
					afterAdminLogin(usersBean);
				} else {
					System.err.println("Unable To Sent Response");
				}
			}

		} else if (adminChoice == 4) {
			System.out.println("You are Successfully Logout!!!");
			System.out.println("-------------------------------------------------------------");
			MedicalMain.main(null);
			
		} else {
			System.err.println("Please Enter Correct Mentioned Option");
		}

	}// End of afterAdminLogin()
}// End of Class
