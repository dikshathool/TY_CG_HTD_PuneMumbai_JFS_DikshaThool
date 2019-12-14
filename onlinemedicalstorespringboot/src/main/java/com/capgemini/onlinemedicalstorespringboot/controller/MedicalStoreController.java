package com.capgemini.onlinemedicalstorespringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlinemedicalstorespringboot.beans.MedicalResponses;
import com.capgemini.onlinemedicalstorespringboot.beans.UsersBean;
import com.capgemini.onlinemedicalstorespringboot.services.MedicalServices;

@RestController
//To connect rest with angular
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicalStoreController {
	@Autowired
	private MedicalServices medicalServices;
	
	@PutMapping(path = "/registerUser",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponses userRegistration(@RequestBody UsersBean userBean) {
		boolean registerUser = medicalServices.userRegistration(userBean);
		MedicalResponses response = new MedicalResponses();
		if (registerUser) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("User Registered.......");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("User Registration Failed........");
		}
		return response;
	}//userRegistration()

	@PostMapping(path = "/usersLogin")
	public MedicalResponses UsersLogin(@RequestBody UsersBean userBean) {
		UsersBean login = medicalServices.usersLogin(userBean.getEmail(), userBean.getPassword());
		MedicalResponses response=new MedicalResponses();
		if (login!=null) {
		//response.setStatusCode(201);
		//response.setMessage("Success");
			response.setUsersBean(login);
			//response.setDescription("Users Logged in........");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("User Login Failed........");
		}
		return response;
	}//End of UserLogin()
	
	@GetMapping(path = "/showAllUsers", produces = { MediaType.APPLICATION_JSON_VALUE})
	public MedicalResponses showAllUsers() {
		List<UsersBean> usersList = medicalServices.showAllUsers();
		MedicalResponses response = new MedicalResponses();
		if (usersList != null) {
			response.setStatusCode(201); 
			response.setMessage("Success");
	        response.setDescription("All Users Data Found........");
			response.setUsersList(usersList);
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("All Users data not Found........");
		}
		return response;
	}// End of getAllUsers()

	@GetMapping(path = "/getUser", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public MedicalResponses getUser(int userId) {
		UsersBean userBean = medicalServices.getUser(userId);
		MedicalResponses response = new MedicalResponses();

		if (userBean != null) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("User Record Found...");
			//response.setUserBean(userBean);
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("User Id " + userId + "Not Found!!!");
		}
		return response;

	}// End of getEmployees()

	@DeleteMapping(path = "/removeUser/{userId}")
	public MedicalResponses removeUser(@PathVariable("userId") int userId) {
		boolean userDelete = medicalServices.removeUser(userId);
		MedicalResponses response = new MedicalResponses();
		if (userDelete) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("User Deleted successfully.......");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Unable To Delete User........");
		}
		return response;
	}// End of removeUser()

	@PostMapping(path = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponses updateUser(@RequestBody UsersBean userBean) {
		boolean userUpdate = medicalServices.updateUser(userBean);
		MedicalResponses response = new MedicalResponses();
		if (userUpdate) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("User Details Updated.......");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Update Failed........");
		}
		return response;
	}// End of updateUser()
	
	

}// End of Class
