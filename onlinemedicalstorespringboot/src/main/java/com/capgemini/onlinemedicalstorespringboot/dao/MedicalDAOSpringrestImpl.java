package com.capgemini.onlinemedicalstorespringboot.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.onlinemedicalstorespringboot.beans.AdminBean;
import com.capgemini.onlinemedicalstorespringboot.beans.UsersBean;

@Repository
public class MedicalDAOSpringrestImpl implements MedicalDAO {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public List<UsersBean> showAllUsers() {
		EntityManager entityManager = null;
		List<UsersBean> data = null;
		String userType="User";
		try {
			entityManager = entityManagerFactory.createEntityManager();

			String jpql = "FROM UsersBean where userType= :userType";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userType", userType);
			data = query.getResultList();

			entityManager.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable To Fetch All Users");
		}
		return data;
	}// End of showAllUsers()

	@Override
	public UsersBean getUser(int userId) {
		EntityManager entityManager = null;
		UsersBean userBean = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();

			String jpql = "from UsersBean where userId= :userId ";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userId", userId);
			userBean = (UsersBean) query.getSingleResult();

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

			String jpql = "DELETE UsersBean WHERE userId =:userId ";
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
	public boolean updateUser(UsersBean userBean) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		UsersBean existingUser = manager.find(UsersBean.class, userBean.getUserId());
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

	@Override
	public UsersBean usersLogin(String email, String password) {
		EntityManager manager = entityManagerFactory.createEntityManager();

		String jpql = "FROM UsersBean WHERE email= :email AND password= :password";
		Query query = manager.createQuery(jpql);
		query.setParameter("email", email);
		query.setParameter("password", password);

		UsersBean userBean = null;
		try {
			userBean = (UsersBean) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userBean;
	}//usersLogin()

	@Override
	public boolean userRegistration(UsersBean userBean) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		boolean isAdded = false;
		
		try {
			transaction.begin();
			manager.persist(userBean);
			transaction.commit();
			isAdded = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		manager.close();
		return isAdded;
	}//End of userRegistration()

}// End of Class
