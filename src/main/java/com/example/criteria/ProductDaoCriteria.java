package com.example.criteria;

import java.util.List;
import java.util.Map;

import com.example.simple.Product;

public interface ProductDaoCriteria {
	List<Product> getAllProductsOrderedByName();
	Map<Integer, Long> getProductsGroupedByYear();
	void saveProduct(Product product1);
}
