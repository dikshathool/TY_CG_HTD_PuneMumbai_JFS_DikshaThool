package com.capgemini.onlinemedicalstorewithjdbc.controller;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.onlinemedicalstorewithjdbc.bean.CartBean;
import com.capgemini.onlinemedicalstorewithjdbc.dao.CartDAO;
import com.capgemini.onlinemedicalstorewithjdbc.factory.MedicalFactory;


public class CartOperations {
	Scanner scanner = new Scanner(System.in);
	CartDAO cartDAO = MedicalFactory.getCartImplInstance();
	
	public void cartOperation(int userId) {
		System.out.println(
				"\nPress 1 to add Product in your Cart\nPress 2 to remove Product from your Cart\nPress 3 to show all products from your cart\nPress 4 to show Total Bill\nPress 5 to do Payment using Card\nPress 6 to go back to menu");
		int operation = scanner.nextInt();
		switch (operation) {
		case 1:// To add product in cart
			addIntoCart(userId);
			break;
			
		case 2:// To remove product from cart
			System.out.println("Enter Cart Id Which You Want to Remove From Cart");
			int cartId = scanner.nextInt();
			boolean state = cartDAO.removeCartProduct(userId, cartId);
			if(state == true) {
				System.out.println("Remove Successfully");
			} else {
				System.out.println("Unable To Remove Product From Cart");
			}
			cartOperation(userId);
			break;
			
		case 3:// To show all products from cart
			List<CartBean> cartList = cartDAO.showCartProducts();
			for(CartBean cartBean : cartList) {
				System.out.println("-------------------------------------------------------");
				System.out.println("Cart Id = "+cartBean.getCartId());
				System.out.println("Product Id = "+cartBean.getProductId());
				System.out.println("Product Name = "+cartBean.getProductName());
				System.out.println("Product Price = "+cartBean.getPrice());
				System.out.println("-------------------------------------------------------");
			}
			cartOperation(userId);
			break;
			
		case 4://To show total bill
			double totalBill = cartDAO.totalBill(userId);
			System.out.println("Your Total Bill is = "+totalBill);
			cartOperation(userId);
			break;

		case 5://To do payment
			payment(userId);
			break;
			
		case 6://To go back to menu when user login
			new UserOperations().afterUserLogin(userId);
			break;
			
		default:
			System.out.println("Please Enter Correct Option!!!");
			break;
		}
	}// End of cartProductOperation()
	
	public void addIntoCart(int userId) {
		System.out.println("Enter the Product Id which you want to order: ");
		int productId = scanner.nextInt();
		if (cartDAO.addIntoCart(userId, productId)) {
			System.out.println("Added Successfully");

			// To order more
			System.out.println("Want to order more then write yes otherwise write no");
			String orderMore = "yes";
			if (orderMore.contentEquals(scanner.next())) {
				addIntoCart(userId);
			} else {
				cartOperation(productId);
			}
		} else {
			System.out.println("Unable to Add Product");
			addIntoCart(userId);
		}
	}// End of addIntoCart()
	
	public void payment(int userId) {
		UserOperations userOperations = new UserOperations();
		System.out.println("\nPlease Enter Your Card Details To Complete Your Transaction!!!");
		System.out.println("Enter Your Card Number: ");
		Pattern pat = Pattern.compile("\\d{16}");
		long number = scanner.nextLong();
		String cardNumber = Long.toString(number);
		Matcher mat = pat.matcher(cardNumber);
		if (mat.matches()) {
			System.out.println("Enter Your Name: ");
			scanner.next();
			scanner.nextLine();
			System.out.println("Expiry Month: ");
			scanner.nextInt();
			System.out.print("Expiry Year: ");
			scanner.nextInt();
			System.out.println("CVV: ");
			Pattern pat1 = Pattern.compile("\\d{3}");
			int num = scanner.nextInt();
			String cvv = Integer.toString(num);
			Matcher mat1 = pat1.matcher(cvv);
			if (mat1.matches()) {
				double totalBill = cartDAO.totalBill(userId);
				System.out.println("Your Total Bill is = "+totalBill);
				System.out.println("TRANSACTION IS SUCCESSFULL");
				System.out.println("***THANK YOU FOR ORDERING***");
				cartDAO.deleteCart(userId);
				
				System.out.println("If You Have Any Query Then 'yes' Otherwise 'no'");
				String forQuery = "yes";
				if (forQuery.contentEquals(scanner.next())) {
					System.out.println("Enter Your Query: ");
					scanner.next();
					System.out.println("Thank You For Your Query...We Will Respond Soon!!!");
					System.out.println("*******************************************************");
					userOperations.afterUserLogin(userId);
				} else {
					System.out.println("Ok...Thank You!!!");
					System.out.println("*******************************************************");
					userOperations.afterUserLogin(userId);
				}

			} else {
				System.out.println("Please Enter Valid CVV Number!!!");
			}
		} else {
			System.out.println("TRANSACTION FAILED...PLEASE ENTER VALID DETAILS!!!");
			payment(userId);
		}
	}// End of payment()
}//End of Class
