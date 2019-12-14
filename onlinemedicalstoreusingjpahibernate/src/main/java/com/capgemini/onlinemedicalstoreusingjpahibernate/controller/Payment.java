package com.capgemini.onlinemedicalstoreusingjpahibernate.controller;

import java.util.Scanner;

import com.capgemini.onlinemedicalstoreusingjpahibernate.beans.UsersBean;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.CartDAO;
import com.capgemini.onlinemedicalstoreusingjpahibernate.exceptions.ExceptionMethods;
import com.capgemini.onlinemedicalstoreusingjpahibernate.exceptions.MedicalExceptions;
import com.capgemini.onlinemedicalstoreusingjpahibernate.factory.MedicalFactory;

public class Payment {
	static ExceptionMethods exceptionMethods = new ExceptionMethods();
	static boolean validation = false;
	CartDAO cartDAO = MedicalFactory.getDAOCartImplInstance();
	Scanner scanner = new Scanner(System.in);
	
	 public void payment(int userId) {
		 System.out.println("Enter Your Adress");
			String address = scanner.nextLine();

			System.out.println("\nPlease Enter Your Card Details To Complete Your Transaction!!!");

			Long cardNumber = 0L;
			validation = true;
			while (validation) {
				System.out.println("Enter Your Card Number: ");
				String cardNumber1 = scanner.next();
				try {
					cardNumber = exceptionMethods.cardNumberValidator(cardNumber1);
					validation = false;
				} catch (MedicalExceptions e) {
					e.getMessage();
				}
			}
			
			scanner.nextLine();
			System.out.println("Enter Your Name On Card");
			scanner.nextLine();

			validation = true;
			while (validation) {
				System.out.println("Expiry Month: ");
				String month1 = scanner.nextLine();
				try {
					int month = exceptionMethods.checkMonth(month1);
					validation = false;
				} catch (MedicalExceptions e) {
					e.getMessage();
				}
			}
			
			validation = true;
			while (validation) {
				System.out.print("Expiry Year: ");
				String year1 = scanner.next();
				try {
					int year = ExceptionMethods.checkYear(year1);
					validation = false;
				} catch (MedicalExceptions e) {
					e.getMessage();
				}
			}

			validation = true;
			while (validation) {
				System.out.println("CVV: ");
				String cvv1 = scanner.next();
				try {
					int cvv = ExceptionMethods.checkCVV(cvv1);
					validation = false;
				} catch (MedicalExceptions e) {
					e.getMessage();
				}
			}
			
			System.out.println("-----------------------------------------------");
			System.out.println("Total Bill is = " + cartDAO.totalBill(userId));
			// Delete From Cart
			boolean isDeleted = cartDAO.deleteCart(userId);
			
			if (isDeleted) {
				System.out.println("TRANSACTION IS SUCCESSFULL");
				System.out.println("***THANK YOU FOR ORDERING***");
				System.out.println("-----------------------------------------------");
				
			} else {
				System.err.println("Unable to Place Order...Plese Try Again");
				payment(userId);
			}

		 
	 }//End of payment()

}//End of class
