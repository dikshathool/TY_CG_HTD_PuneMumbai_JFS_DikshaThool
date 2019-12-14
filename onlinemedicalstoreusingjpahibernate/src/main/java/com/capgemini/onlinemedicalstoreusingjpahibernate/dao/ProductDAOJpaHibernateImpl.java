package com.capgemini.onlinemedicalstoreusingjpahibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.capgemini.onlinemedicalstoreusingjpahibernate.beans.ProductBean;

public class ProductDAOJpaHibernateImpl implements ProductDAO  {

	@Override
	public boolean addProduct(ProductBean productBean) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		boolean isAdded = false;
		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistence");
			entityManager = entityManagerFactory.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(productBean);
			transaction.commit();
			entityManager.close();
			
			isAdded = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAdded;
	}//End of addProduct()

	@Override
	public boolean updateProduct(int productId, double price, String available) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		boolean isUpdated = false;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistence");
			entityManager = entityManagerFactory.createEntityManager();

			String jpql = "UPDATE ProductBean SET price =:price, available =:available where productId =:productId";
			transaction = entityManager.getTransaction();
			transaction.begin();
			Query query = entityManager.createQuery(jpql);
			query.setParameter("price", price);
			query.setParameter("available", available);
			query.setParameter("productId", productId);
			int result = query.executeUpdate();
			transaction.commit();
			entityManager.close();
			
			isUpdated = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}//End of updateProduct()

	@Override
	public boolean removeProduct(int productId) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		boolean isRemove = false;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistence");
			entityManager = entityManagerFactory.createEntityManager();

			String jpql = "DELETE ProductBean WHERE productId =:productId";
			transaction = entityManager.getTransaction();
			transaction.begin();
			Query query = entityManager.createQuery(jpql);
			query.setParameter("productId", productId);
			query.executeUpdate();
			transaction.commit();
			entityManager.close();
			isRemove = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isRemove;
	}//End of removeProduct()

	@Override
	public List<ProductBean> categoryProducts(String category) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistence");
			entityManager = entityManagerFactory.createEntityManager();

			String jpql = "FROM ProductBean WHERE category =:category";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("category", category);
			List<ProductBean> data = query.getResultList();
			for (ProductBean productBean : data) {
				System.out.println("Product ID = " + productBean.getProductId());
				System.out.println("Product Name = " + productBean.getProductName());
				System.out.println("Product Brand = " + productBean.getProductBrand());
				System.out.println("Category = " + productBean.getCategory());
				System.out.println("Price = " + productBean.getPrice());
				System.out.println("Details = " + productBean.getDetails());
				System.out.println("Available = " + productBean.getAvailable());
				System.out.println("---------------------------------------------------------");
			}
			entityManager.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable To Show Product or No Such Category Products Present!!!");
		}
		return null;
	}//End of categoryProducts()

	@Override
	public List<ProductBean> showAllProducts() {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		List<ProductBean> data = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistence");
			entityManager = entityManagerFactory.createEntityManager();

			String jpql = "FROM ProductBean";
			Query query = entityManager.createQuery(jpql);
			data = query.getResultList();
			entityManager.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return data;

	}//End of showAllProducts()
	


}//End of class
