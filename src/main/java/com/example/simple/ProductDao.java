package com.example.simple;

import java.util.List;

public interface ProductDao {
    void saveProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(Product product);
}
