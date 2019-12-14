package com.capgemini.onlinemedicalstorewithjdbc.controller;

import java.util.List;
import java.util.Scanner;

import com.capgemini.onlinemedicalstorewithjdbc.bean.UserBean;
import com.capgemini.onlinemedicalstorewithjdbc.bean.UsersBean;
import com.capgemini.onlinemedicalstorewithjdbc.dao.MedicalDAO;
import com.capgemini.onlinemedicalstorewithjdbc.factory.MedicalFactory;

public class AllOperationsOnUser {
	Scanner scanner = new Scanner(System.in);
	//Operations on users perform by admin after login
		public void userOperationByAdmin() {
			System.out.println("Press A to Show All Users\nPress B to Remove User\nPress C to go back to menu");
			char selectedOperation = scanner.next().charAt(0);
			switch (selectedOperation) {
			case 'A':
				// To show all users to admin
			    showAllUser();
			    userOperationByAdmin();
				break;
			case 'B':
				// To remove selected user by admin
				userRemove();
				userOperationByAdmin();
				break;
			case 'C':
				//Go back to menu
				break;
			default:
				System.out.println("Please Enter Valid Option");
				break;
			}
		}//End of userOperationByAdmin()
		
	//Show All users
	public void showAllUser() {
		MedicalDAO dao = MedicalFactory.getDAOImplInstance();
		List<UsersBean> list = dao.showUsers();
		if (list != null)
		{	
			for (UsersBean userBean : list)
			{
				System.out.println("User ID: "+userBean.getUserId());
				System.out.println("User Name: "+userBean.getUserName());
				System.out.println("User Email: "+userBean.getEmail());
				System.out.println("User Mobile Number"+userBean.getMobileNumber());
				System.out.println("-----------------------------------------------------");
			}//End of for loop
		}
		else
		{
			System.out.println("Something went wrong");
		}
	}//End of showAllUser()
	
	//To remove user by admin
	public void userRemove() {
		MedicalDAO medicalDAO = MedicalFactory.getDAOImplInstance();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the UserId which you want to delete");
		if(medicalDAO.removeUser(Integer.parseInt(scanner.nextLine())))
		{
			System.out.println("User deleted Sucessfully");
		}
		else
		{
			System.out.println("Something went wrong");
		}
	}//End of userRemove()
	
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

}//End of class
