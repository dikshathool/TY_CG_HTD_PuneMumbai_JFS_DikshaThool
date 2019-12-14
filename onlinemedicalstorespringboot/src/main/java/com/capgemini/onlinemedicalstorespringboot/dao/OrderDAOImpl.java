package com.capgemini.onlinemedicalstorespringboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.onlinemedicalstorespringboot.beans.CartBean;
import com.capgemini.onlinemedicalstorespringboot.beans.OrderHistoryBean;
import com.capgemini.onlinemedicalstorespringboot.beans.PaymentDetails;

@Repository
public class OrderDAOImpl implements OrderDAO {
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Override
	public boolean payment(int userId, String address) {
		PaymentDetails paymentDetails = new PaymentDetails();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction trans = entityManager.getTransaction();

		boolean isAdded = false;
		try {
			String jpql1 = "FROM CartBean where UserId= :userId";
			Query query1 = entityManager.createQuery(jpql1);
			query1.setParameter("userId", userId);
			List<CartBean> cartBeans = query1.getResultList();

			OrderHistoryBean orderBean = new OrderHistoryBean();
			for (CartBean cartBean : cartBeans) {
				orderBean.setOrderId(cartBean.getCartId());
				orderBean.setUserId(cartBean.getUserId());
				orderBean.setAddress(address);
				orderBean.setPrice(cartBean.getPrice());
				orderBean.setProductId(cartBean.getProductId());
				orderBean.setUserName(cartBean.getUserName());
				orderBean.setProductName(cartBean.getProductName());

				trans.begin();
				entityManager.remove(cartBean);
				entityManager.persist(orderBean);
				trans.commit();
				paymentDetails.setStatus("Done");
				isAdded = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAdded;

	}// End of payment

	@Override
	public boolean addOrderIntoHistory(OrderHistoryBean orderBean) {
		boolean isAdded=false;
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction trans = manager.getTransaction();
		try {
			trans.begin();
			manager.persist(orderBean);
			trans.commit();
			isAdded=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAdded;
	}// End of addOrderIntoHistory()

	@Override
	public List<OrderHistoryBean> showOrder(int userId) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		String jpql = "from OrderHistoryBean where userId = :userId";
		Query query = manager.createQuery(jpql);
		query.setParameter("userId", userId);

		List<OrderHistoryBean> list = query.getResultList();
		
		return list;
	}// End of showOrder()

}// End of class
