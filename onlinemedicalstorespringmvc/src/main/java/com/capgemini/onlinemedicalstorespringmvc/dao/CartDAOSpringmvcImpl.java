package com.capgemini.onlinemedicalstorespringmvc.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.onlinemedicalstorespringmvc.beans.CartBean;
import com.capgemini.onlinemedicalstorespringmvc.beans.ProductBean;
import com.capgemini.onlinemedicalstorespringmvc.beans.UserBean;

@Repository
public class CartDAOSpringmvcImpl implements CartDAO {

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
		Scanner scanner = new Scanner(System.in);

		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();

			// Get User Name
			String jpql = "from UserBean where userId =:userId";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userId", userId);
			UserBean userBean = (UserBean) query.getSingleResult();
			getUserName = userBean.getUserName();

			// Get Product Name
			String jpql1 = "from ProductBean where productId =:productId";
			Query query1 = entityManager.createQuery(jpql1);
			query1.setParameter("productId", productId);
			ProductBean productBean = (ProductBean) query1.getSingleResult();
			getProductName = productBean.getProductName();
			getPrice = productBean.getPrice();

			// Inser Into Cart
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
	}// End of addIntoCart

	@Override
	public boolean removeFromCart(int cartId) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		boolean isDeleted = false;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();

			transaction.begin();
			CartBean cartBean = entityManager.find(CartBean.class, cartId);
			entityManager.remove(cartBean);
			transaction.commit();
			isDeleted = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		entityManager.close();
		return isDeleted;
	}// End of removeFromCart()

	@Override
	public double totalBill(int userId) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		double totalBill = 0.0;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistence");
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

	@Override
	public List<CartBean> showAllProducts() {
		EntityManager entityManager = null;
		List<CartBean> data = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();

			String jpql = "FROM CartBean";
			Query query = entityManager.createQuery(jpql);
			data = query.getResultList();
			entityManager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}// End of showAllProducts()

}// End of class
