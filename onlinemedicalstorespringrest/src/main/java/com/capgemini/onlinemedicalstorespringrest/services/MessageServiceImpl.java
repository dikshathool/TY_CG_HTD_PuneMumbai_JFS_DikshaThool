package com.capgemini.onlinemedicalstorespringrest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.onlinemedicalstorespringrest.beans.MessageBean;
import com.capgemini.onlinemedicalstorespringrest.dao.MessageDAO;

@Service
public class MessageServiceImpl implements MessageServices {
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Override
	public boolean sendMessage(int userId, String message, String messageType) {
		return messageDAO.sendMessage(userId, message, messageType);
	}

	@Override
	public List<MessageBean> getMessage() {
		return messageDAO.getMessage();
	}

	@Override
	public boolean sendResponse(int userId, String message, String messageType) {
		return messageDAO.sendResponse(userId, message, messageType);
	}

	@Override
	public List<MessageBean> getResponse(int userId) {
		return messageDAO.getResponse(userId);
	}

}//End of Class
