package com.capgemini.onlinemedicalstorespringboot.services;

import java.util.List;

import com.capgemini.onlinemedicalstorespringboot.beans.MessageBean;

public interface MessageServices {
	public boolean sendMessage(int userId, String message, String messageType);

	public List<MessageBean> getMessage();

	public boolean sendResponse(int userId, String message, String messageType);

	public List<MessageBean> getResponse(int userId);

}//End of MessageServices
