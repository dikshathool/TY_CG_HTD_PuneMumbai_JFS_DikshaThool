package com.capgemini.onlinemedicalstorespringmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.onlinemedicalstorespringmvc.beans.AdminBean;
import com.capgemini.onlinemedicalstorespringmvc.beans.ProductBean;
import com.capgemini.onlinemedicalstorespringmvc.beans.UserBean;

@Repository
public class MedicalDAOSpringMVCImpl implements MedicalDAO {
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public AdminBean adminLogin(String email, String password) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		String jpql = "FROM AdminBean WHERE email = :email and password = :password";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("email", email);
		query.setParameter("password", password);
		AdminBean adminBean = null;
		
		try {
			adminBean = (AdminBean) query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return adminBean;
	}//End of adminLogin()

	@Override
	public UserBean userLogin(String email, String password) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "FROM UserBean WHERE email = :email and password = :password";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("email", email);
		query.setParameter("password", password);
		UserBean userBean = null;
		
		try {
			userBean = (UserBean) query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return userBean;
	}//End of userLogin()

	@Override
	public boolean userRegister(UserBean userBean) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		boolean isAdded = false;
		try {
			tx.begin();
			entityManager.persist(userBean);
			tx.commit();
			isAdded = true;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		entityManager.close();
		return isAdded;
	}//End of userRegister()

	@Override
	public List<ProductBean> getAllProducts() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "from ProductBean";
		Query query = entityManager.createQuery(jpql);
		
		List<ProductBean> productsList = null;
		try {
			productsList = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productsList;
	}//End of getAllProducts()

	@Override
	public List<UserBean> getAllUsers() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "from UserBean";
		Query query = entityManager.createQuery(jpql);
		
		List<UserBean> usersList = null;
		try {
			usersList = query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usersList;
	}//End of getAllUsers()

	@Override
	public boolean removeUser(int userId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean isDeleted = false;

		try {
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();
			UserBean userBean = entityManager.find(UserBean.class, userId);
			entityManager.remove(userBean);
			tx.commit();
			isDeleted = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		entityManager.close();
		return isDeleted;
	}//End of removeUser()

	
}//End of class
