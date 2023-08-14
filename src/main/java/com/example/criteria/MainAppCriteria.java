package com.example.criteria;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.simple.Product;

public class MainAppCriteria {

	public static void main(String[] args) {
		// Create a Hibernate SessionFactory
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
			ProductDaoCriteria productDao = new ProductDaoCriteriaImpl();
			((ProductDaoCriteriaImpl) productDao).setSessionFactory(sessionFactory);

			// Create and save a new Product
			Product product1 = new Product();
			product1.setName("Sample Product");
			product1.setManufacturingYear(2023);
			product1.setBatchID("BATCH001");
			productDao.saveProduct(product1);


			// Group products by year and get the count for each year
			Map<Integer, Long> productsByYear = productDao.getProductsGroupedByYear();
			System.out.println("Products Grouped by Year: " + productsByYear);

			// Get all products sorted by name
			List<Product> productsOrderedByName = productDao.getAllProductsOrderedByName();
			System.out.println("Products Ordered by Name: " + productsOrderedByName);
			
		}


	}

}
