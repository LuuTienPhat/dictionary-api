package com.example.demo.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Example;
import com.example.demo.entities.Meaning;

public interface ExampleRepo extends JpaRepository<Example, Long> {
	List<Example> findAllByMeaning(Meaning meaning);
	
	List<Example> findAllByMeaningId(Long id);
//	
//	@Transactional
//	@Modifying
//	@Query("SELECT e from Example e WHERE e.name LIKE %?1% OR e.description LIKE %?1%")
//	List<Example> search(String keyword);
}