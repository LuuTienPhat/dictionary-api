package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.User;
import com.example.demo.entities.SavedWord;

public interface SavedWordService {
	SavedWord getSavedWord(Long id);

	SavedWord getSavedWord(User user);
	
	List<SavedWord> getSavedWordList(User user);
}
