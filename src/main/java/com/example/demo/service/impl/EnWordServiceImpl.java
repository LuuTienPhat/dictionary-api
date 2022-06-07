package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.EnWord;
import com.example.demo.repo.EnWordRepo;
import com.example.demo.service.EnWordService;

@Service
public class EnWordServiceImpl implements EnWordService {

	private EnWordRepo enWordRepo;

	public EnWordServiceImpl(EnWordRepo repository) {
		this.enWordRepo = repository;
	}

	@Override
	public EnWord insertEnWord(EnWord enWord) {
		return enWordRepo.save(enWord);
	}

	@Override
	public EnWord updateEnWord(Long id, EnWord newEnWord) {
		
		return null;
	}

	@Override
	public void deleteEnWord(Long id) {
		enWordRepo.deleteById(id);
	}

	@Override
	public EnWord getEnWord(Long id) {
//		EnWord enWord = enWordRepo.findById(id);
		return null;
	}

	@Override
	public EnWord getEnWord(String word) {
		EnWord enWord = enWordRepo.findByWord(word);
		return enWord;
	}

	@Override
	public List<EnWord> getEnWords() {
		
		return null;
	}
}
