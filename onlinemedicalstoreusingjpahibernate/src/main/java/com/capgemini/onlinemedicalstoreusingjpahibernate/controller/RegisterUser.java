package com.capgemini.onlinemedicalstoreusingjpahibernate.controller;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.onlinemedicalstoreusingjpahibernate.beans.UsersBean;
import com.capgemini.onlinemedicalstoreusingjpahibernate.dao.MedicalDAO;
import com.capgemini.onlinemedicalstoreusingjpahibernate.exceptions.ExceptionMethods;
import com.capgemini.onlinemedicalstoreusingjpahibernate.exceptions.MedicalExceptions;
import com.capgemini.onlinemedicalstoreusingjpahibernate.factory.MedicalFactory;

public class RegisterUser {
	static ExceptionMethods exceptionMethods = new ExceptionMethods();
	static boolean validation = false;
	static String password1;
	static String emailId;
	static String mobileNumber1;
	
	public void userRegister() {
		MedicalDAO dao = MedicalFactory.getDAOImplInstance();
		UsersBean info = new UsersBean();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter your Name: ");
		info.setUserName(scanner.nextLine());
		
		System.out.println("Enter your Email: ");
		String email = null;
		validation = true;
		while (validation) {
			try {
				emailId = scanner.nextLine();
				email = ExceptionMethods.emailValidator(emailId);
				validation = false;
			} catch (MedicalExceptions e) {
				e.getMessage();
			}
		}
		info.setEmail(email);
		
		System.out.println("Enter your Mobile Number: ");
		Long mobileNumber = null;
		validation = true;
		while (validation) {
			mobileNumber1 = scanner.next();
			scanner.nextLine();
			try {
				mobileNumber = exceptionMethods.contactValidator(mobileNumber1);
				validation = false;
			} catch (MedicalExceptions e) {
				e.getMessage();
			}
		}
		info.setMobileNumber(mobileNumber);
		
		System.out.println("Enter Password: ");
		String password = null;
		validation = true;
		while (validation) {
			password1 = scanner.nextLine();
			try {
				password = ExceptionMethods.checkPassword(password1);
				validation = false;
			} catch (MedicalExceptions e) {
				e.getMessage();
			}
		}
		info.setPassword(password);
		
		System.out.println("Select User Type:\nPress 1 for Admin \nPress 2 for User");
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
		String userType;
		if (choice == 1) {
			userType = "Admin";
		} else {
			userType = "User";
		}
		info.setUserType(userType);
	
		boolean user = dao.registerUser(info);
		if(user == true) {
			System.out.println("Registered Successfully");
			new UserLogin().loginUser();
		} else {
			System.err.println("Unable To Registered...Please Try Again");
			userRegister();
		}
	}//End of userRegister()
}//End of Class
