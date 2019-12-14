package com.capgemini.onlinemedicalstorespringmvc.service;

import java.util.List;

import com.capgemini.onlinemedicalstorespringmvc.beans.MessageBean;

public interface MessageServices {
	public boolean sendMessage(int userId, String message, String messageType);

	public List<MessageBean> getMessage();

	public boolean sendResponse(int userId, String message, String messageType);

	public List<MessageBean> getResponse(int userId);

}//End of MessageServices
