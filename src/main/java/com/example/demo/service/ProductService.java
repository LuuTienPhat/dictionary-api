package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Product;

public interface ProductService {
	Product insertProduct(Product Product);

	Product updateProduct(Long id, Product newProduct);

	void deleteProduct(Long id);

	Product getProduct(Long id);

	Product getProduct(String word);
	
	List<Product> getProducts();
	
	List<Product> getProductsOrderByViewsAsc();
	
	List<Product> getProducts(String keyword);
	
	Long count();
}
