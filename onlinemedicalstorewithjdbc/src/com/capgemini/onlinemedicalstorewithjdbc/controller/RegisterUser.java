package com.capgemini.onlinemedicalstorewithjdbc.controller;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.onlinemedicalstorewithjdbc.bean.UserBean;
import com.capgemini.onlinemedicalstorewithjdbc.bean.UsersBean;
import com.capgemini.onlinemedicalstorewithjdbc.dao.MedicalDAO;
import com.capgemini.onlinemedicalstorewithjdbc.factory.MedicalFactory;

public class RegisterUser {
	public void userRegister() {
		MedicalDAO dao = MedicalFactory.getDAOImplInstance();
		UsersBean info = new UsersBean();
		Scanner scanner = new Scanner(System.in);
		//System.out.println("Enter User Id: ");
		//info.setUserId(scanner.nextInt());
		System.out.println("Enter your Name: ");
		info.setUserName(scanner.nextLine());
		System.out.println("Enter your Email: ");
		Pattern pat = Pattern.compile("\\w+\\@\\w+\\.\\w+");
		String email = scanner.next();
		Matcher mat = pat.matcher(email);
		if(mat.matches()) {
			info.setEmail(email);
			System.out.println("Enter your Mobile Number: ");
			info.setMobileNumber(scanner.nextLong());
			System.out.println("Enter Password: ");
			info.setPassword(scanner.next());
		} else {
			System.out.println("Enter valid Email...");
			userRegister();
		}
		if(dao.registerUser(info)) {
			System.out.println("Registered Successfully");
		} else {
			System.out.println("Something went wrong");
		}
	}//End of userRegister()
}//End of class
