package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.PartOfSpeech;


public interface PartOfSpeechService {
	PartOfSpeech insertPartOfSpeech(PartOfSpeech PartOfSpeech);

	PartOfSpeech updatePartOfSpeech(Long id, PartOfSpeech newPartOfSpeech);

	void deletePartOfSpeech(Long id);

	PartOfSpeech getPartOfSpeech(Long id);

	PartOfSpeech getPartOfSpeech(String word);

	List<PartOfSpeech> getPartOfSpeeches();
	
	List<PartOfSpeech> getPartOfSpeeches(String keyword);
	
	List<PartOfSpeech> getPartOfSpeeches(int from, int limit);
	
	Long count();
}
