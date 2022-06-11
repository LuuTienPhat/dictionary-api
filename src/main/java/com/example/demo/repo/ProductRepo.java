package com.example.demo.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	Product findByName(String name);
	
	@Transactional
	@Modifying
	@Query("SELECT e from Product e WHERE e.name LIKE %?1%")
	List<Product> search(String keyword);
}
