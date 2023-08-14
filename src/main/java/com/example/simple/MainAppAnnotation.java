package com.example.simple;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainAppAnnotation {

    public static void main(String[] args) {
        // Create a Hibernate SessionFactory
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            ProductDao productDao = new ProductDaoImpl();
            ((ProductDaoImpl) productDao).setSessionFactory(sessionFactory);

            // Create and save a new Product
            Product product1 = new Product();
            product1.setName("Sample Product");
            product1.setManufacturingYear(2023);
            product1.setBatchID("BATCH001");
            productDao.saveProduct(product1);

            // Retrieve and print the saved Product
            Long productId = product1.getProductId();
            Product retrievedProduct = productDao.getProductById(productId);
            System.out.println("Retrieved Product: " + retrievedProduct);

            // Update the Product
            retrievedProduct.setName("Updated Product");
            productDao.updateProduct(retrievedProduct);

            // Delete the Product
            //productDao.deleteProduct(retrievedProduct);

            // Retrieve all Products
            System.out.println("All Products: " + productDao.getAllProducts());
        }
    }
}
