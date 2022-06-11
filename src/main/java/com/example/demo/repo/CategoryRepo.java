package com.example.demo.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
	Category findByName(String name);
	
	@Transactional
	@Modifying
	@Query("SELECT e from Category e WHERE e.name LIKE %?1% OR e.description LIKE %?1%")
	List<Category> search(String keyword);
}