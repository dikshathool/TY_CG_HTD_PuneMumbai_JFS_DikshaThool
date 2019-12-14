package com.capgemini.onlinemedicalstorespringrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlinemedicalstorespringrest.beans.AdminBean;
import com.capgemini.onlinemedicalstorespringrest.beans.MedicalResponses;
import com.capgemini.onlinemedicalstorespringrest.beans.UserBean;
import com.capgemini.onlinemedicalstorespringrest.services.MedicalServices;

@RestController
//To connect rest with angular
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicalStoreController {
	@Autowired
	private MedicalServices medicalServices;

	@PutMapping(path = "/userRegister", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponses Registration(@RequestBody UserBean userBean) {
		boolean registerUser = medicalServices.registerUser(userBean);
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
	}// End of Registration

	@GetMapping(path = "/adminLogin")
	public MedicalResponses adminLogin(@RequestParam String email, @RequestParam String password) {
		AdminBean adminLogin = medicalServices.adminLogin(email, password);
		MedicalResponses response = new MedicalResponses();
		if (adminLogin != null) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Admin Logged In........");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Admin Login Failed........");
		}
		return response;
	}// End of adminLogin

	@GetMapping(path = "/userLogin")
	public MedicalResponses UserLogin(@RequestParam String email, @RequestParam String password) {
		UserBean userLogin = medicalServices.userLogin(email, password);
		MedicalResponses response = new MedicalResponses();
		if (userLogin != null) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Users Logged in........");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("User Login Failed........");
		}
		return response;
	}// End of UserLogin

	@GetMapping(path = "/showAllUsers", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public MedicalResponses showAllUsers() {
		List<UserBean> userList = medicalServices.showAllUsers();
		MedicalResponses response = new MedicalResponses();
		if (userList != null) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("All Users Data Found........");
			response.setUserList(userList);
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("All Users data not Found........");
		}
		return response;
	}// End of getAllUsers()

	@GetMapping(path = "/getUser", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public MedicalResponses getUser(int userId) {
		UserBean userBean = medicalServices.getUser(userId);
		MedicalResponses response = new MedicalResponses();

		if (userBean != null) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("User Record Found...");
			response.setUserBean(userBean);
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("User Id " + userId + "Not Found!!!");
		}
		return response;

	}// End of getEmployees()

	@DeleteMapping(path = "/removeUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponses removeUser(@RequestParam int userId) {
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
	public MedicalResponses updateUser(@RequestBody UserBean userBean) {
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
