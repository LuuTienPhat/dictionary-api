package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.EnWord;

public interface EnWordService {
	EnWord insertEnWord(EnWord enWord);

	EnWord updateEnWord(Long id, EnWord newEnWord);

	void deleteEnWord(Long id);

	EnWord getEnWord(Long id);

	EnWord getEnWord(String word);
	
	List<EnWord> getEnWords();
	
	List<EnWord> getEnWords(int offset, int limit);
	
	List<EnWord> getEnWords(String keyword);
	
	Long count();
}
