package com.example.NamedQuery;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.criteria.ProductDaoCriteriaImpl;
import com.example.simple.Product;

public class MainAppNamed {

	public static void main(String[] args) {
		// Create a Hibernate SessionFactory
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
			ProductDaoNamed productDao = new ProductDaoNamedImpl();
			((ProductDaoCriteriaImpl) productDao).setSessionFactory(sessionFactory);

			// Create and save a new Product
			Product product1 = new Product();
			product1.setName("Sample Product");
			product1.setManufacturingYear(2023);
			product1.setBatchID("BATCH001");
			productDao.saveProduct(product1);
		    
	        // Use the named query to get products by name
	        List<Product> productsByName = productDao.getProductsByName("Sample Product");
	        System.out.println("Products by Name: " + productsByName);

	        // Use the named native query to get products older than a certain year
	        List<Product> productsOlderThanYear = productDao.getProductsOlderThanYear(2010);
	        System.out.println("Products Older Than Year: " + productsOlderThanYear);
		}


	}

}
