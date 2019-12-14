package com.capgemini.onlinemedicalstorewithjdbc.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.capgemini.onlinemedicalstorewithjdbc.bean.MessageBean;

public class MessageDAOImpl implements MessageDAO {
	
	FileReader reader = null;
	Properties prop = null;
	
	
	public MessageDAOImpl() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			reader = new FileReader("jdbc.properties");
			prop = new Properties();
			prop.load(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//End of MessageDAOImpl()

	@Override
	public boolean sendMessage(int userId, String message, String messageType) {
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"))) {

			PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("sendMessage"));
			pstmt.setInt(1, userId);
			pstmt.setString(2, message);
			pstmt.setString(3, messageType);
			

			int count = pstmt.executeUpdate();
			if (count > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}//End of sendMessage()

	@Override
	public List<MessageBean> getMessage() {
		return null;
	}//End of getMessage()

	@Override
	public boolean sendResponse(int userId, String message, String messageType) {
		return false;
	}//End of sendResponse()

	@Override
	public List<MessageBean> getResponse(int userId) {
		return null;
	}//End of getResponse()

}//End of class
