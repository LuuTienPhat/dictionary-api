package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Meaning;
import com.example.demo.entities.PartOfSpeech;
import com.example.demo.entities.EnWord;
import com.example.demo.repo.MeaningRepo;
import com.example.demo.service.MeaningService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MeaningServiceImpl implements MeaningService {
	private final MeaningRepo MeaningRepo;

	@Override
	public Meaning insertMeaning(Meaning Meaning) {
		Meaning result = null;
		try {
			log.info("Saving new Meaning '{}' to the database", Meaning.getMeaning());
			result = MeaningRepo.save(Meaning);
		} catch (Exception e) {
			log.info("Error while inserting Meaning '{}' to the database", Meaning.getMeaning());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Meaning updateMeaning(Long id, Meaning newMeaning) {
		Meaning updatedMeaning = null;
		try {
			log.info("Updating Meaning '{}' to the database", newMeaning.getMeaning());
			updatedMeaning = MeaningRepo.findById(id).map(meaning -> {
				meaning.setMeaning(newMeaning.getMeaning());
				meaning.setEnWord(newMeaning.getEnWord());
				meaning.setPartOfSpeech(newMeaning.getPartOfSpeech());
				return MeaningRepo.save(meaning);
			}).orElseGet(() -> {
				newMeaning.setId(id);
				return MeaningRepo.save(newMeaning);
			});
		} catch (Exception e) {
			log.info("Error while updating Meaning '{}' to the database", newMeaning.getMeaning());
			e.printStackTrace();
		}

		return updatedMeaning;
	}

	@Override
	public void deleteMeaning(Long id) {
		MeaningRepo.deleteById(id);
	}

	@Override
	public Meaning getMeaning(Long id) {
		Meaning Meaning = MeaningRepo.findById(id).get();
		return Meaning;
	}

	@Override
	public Meaning getMeaning(String name) {
//		Meaning Meaning = MeaningRepo.findByName(name);
//		return Meaning;
		return null;
	}

	@Override
	public List<Meaning> getMeanings() {
		return MeaningRepo.findAll();
	}

	@Override
	public Long count() {
		return MeaningRepo.count();
	}

//	@Override
//	public List<Meaning> getMeanings(String keyword) {
//		// TODO Auto-generated method stub
//		return MeaningRepo.search(keyword);
//	}

//	@Override
//	public List<Meaning> getMeanings(int from, int limit) {
//		// TODO Auto-generated method stub
//		Page<Meaning> pages = MeaningRepo.findAll(PageRequest.of(from, limit));
//		return pages.toList();
//	}
}
