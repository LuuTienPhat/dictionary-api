package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.PartOfSpeech;
import com.example.demo.repo.PartOfSpeechRepo;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.PartOfSpeechService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PartOfSpeechServiceImpl implements PartOfSpeechService {
	private final PartOfSpeechRepo partOfSpeechRepo;

	@Override
	public PartOfSpeech insertPartOfSpeech(PartOfSpeech partOfSpeech) {
		PartOfSpeech result = null;
		try {
			log.info("Saving new part of speech '{}' to the database", partOfSpeech.getName());
			result = partOfSpeechRepo.save(partOfSpeech);
		} catch (Exception e) {
			log.info("Error while inserting '{}' to the database", partOfSpeech.getName());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public PartOfSpeech updatePartOfSpeech(Long id, PartOfSpeech newPartOfSpeech) {
		PartOfSpeech updatedPartOfSpeech = null;
		try {
			log.info("Updating part of speech '{}' to the database", newPartOfSpeech.getName());
			updatedPartOfSpeech = partOfSpeechRepo.findById(id).map(partOfSpeech -> {
				partOfSpeech.setName(newPartOfSpeech.getName());
				return partOfSpeechRepo.save(partOfSpeech);
			}).orElseGet(() -> {
				newPartOfSpeech.setId(id);
				return partOfSpeechRepo.save(newPartOfSpeech);
			});
		} catch (Exception e) {
			log.info("Error while updating '{}' to the database", newPartOfSpeech.getName());
			e.printStackTrace();
		}

		return updatedPartOfSpeech;
	}

	@Override
	public void deletePartOfSpeech(Long id) {
		partOfSpeechRepo.deleteById(id);
	}

	@Override
	public PartOfSpeech getPartOfSpeech(Long id) {
		PartOfSpeech partOfSpeech = partOfSpeechRepo.findById(id).get();
		return partOfSpeech;
	}

	@Override
	public PartOfSpeech getPartOfSpeech(String name) {
		PartOfSpeech partOfSpeech = partOfSpeechRepo.findByName(name);
		return partOfSpeech;
	}

	@Override
	public List<PartOfSpeech> getPartOfSpeeches() {
		return partOfSpeechRepo.findAll();
	}

	@Override
	public List<PartOfSpeech> getPartOfSpeeches(int from, int limit) {
		// TODO Auto-generated method stub
		Page<PartOfSpeech> pages = partOfSpeechRepo.findAll(PageRequest.of(from, limit));
		return pages.toList();
	}

	@Override
	public List<PartOfSpeech> getPartOfSpeeches(String keyword) {
		return partOfSpeechRepo.search(keyword);
	}
}
