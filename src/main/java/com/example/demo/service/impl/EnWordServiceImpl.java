package com.example.demo.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.EnWord;
import com.example.demo.entities.EnWord;
import com.example.demo.repo.EnWordRepo;
import com.example.demo.repo.EnWordRepo;
import com.example.demo.service.EnWordService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EnWordServiceImpl implements EnWordService {

	private final EnWordRepo enWordRepo;

	@Override
	public EnWord insertEnWord(EnWord enWord) {
		EnWord result = null;
		try {
			result = enWordRepo.save(enWord);
			log.info("Saving new enword '{}' to the database", enWord.getWord());
		} catch (Exception e) {
			log.info("Error while inserting enword '{}' to the database", enWord.getWord());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public EnWord updateEnWord(Long id, EnWord newEnWord) {
		EnWord updatedEnWord = null;
		try {
			log.info("Updating EnWord '{}' to the database", newEnWord.getWord());
			updatedEnWord = enWordRepo.findById(id).map(enWord -> {
				enWord.setWord(newEnWord.getWord());
				enWord.setPronunciation(newEnWord.getPronunciation());
				return enWordRepo.save(enWord);
			}).orElseGet(() -> {
				newEnWord.setId(id);
				return enWordRepo.save(newEnWord);
			});
		} catch (Exception e) {
			log.info("Error while updating EnWord '{}' to the database", newEnWord.getWord());
			e.printStackTrace();
		}

		return updatedEnWord;
	}

	@Override
	public void deleteEnWord(Long id) {
		enWordRepo.deleteById(id);
	}

	@Override
	public EnWord getEnWord(Long id) {
		EnWord enWord = null;
		try {
			enWord = enWordRepo.findById(id).get();
			log.info("EnWord found '{}'", enWord.getWord());
		} catch (Exception e) {
			log.info("EnWord not found '{}'", enWord.getWord());
			e.printStackTrace();
		}
		return enWord;
	}

	@Override
	public EnWord getEnWord(String word) {
		EnWord enWord = null;
		try {
			enWord = enWordRepo.findByWord(word);
			log.info("EnWord found '{}'", enWord.getWord());
		} catch (Exception e) {
			log.info("EnWord not found '{}'", enWord.getWord());
			e.printStackTrace();
		}
		return enWord;
	}

	@Override
	public List<EnWord> getEnWords() {
		return null;
	}

	@Override
	public List<EnWord> getEnWords(int offset, int limit) {
		Page<EnWord> page = enWordRepo.findAll(PageRequest.of(offset, limit));
		return page.toList();
	}

	@Override
	public List<EnWord> getEnWords(String keyword) {
		return enWordRepo.search(keyword);
	}

	@Override
	public Long count() {
		return enWordRepo.count();
	}

	@Override
	public List<EnWord> searchWord(String query, PageRequest pageRequest) {
		return enWordRepo.searchWord(query, pageRequest);
	}

	@Override
	public List<EnWord> getEnWordsOrderByViewsDesc() {
		// TODO Auto-generated method stub
		return enWordRepo.findTop5ByOrderByViewsDesc();
	}
	
}
