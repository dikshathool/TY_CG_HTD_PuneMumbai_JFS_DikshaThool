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

import com.capgemini.onlinemedicalstorewithjdbc.bean.CartBean;

public class CartDAOJDBCImpl implements CartDAO {
	FileReader reader = null;
	Properties prop = null;
	
	
	public CartDAOJDBCImpl() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			reader = new FileReader("jdbc.properties");
			prop = new Properties();
			prop.load(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//End of CartDAOJDBCImpl()
	
	//To add product into cart table
	@Override
	public boolean addIntoCart(int userId, int productId) {
		String userName = null;
		String productName = null;
		double productPrice = 0.0;
		
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"))) {
			PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("getUserName"));
			pstmt.setInt(1,userId);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				userName = rs.getString(1);
				
				pstmt = conn.prepareStatement(prop.getProperty("selectProductNamePrice"));
				pstmt.setInt(1, productId);
				ResultSet rs2 = pstmt.executeQuery();

				if (rs2.next()) {
					productName = rs2.getString(1);
					productPrice = rs2.getDouble(2);
				}

			}
			
			pstmt = conn.prepareStatement(prop.getProperty("addIntoCart"));
			pstmt.setInt(1, userId);
			pstmt.setString(2, userName);
			pstmt.setInt(3, productId);
			pstmt.setString(4, productName);
			pstmt.setDouble(5, productPrice);

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
	}//End of addIntoCart(int productId)
	
	//To Show all cart products
	@Override
	public List<CartBean> showCartProducts() {
		List<CartBean> list = new ArrayList<CartBean>();
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(prop.getProperty("showCartProduct"))){
			while (rs.next()) {
				CartBean cartBean = new CartBean();
				cartBean.setCartId(rs.getInt(1));
				cartBean.setUserId(rs.getInt(2));
				cartBean.setUserName(rs.getString(3));
				cartBean.setProductId(rs.getInt(4));
				cartBean.setProductName(rs.getString(5));
				cartBean.setPrice(rs.getDouble(6));
				
				list.add(cartBean);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}//End of showCartProducts()

	@Override
	public boolean removeCartProduct(int userId, int cartId) {
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("removeCartProduct"))) {
			pstmt.setInt(1, cartId);
		
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
	}//To delete product from cart
	
	//To get Total Bill
	@Override
	public double totalBill(int userId) {
		double sum = 0.0;
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("totalBill"))){
			pstmt.setInt(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				sum = rs.getDouble(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}

	@Override
	public boolean deleteCart(int userId) {
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("deleteCart"))) {
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
	}//End of deleteCart()
		
}//End of Class
