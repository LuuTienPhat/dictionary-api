package com.example.demo.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.PartOfSpeech;

public interface PartOfSpeechRepo extends JpaRepository<PartOfSpeech, Long> {
	PartOfSpeech findByName(String name);
	
	List<PartOfSpeech> findAllByName(String name);
	
	@Transactional
	@Modifying
	@Query("SELECT e from PartOfSpeech e WHERE e.name LIKE %?1% OR e.id LIKE %?1%")
	List<PartOfSpeech> search(String keyword);
}
