package com.example.NamedQuery;

import java.util.List;
import java.util.Map;

import com.example.simple.Product;

public interface ProductDaoNamed {

	List<Product> getProductsByName(String productName);

	void saveProduct(Product product);

	List<Product> getProductsOlderThanYear(int year);

}
