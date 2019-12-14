package com.capgemini.onlinemedicalstorespringmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.onlinemedicalstorespringmvc.beans.MessageBean;

@Repository
public class MessageDAOSpringmvcImpl implements MessageDAO {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Override
	public boolean sendMessage(int userId, String message, String messageType) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		boolean isSent = false;
		try {
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
		EntityManager entityManager = null;
		List<MessageBean> messageBean = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();

			String messageType = "Question";
			String jpql = "FROM MessageBean WHERE messageType =:messageType";
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
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		boolean isSent = false;
		try {
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
		EntityManager entityManager = null;
		List<MessageBean> messageBean = null;
		try {
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
