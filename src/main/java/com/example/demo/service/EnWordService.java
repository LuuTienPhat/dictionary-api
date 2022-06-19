package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.example.demo.entities.EnWord;
import com.example.demo.entities.Product;

public interface EnWordService {
	EnWord insertEnWord(EnWord enWord);

	EnWord updateEnWord(Long id, EnWord newEnWord);

	void deleteEnWord(Long id);

	EnWord getEnWord(Long id);

	EnWord getEnWord(String word);
	
	List<EnWord> getEnWords();
	
	List<EnWord> getEnWords(int offset, int limit);
	
	List<EnWord> getEnWords(String keyword);
	
	List<EnWord> searchWord(String query, PageRequest pageRequest);
	
	List<EnWord> getEnWordsOrderByViewsDesc();
	
	Long count();
}
