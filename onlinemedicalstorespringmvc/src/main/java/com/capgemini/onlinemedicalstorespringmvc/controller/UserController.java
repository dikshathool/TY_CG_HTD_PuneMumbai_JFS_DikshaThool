package com.capgemini.onlinemedicalstorespringmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.capgemini.onlinemedicalstorespringmvc.beans.UserBean;
import com.capgemini.onlinemedicalstorespringmvc.service.MedicalService;

@Controller
public class UserController {
	@Autowired
	private MedicalService service;

	// Handller method to display login form
	@GetMapping("/userLoginForm")
	public String displayUserLoginForm() {
		return "userLoginForm";
	}// End of displayUserLoginForm()

	// Handler method to perform submission of form and to get data
	@PostMapping("/userLogin")
	public String userLogin(String email, String password, ModelMap modelMap, HttpServletRequest req) {
		UserBean userBean = service.userLogin(email, password);
		if (userBean != null) {
			// Valid Credentials
			HttpSession session = req.getSession(true);
			session.setAttribute("userBean", userBean);

			return "userHomePage";
		} else {
			modelMap.addAttribute("msg", "Invalid Credentials...");
			return "userLoginForm";
		}
	}// End of userLogin()

	// Method to show registration form
	@GetMapping("/registrationForm")
	public String displayUserRegistrationForm() {
		return "registrationForm";
	}// End of displayUserRegistrationForm()

	@PostMapping("/userRegister")
	public String userRegister(UserBean userBean, HttpSession session, ModelMap modelMap) {
		if (service.userRegister(userBean)) {
			modelMap.addAttribute("msg", "Registration Successful!");
			return "userLoginForm";
		} else {
			modelMap.addAttribute("msg", "Unable to registered");
			return "registrationForm";
		}
	}// End of userRegister()
	
	@GetMapping("/userHome")
	public String displayUserHomePage(HttpSession session, ModelMap modelMap) {

		if (session.isNew()) {
			// Invalid Session
			modelMap.addAttribute("msg", "Please Login First");
			return "userLoginForm";

		} else {
			// Valid Session
			return "userHomePage";
		}
	}// End of displayUserHomePage()
	
	

}// End of Controller
