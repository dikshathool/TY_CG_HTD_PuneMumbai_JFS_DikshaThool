package com.capgemini.onlinemedicalstorespringrest.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.onlinemedicalstorespringrest.beans.AdminBean;
import com.capgemini.onlinemedicalstorespringrest.beans.UserBean;

@Repository
public class MedicalDAOSpringrestImpl implements MedicalDAO {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Override
	public AdminBean adminLogin(String email, String password) {
		Scanner scanner = new Scanner(System.in);
		EntityManager entityManager = null;
		AdminBean adminBean = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			String jpql = "FROM AdminBean WHERE email =:email AND password =:password";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("email", email);
			query.setParameter("password", password);
			adminBean = (AdminBean) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminBean;
	}// End of adminLogin()

	@Override
	public UserBean userLogin(String email, String password) {
		Scanner scanner = new Scanner(System.in);
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		UserBean userBean = null;
		int userId = 0;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistence");
			entityManager = entityManagerFactory.createEntityManager();
			String jpql = "FROM UserBean WHERE email =:email AND password =:password";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("email", email);
			query.setParameter("password", password);
			userBean = (UserBean) query.getSingleResult();
			userId = userBean.getUserId();

		} catch (Exception e) {
			e.printStackTrace();
		}
		entityManager.close();
		return userBean;
	}// End of userLogin()

	@Override
	public boolean registerUser(UserBean userBean) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		boolean isRegister = false;

		try {
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(userBean);
			transaction.commit();
			entityManager.close();

			isRegister = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isRegister;
	}// End of registerUser()

	@Override
	public List<UserBean> showAllUsers() {
		EntityManager entityManager = null;
		List<UserBean> data = null;

		try {
			entityManager = entityManagerFactory.createEntityManager();

			String jpql = "FROM UserBean";
			Query query = entityManager.createQuery(jpql);
			data = query.getResultList();

			entityManager.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable To Fetch All Users");
		}
		return data;
	}// End of showAllUsers()

	@Override
	public UserBean getUser(int userId) {
		EntityManager entityManager = null;
		UserBean userBean = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();

			String jpql = "from UserBean where userId= :userId";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userId", userId);
			userBean = (UserBean) query.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userBean;
	}// End of getUser()

	@Override
	public boolean removeUser(int userId) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		boolean isDeleted = false;
		try {
			entityManager = entityManagerFactory.createEntityManager();

			String jpql = "DELETE UserBean WHERE userId =:userId";
			transaction = entityManager.getTransaction();
			transaction.begin();
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userId", userId);
			int result = query.executeUpdate();
			transaction.commit();
			entityManager.close();

			isDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}// End of removeUser()

	@Override
	public boolean updateUser(UserBean userBean) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		UserBean existingUser = manager.find(UserBean.class, userBean.getUserId());
		boolean isUpdate = false;

		if (existingUser != null) {
			int userId = userBean.getUserId();
			if (userId > 0) {
				existingUser.setUserId(userId);
			}

			String name = userBean.getUserName();
			if (name != null) {
				existingUser.setUserName(name);
			}

			String email = userBean.getEmail();
			if (email != null) {
				existingUser.setEmail(email);
			}
			String password = userBean.getPassword();
			if (password != null) {
				existingUser.setPassword(password);
			}
		}

		try {
			transaction.begin();
			manager.persist(existingUser);
			transaction.commit();

			isUpdate = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		manager.close();

		return isUpdate;
	}// End of updateUser()

}// End of Class
