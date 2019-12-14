package com.capgemini.onlinemedicalstorewithjdbc.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.capgemini.onlinemedicalstorewithjdbc.bean.AdminBean;
import com.capgemini.onlinemedicalstorewithjdbc.bean.ProductBean;
import com.capgemini.onlinemedicalstorewithjdbc.bean.UserBean;
import com.capgemini.onlinemedicalstorewithjdbc.bean.UsersBean;

public class MedicalDAOJDBCImpl implements MedicalDAO {

	FileReader reader = null;
	Properties prop = null;
	UsersBean userBean;
	AdminBean admin;
	ProductBean product;

	public MedicalDAOJDBCImpl() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			reader = new FileReader("jdbc.properties");
			prop = new Properties();
			prop.load(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//End of MedicalDAOJDBCImpl()

	@Override
	public AdminBean adminLogin(String email, String password) {
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("query1"))) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					System.out.println("*****Successfully login******");
					admin = new AdminBean();
					admin.setAdminId(rs.getInt(1));
					admin.setAdminName(rs.getString(2));
					admin.setMobileNumber(rs.getLong(4));
				}
				return admin;

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}// End of adminlogin

	@Override
	public UsersBean userLogin(String email, String password) {
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("query2"))) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					System.out.println("*****Successfully login******");
					userBean = new UsersBean();
					userBean.setUserId(rs.getInt(1));
					userBean.setUserName(rs.getString(2));
					userBean.setEmail(rs.getString(3));
					userBean.setMobileNumber(rs.getLong(4));
					userBean.setUserType(rs.getString(6));
				}
				return userBean;

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}// End of userlogin()

	@Override
	public boolean registerUser(UsersBean userBean) {
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("registerUser"))) {
			pstmt.setString(1, null);
			pstmt.setString(2, userBean.getUserName());
			pstmt.setString(3, userBean.getEmail());
			pstmt.setLong(4, userBean.getMobileNumber());
			pstmt.setString(5, userBean.getPassword());
		
			int count = pstmt.executeUpdate();
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}//End of registerUser()

	@Override
	public List<UsersBean> showUsers() {
		List<UsersBean> list = new ArrayList<UsersBean>();
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(prop.getProperty("showAllUsers"))){
			while (rs.next()) {
				userBean = new UsersBean();
				userBean.setUserId(rs.getInt(1));
				userBean.setUserName(rs.getString(2));
				userBean.setEmail(rs.getString(3));
				userBean.setMobileNumber(rs.getLong(4));
				list.add(userBean);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}//End of showUsers()

	@Override
	public boolean removeUser(int userId) {
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("removeUser"))) {
			pstmt.setInt(1, userId);
		
			int count = pstmt.executeUpdate();
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}//End of removeUser()

	@Override
	public boolean updateUserPassword(long mobile, String password) {
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("query6"))) {
			
			pstmt.setString(1, password);
			pstmt.setLong(2, mobile);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}//End of updateUserPassword()

	@Override
	public UsersBean getUser(int userId) {
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("query11")))
		{
			pstmt.setInt(1, userId);
			try(ResultSet rs = pstmt.executeQuery())
			{
				if(rs.next())
				{
					userBean = new UsersBean();
					userBean.setUserId(rs.getInt(1));
					userBean.setUserName(rs.getString(2));
					userBean.setEmail(rs.getString(3));
					userBean.setMobileNumber(rs.getLong(4));
					userBean.setUserType(rs.getString(6));
				}
				return userBean;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}//End of getUser()
}// End of class
