package com.capgemini.onlinemedicalstorespringmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.capgemini.onlinemedicalstorespringmvc.beans.MessageBean;
import com.capgemini.onlinemedicalstorespringmvc.service.MessageServices;

@Controller
public class MessageController {
	@Autowired
	private MessageServices messageServices;
	
	@GetMapping("/userMessages")
	public String showUserQuery(HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			// Invalid Session
			modelMap.addAttribute("msg", "Please Login First");
			return "adminLoginForm";

		} else {
			// Valid Session
			List<MessageBean> messageList = messageServices.getMessage();
			modelMap.addAttribute("messageList", messageList);

			return "showUserMessages";
		}
	}// End of showUserQuery()
	
	@GetMapping("/giveUserReply")
	public String displayUserReplyForm() {
		return "userReplyForm";
	}//End of displayUserReplyForm()
	
	@PostMapping("/userReplyForm")
	public String giveUserReply(int userId, String message, String messageType, HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			// Invalid Session
			modelMap.addAttribute("msg", "Please Register....");

			return "adminLoginForm";
		} else {
			// Valid Session
			if (messageServices.sendResponse(userId, message, messageType)) {
				modelMap.addAttribute("msg", " Reply Sent Successfully");
			} else {
				modelMap.addAttribute("msg", "Unable To Sent Reply");
			}
			return "userReplyForm";
		}
	}// End of giveUserReply()
	
	@GetMapping(path = "/discussion")
	public String displayDisscussionBoard() {
		return "disscusionBoard";
	}//End of displayDisscutionBoard()	

}//End of Controller
