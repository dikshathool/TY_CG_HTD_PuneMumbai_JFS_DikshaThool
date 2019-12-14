package com.capgemini.onlinemedicalstorespringmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onlinemedicalstorespringmvc.beans.ProductBean;

@Repository
public class ProductDAOSpringrestImpl implements ProductDAO {
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public boolean addProduct(ProductBean productBean) {
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		boolean isAdded = false;
		try {
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
	}// End of addProduct()

	@Override
	public boolean updateProduct(ProductBean productBean) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean isUpdated = false;
		ProductBean existingProduct = entityManager.find(ProductBean.class, productBean.getProductId());

		if (productBean != null) {
			int productId = productBean.getProductId();
			if (productId > 0) {
				existingProduct.setProductId(productId);
			}

			String productName = productBean.getProductName();
			if (productName != null) {
				existingProduct.setProductName(productName);
			}

			String productBrand = productBean.getProductBrand();
			if (productBrand != null) {
				existingProduct.setProductBrand(productBrand);
			}

			String category = productBean.getCategory();
			if (category != null) {
				existingProduct.setCategory(category);
			}

			double price = productBean.getPrice();
			if (price > 0) {
				existingProduct.setPrice(price);
			}

			String details = productBean.getDetails();
			if (details != null) {
				existingProduct.setDetails(details);
			}

			String available = productBean.getAvailable();
			if (available != null) {
				existingProduct.setAvailable(available);
			}
		}

		try {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(existingProduct);
			transaction.commit();
			isUpdated = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		entityManager.close();

		return isUpdated;

	}// End of updateProduct()

	@Override
	public boolean removeProduct(int productId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		boolean isDeleted = false;

		try {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			ProductBean productBean = entityManager.find(ProductBean.class, productId);
			entityManager.remove(productBean);
			transaction.commit();
			isDeleted = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		entityManager.close();
		return isDeleted;
	}// End of removeProduct()

	@Override
	public List<ProductBean> showAllProducts() {

		List<ProductBean> productsList = null;
		try {
			EntityManager manager = entityManagerFactory.createEntityManager();
			String jpql = "from ProductBean";
			Query query = manager.createQuery(jpql);

			productsList = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return productsList;
	}// End of showAllProducts()

	@Override
	public List<ProductBean> categoryProducts(String category) {
		List<ProductBean> productsList = null;
		try {
			EntityManager manager = entityManagerFactory.createEntityManager();
			String jpql = "FROM ProductBean WHERE category =:category";
			Query query = manager.createQuery(jpql);
			query.setParameter("category", category);
			
			productsList = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productsList;
	}// End of categoryProducts()

}// End of Class
