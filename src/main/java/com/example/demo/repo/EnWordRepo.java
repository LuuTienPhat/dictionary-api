package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.PageRequest;

import com.example.demo.entities.EnWord;

public interface EnWordRepo extends JpaRepository<EnWord, Long> {
	EnWord findByWord(String word);
	
	@Transactional
	@Modifying
	@Query("SELECT e from EnWord e WHERE e.word LIKE %?1%")
	List<EnWord> search(String keyword);
	
	@Query("SELECT e FROM EnWord e WHERE e.word LIKE ?1%")
	List<EnWord> searchWord(String query, PageRequest pageRequest);
	
	@Query("SELECT e FROM EnWord e WHERE e.word LIKE ?1%")
	List<EnWord> searchWord(String query);
}
