package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.User;
import com.example.demo.entities.EnWord;
import com.example.demo.entities.Feedback;
import com.example.demo.entities.SavedWord;
import com.example.demo.repo.FeedbackRepo;
import com.example.demo.repo.SavedWordRepo;
import com.example.demo.service.SavedWordService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SaveWordServiceImpl implements SavedWordService{
	private final SavedWordRepo savedWordRepo;

	@Override
	public SavedWord getSavedWord(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SavedWord> getSavedWordList(User user) {
		List<SavedWord> result = null;
//		try {
//			result = (SavedWord) savedWordRepo.findByUser(user);
//			log.info("Feedback found in database {}", word.getId());
//		} catch (Exception e) {
//			log.info("Feedback not found in database {}", word.getId());
//			e.printStackTrace();
//		}
		return result;
	}

	@Override
	public SavedWord getSavedWord(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
