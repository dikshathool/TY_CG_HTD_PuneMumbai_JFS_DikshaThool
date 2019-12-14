package com.capgemini.onlinemedicalstorewithjdbc.dao;

import java.util.List;

import com.capgemini.onlinemedicalstorewithjdbc.bean.MessageBean;

public interface MessageDAO {
	
	public boolean sendMessage(int userId, String message, String messageType);

	public List<MessageBean> getMessage();

	public boolean sendResponse(int userId, String message, String messageType);

	public List<MessageBean> getResponse(int userId);

}//End of interface
