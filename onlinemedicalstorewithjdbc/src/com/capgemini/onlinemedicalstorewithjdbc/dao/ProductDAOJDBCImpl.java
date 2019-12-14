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

import com.capgemini.onlinemedicalstorewithjdbc.bean.ProductBean;
import com.capgemini.onlinemedicalstorewithjdbc.bean.UserBean;

public class ProductDAOJDBCImpl implements ProductDAO {

	FileReader reader = null;
	Properties prop = null;
	ProductBean productBean;

	public ProductDAOJDBCImpl() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			reader = new FileReader("jdbc.properties");
			prop = new Properties();
			prop.load(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// End of ProductDAOJDBCImpl()

	@Override
	public boolean addProduct(ProductBean productBean) {
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("addProduct"))) {
			pstmt.setInt(1, productBean.getProductId());
			pstmt.setString(2, productBean.getProductName());
			pstmt.setString(3, productBean.getProductBrand());
			pstmt.setString(4, productBean.getCategory());
			pstmt.setDouble(5, productBean.getPrice());
			pstmt.setString(6, productBean.getDetails());
			pstmt.setString(7, productBean.getAvailable());

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
	}//End of addProduct()

	@Override
	public boolean updateProduct(int productId, double price, String available) {
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("query8"))) {
			
			pstmt.setDouble(1, price);
			pstmt.setString(2, available);
			pstmt.setInt(3, productId);
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
	}//End of updateProduct()

	@Override
	public boolean removeProduct(int productId) {
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("query9"))) {
			pstmt.setInt(1, productId);
		
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
	}

	@Override
	public List<ProductBean> showAllProducts() {
		List<ProductBean> list = new ArrayList<ProductBean>();
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(prop.getProperty("query10"))){
			while (rs.next()) {
				productBean = new ProductBean();
				productBean.setProductId(rs.getInt(1));
				productBean.setProductName(rs.getString(2));
				productBean.setProductBrand(rs.getString(3));
				productBean.setCategory(rs.getString(4));
				productBean.setPrice(rs.getInt(5));
				productBean.setDetails(rs.getString(6));
				productBean.setAvailable(rs.getString(7));
				
				list.add(productBean);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}//End of showAllProduct()

	@Override
	public ProductBean getProductInfo(int productId) {
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("query11")))
		{
			pstmt.setInt(1, productId);
			try(ResultSet rs = pstmt.executeQuery())
			{
				if(rs.next())
				{
					productBean = new ProductBean();
					productBean.setProductId(rs.getInt(1));
					productBean.setProductName(rs.getString(2));
					productBean.setProductBrand(rs.getString(3));
					productBean.setPrice(rs.getInt(4));
					productBean.setDetails(rs.getString(5));
				}
				return productBean;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}//End of getProductInfo()

	@Override
	public List<ProductBean> categoryProducts(String category) {
		List<ProductBean> list = new ArrayList<ProductBean>();
		try (Connection conn = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("user"),
				prop.getProperty("password"));
				PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("query11")))
		{
			pstmt.setString(1, category);
			try(ResultSet rs = pstmt.executeQuery())
			{
				while(rs.next())
				{
					productBean = new ProductBean();
					productBean.setProductId(rs.getInt(1));
					productBean.setProductName(rs.getString(2));
					productBean.setProductBrand(rs.getString(3));
					productBean.setCategory(rs.getString(4));
					productBean.setPrice(rs.getInt(5));
					productBean.setDetails(rs.getString(6));
					productBean.setAvailable(rs.getString(7));
					
					list.add(productBean);
				}
				return list;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	
}// End of class
