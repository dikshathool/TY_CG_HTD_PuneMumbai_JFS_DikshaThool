package com.capgemini.onlinemedicalstoreusingjpahibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.capgemini.onlinemedicalstoreusingjpahibernate.beans.MessageBean;

public class MessageDAOJpaHibernateImpl implements MessageDAO {

	@Override
	public boolean sendMessage(int userId, String message, String messageType) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		boolean isSent = false;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistence");
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			MessageBean messageBean = new MessageBean();
			messageBean.setUserId(userId);
			messageBean.setMessage(message);
			messageBean.setMessageType(messageType);
			transaction.begin();
			entityManager.persist(messageBean);
			transaction.commit();
			isSent = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSent;
	}// End of sendMessage()

	@Override
	public List<MessageBean> getMessage() {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		List<MessageBean> messageBean = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistence");
			entityManager = entityManagerFactory.createEntityManager();

			String messageType = "Question";
			String jpql = "FROM MessageBean WHERE userId =:userId, messageType =:messageType";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("messageType", messageType);
			messageBean = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return messageBean;
	}// End of getMessage()

	@Override
	public boolean sendResponse(int userId, String message, String messageType) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		boolean isSent = false;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistence");
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			MessageBean messageBean = new MessageBean();
			messageBean.setUserId(userId);
			messageBean.setMessageType(messageType);
			messageBean.setMessage(message);

			transaction.begin();
			entityManager.persist(messageBean);
			transaction.commit();
			isSent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSent;
	}// End of sendResponse()

	@Override
	public List<MessageBean> getResponse(int userId) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		List<MessageBean> messageBean = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistence");
			entityManager = entityManagerFactory.createEntityManager();
			
			String messageType = "Answer";
			String jpql = "FROM MessageBean WHERE messageType= :messageType";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("messageType", messageType);
			messageBean = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return messageBean;
	}// End of getResponse()

}// End of Class
