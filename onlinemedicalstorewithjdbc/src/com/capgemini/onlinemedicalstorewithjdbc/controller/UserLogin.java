package com.capgemini.onlinemedicalstorewithjdbc.controller;

import java.util.Scanner;

import com.capgemini.onlinemedicalstorewithjdbc.bean.UserBean;
import com.capgemini.onlinemedicalstorewithjdbc.bean.UsersBean;
import com.capgemini.onlinemedicalstorewithjdbc.dao.MedicalDAO;
import com.capgemini.onlinemedicalstorewithjdbc.factory.MedicalFactory;

public class UserLogin {
	public void loginUser() {
		Scanner scanner=new Scanner(System.in);
		MedicalDAO dao=MedicalFactory.getDAOImplInstance();
		System.out.println("Enter your email: ");
		String email=scanner.next();
		scanner.nextLine();
		System.out.println("Enter your password: ");
		String password=scanner.next();
		UsersBean usersBean = dao.userLogin(email, password);
		if(usersBean!=null) {
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
			System.out.println("You have entered wrong Credentials!!!");
			System.out.println("Press 1 to Again Login\nPress 2 to Forgot Password");
			int choice = scanner.nextInt();
			switch(choice) {
			case 1:
				loginUser();
				break;
			case 2:
				UpdateUserPassword updateUserPassword = new UpdateUserPassword();
				updateUserPassword.updatePassword();
				break;
			default:
				System.out.println("Please Enter Valid Choice!!!");
				break;
			}//End of Switch
		}
	}//End of loginUser()
}//End of class
