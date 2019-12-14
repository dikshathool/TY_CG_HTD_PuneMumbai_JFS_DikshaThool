package com.capgemini.onlinemedicalstorewithjdbc.controller;

import java.util.Scanner;

import com.capgemini.onlinemedicalstorewithjdbc.dao.MedicalDAO;
import com.capgemini.onlinemedicalstorewithjdbc.factory.MedicalFactory;

public class UpdateUserPassword {
	public void updatePassword() {
		MedicalDAO medicalDAO = MedicalFactory.getDAOImplInstance();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Your Mobile Number");
		long mobile = scanner.nextLong();
		System.out.println("Enter Your new Password");
		String password = scanner.next();
		if(medicalDAO.updateUserPassword(mobile, password)) {
			System.out.println("Your Password Updated Successfully");
		}
		else {
		 	System.out.println("Please Enter valid Mobile Number");
		}
		
	}//End of UpdateUserPassword()
}
