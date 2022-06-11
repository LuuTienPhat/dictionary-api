package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Category;
import com.example.demo.entities.EnWord;

public interface CategoryService {
	Category insertCategory(Category Category);

	Category updateCategory(Long id, Category newCategory);

	void deleteCategory(Long id);

	Category getCategory(Long id);

	Category getCategory(String word);
	
	List<Category> getCategories();
	
	List<Category> getCategories(String keyword);
	
	Long count();
}
