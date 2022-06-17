package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Example;
import com.example.demo.entities.EnWord;
import com.example.demo.repo.ExampleRepo;
import com.example.demo.service.ExampleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ExampleServiceImpl implements ExampleService {
	private final ExampleRepo exampleRepo;

	@Override
	public Example insertExample(Example example) {
		Example result = null;
		try {
			log.info("Saving new Example '{}' to the database", example.getExample());
			result = exampleRepo.save(example);
		} catch (Exception e) {
			log.info("Error while inserting Example '{}' to the database", example.getExample());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Example updateExample(Long id, Example newExample) {
		Example updatedExample = null;
		try {
			log.info("Updating Example '{}' to the database", newExample.getExample());
			updatedExample = exampleRepo.findById(id).map(example -> {
				
				example.setExample(newExample.getExample());
				example.setExampleMeaning(newExample.getExampleMeaning());
				example.setMeaning(newExample.getMeaning());
				
				return exampleRepo.save(example);
			}).orElseGet(() -> {
				newExample.setId(id);
				return exampleRepo.save(newExample);
			});
		} catch (Exception e) {
			log.info("Error while updating Example '{}' to the database", newExample.getExample());
			e.printStackTrace();
		}

		return updatedExample;
	}

	@Override
	public void deleteExample(Long id) {
		exampleRepo.deleteById(id);
	}

	@Override
	public Example getExample(Long id) {
		Example Example = exampleRepo.findById(id).get();
		return Example;
	}

//	@Override
//	public Example getExample(String name) {
//		Example Example = exampleRepo.findByName(name);
//		return Example;
//	}

//	@Override
//	public List<Example> getCategories() {
//		return exampleRepo.findAll();
//	}

	@Override
	public Long count() {
		return exampleRepo.count();
	}

	@Override
	public List<Example> getExamples() {
		return null;
	}

//	@Override
//	public List<Example> getCategories(String keyword) {
//		// TODO Auto-generated method stub
//		return exampleRepo.search(keyword);
//	}

//	@Override
//	public List<Example> getCategories(int from, int limit) {
//		// TODO Auto-generated method stub
//		Page<Example> pages = exampleRepo.findAll(PageRequest.of(from, limit));
//		return pages.toList();
//	}
}
