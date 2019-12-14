package com.capgemini.onlinemedicalstorespringboot.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.onlinemedicalstorespringboot.beans.CartBean;
import com.capgemini.onlinemedicalstorespringboot.beans.ProductBean;
import com.capgemini.onlinemedicalstorespringboot.beans.UsersBean;

@Repository
public class CartDAOSpringrestImpl implements CartDAO {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Override
	public boolean addIntoCart(int userId, int productId) {
		String getUserName = null;
		String getProductName = null;
		double getPrice = 0;
		boolean isAdded = false;

		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();

		try {
			

			// Get User Name
			String jpql = "from UsersBean where userId =:userId";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userId", userId);
			UsersBean userBean = (UsersBean) query.getSingleResult();
			getUserName = userBean.getUserName();

			// Get Product Name
			String jpql1 = "from ProductBean where productId =:productId";
			Query query1 = entityManager.createQuery(jpql1);
			query1.setParameter("productId", productId);
			ProductBean productBean = (ProductBean) query1.getSingleResult();
			getProductName = productBean.getProductName();
			getPrice = productBean.getPrice();

			// Insert Into Cart
			CartBean cartBean = new CartBean();
			cartBean.setUserId(userId);
			cartBean.setUserName(getUserName);
			cartBean.setProductId(productId);
			cartBean.setProductName(getProductName);
			cartBean.setPrice(getPrice);

			transaction.begin();
			entityManager.persist(cartBean);
			transaction.commit();
			isAdded = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAdded;
	}// End of addIntoCart()

	@Override
	public boolean removeFromCart(int userId, int cartId) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		boolean isDeleted = false;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();

			String jpql = "FROM CartBean where userId =:userId AND cartId =:cartId";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userId", userId);
			query.setParameter("cartId", cartId);
			CartBean cartBean = (CartBean) query.getSingleResult();
			transaction.begin();
			entityManager.remove(cartBean);
			transaction.commit();
			entityManager.close();

			isDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}// End of removeFromCart()

	@Override
	public List<CartBean> showAllProducts(int userId) {
		EntityManager entityManager = null;
		List<CartBean> data = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();

			String jpql = "FROM CartBean WHERE userId =: userId";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userId", userId);
			data = query.getResultList();
			entityManager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}// End of showAllProducts()

	@Override
	public double totalBill(int userId) {
		EntityManager entityManager = null;
		double totalBill = 0.0;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			String jpql = "SELECT sum(price) FROM CartBean WHERE userId= :userId";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userId", userId);
			totalBill = (double) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalBill;
	}// End of totalBill()

	@Override
	public boolean deleteCart(int userId) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		boolean isDeleted = false;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();

			String jpql = "DELETE FROM CartBean WHERE userId =:userId";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userId", userId);
			transaction.begin();
			query.executeUpdate();
			transaction.commit();
			entityManager.close();

			isDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}// End of deleteCart()

}// End of Class
