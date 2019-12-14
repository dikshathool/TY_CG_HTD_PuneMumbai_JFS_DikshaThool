package com.capgemini.onlinemedicalstoreusingjpahibernate.controller;

import java.util.Scanner;

import com.capgemini.onlinemedicalstoreusingjpahibernate.beans.UsersBean;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.MedicalDAO;
import com.capgemini.onlinemedicalstoreusingjpahibernate.exceptions.ExceptionMethods;
import com.capgemini.onlinemedicalstoreusingjpahibernate.exceptions.MedicalExceptions;
import com.capgemini.onlinemedicalstoreusingjpahibernate.factory.MedicalFactory;

public class UserLogin {
	static boolean validation = false;
	
	public void loginUser() {
		Scanner scanner = new Scanner(System.in);
		MedicalDAO medicalDAO = MedicalFactory.getDAOImplInstance();
		System.out.println("Enter your email: ");
		String email = null;
		validation = true;
		while (validation) {
			try {
				String emailId = scanner.nextLine();
				scanner.nextLine();
				email = ExceptionMethods.emailValidator(emailId);
				validation = false;
			} catch (MedicalExceptions e) {
				e.getMessage();
			}
		}
		
		System.out.println("Enter your password: ");
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
		
		UsersBean usersBean = medicalDAO.userLogin(email, password);
		
		if (usersBean != null) {
			System.out.println("Successfully Login!!!");
			System.out.println("||||||***********WELCOME " + usersBean.getUserName() + "***********||||||");
			System.out.println("User Id = "+usersBean.getUserId());
			System.out.println("Email = "+usersBean.getEmail());
			System.out.println("Mobile Number = "+usersBean.getMobileNumber());
			System.out.println("Role = "+usersBean.getUserType());
			
			if (usersBean.getUserType().equals("Admin")) {
				new AdminOperations().afterAdminLogin(usersBean);
				
			} else {
				new UserOperations().afterUserLogin(usersBean.getUserId());
			}
			

		} else {
			System.err.println("You have entered wrong Credentials!!!");
			System.out.println("Press 1 to Again Login\nPress 2 to Forgot Password");
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
			case 1:
				loginUser();
				break;
				
			case 2:
				System.out.println("Enter Your Phone Number");
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
				scanner.nextLine();
				System.out.println("Enter Your New Password");
				String password1 = null;
				validation = true;
				while (validation) {
					String password2 = scanner.nextLine();
					try {
						password1 = ExceptionMethods.checkPassword(password2);
						validation = false;
					} catch (MedicalExceptions e) {
						e.getMessage();
					}
				}
				
				boolean state = medicalDAO.updateUserPassword(mobileNumber, password1);
				if(state == true) {
					System.out.println("Password Updated Successfully....Now Login!!!");
					loginUser();
				} else {
					System.err.println("Unable To Update Password");
				}
				break;
				
			default:
				System.err.println("Please Enter Valid Choice!!!");
				break;
			}// End of switch
		}
	}// End of loginUser()
}// End of class
