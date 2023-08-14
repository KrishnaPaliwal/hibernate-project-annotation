package com.example.NamedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.simple.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductDaoNamedImpl implements ProductDaoNamed {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveProduct(Product product) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(product);
		tx.commit();
		session.close();
	}

	@Override
	public List<Product> getProductsByName(String productName) {
		Session session = this.sessionFactory.openSession();
		Query<Product> query = session.createNamedQuery("Product.findByName", Product.class);
		query.setParameter("productName", productName);
		List<Product> products = query.getResultList();
		session.close();
		return products;
	}

	@Override
	public List<Product> getProductsOlderThanYear(int year) {
		Session session = this.sessionFactory.openSession();
		Query<Product> query = session.createNativeQuery("Product.findOlderThanYear", Product.class);
		query.setParameter("year", year);
		List<Product> products = query.getResultList();
		session.close();
		return products;
	}

}
