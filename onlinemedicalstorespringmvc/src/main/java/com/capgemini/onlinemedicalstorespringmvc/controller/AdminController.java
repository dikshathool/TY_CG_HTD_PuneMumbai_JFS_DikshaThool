package com.capgemini.onlinemedicalstorespringmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.capgemini.onlinemedicalstorespringmvc.beans.AdminBean;
import com.capgemini.onlinemedicalstorespringmvc.beans.UserBean;
import com.capgemini.onlinemedicalstorespringmvc.service.MedicalService;

@Controller
public class AdminController {
	@Autowired
	private MedicalService service;

	@GetMapping("/homePage")
	public String frontPage() {
		return "medicalStoreHomePage";
	}// End of displayAdminLoginForm()

	// Handller method to display login form
	@GetMapping("/adminLoginForm")
	public String displayAdminLoginForm() {
		return "adminLoginForm";
	}// End of displayAdminLoginForm()

	// Handler method to perform submission of form and to get data
	@PostMapping("/adminLogin")
	public String adminLogin(String email, String password, ModelMap modelMap, HttpServletRequest req) {
		AdminBean adminBean = service.adminLogin(email, password);
		if (adminBean != null) {
			// Valid Credentials
			HttpSession session = req.getSession(true);
			session.setAttribute("adminBean", adminBean);

			return "adminHomePage";
		} else {
			modelMap.addAttribute("msg", "Invalid Credentials...");
			return "adminLoginForm";
		}
	}// End of adminLogin()

	@GetMapping("/adminHomePage")
	public String displayAdminHomePage() {
		return "adminHomePage";
	}// End of displayAdminLoginForm()

	@GetMapping("/userOperationPage")
	public String displayUserOperationPage() {
		return "userOperationPage";
	}// End of displayUserOperationPage()

	@GetMapping("/showAllUser")
	public String getAllUsers(HttpSession session, ModelMap modelMap) {

		if (session.isNew()) {
			// Invalid Session
			modelMap.addAttribute("msg", "Please Login First");
			return "adminLoginForm";

		} else {
			// Valid Session
			List<UserBean> usersList = service.getAllUsers();
			modelMap.addAttribute("usersList", usersList);

			return "displayAllUsers";
		}
	}// End of getAllUsers()

	@GetMapping("/removeUserForm")
	public String displayRemoveUserForm(HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			// Invalid Session
			modelMap.addAttribute("msg", "Please Login First");
			return "adminLoginForm";

		} else {
			// Valid Session
			return "removeUserForm";
		}
	}// End of displayRemoveUserForm()

	@GetMapping("/removeUser")
	public String deleteEmployee(int userId, HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			// Invalid Session
			modelMap.addAttribute("msg", "Please Login First");
			return "adminLoginForm";

		} else {
			// Valid Session
			if (service.removeUser(userId)) {
				modelMap.addAttribute("msg", "User Deleted Successfully!");
			} else {
				modelMap.addAttribute("msg", "User ID " + userId + " Not Found!");
			}

			return "removeUserForm";
		}
	}// End of searchEmployee()

	@GetMapping("/adminHome")
	public String displayAdminHomePage(HttpSession session, ModelMap modelMap) {

		if (session.isNew()) {
			// Invalid Session
			modelMap.addAttribute("msg", "Please Login First");
			return "adminLoginForm";

		} else {
			// Valid Session
			return "adminHomePage";
		}
	}// End of displayAdminHomePage()

	// To logout
	@GetMapping("/logout")
	public String logout(HttpSession session, ModelMap modelMap) {
		session.invalidate();
		modelMap.addAttribute("msg", "Logged out Successfully");
		return "medicalStoreHomePage";
	}// End of logout()
}// End of Class
