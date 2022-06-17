package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Meaning;

public interface MeaningService {
	Meaning insertMeaning(Meaning Meaning);

	Meaning updateMeaning(Long id, Meaning newMeaning);

	void deleteMeaning(Long id);

	Meaning getMeaning(Long id);

	Meaning getMeaning(String word);
	
	List<Meaning> getMeanings();
	
//	List<Meaning> getMeanings(String keyword);
	
	Long count();
}
