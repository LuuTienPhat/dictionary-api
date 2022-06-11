package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.EnWord;

public interface EnWordRepo extends JpaRepository<EnWord, Long> {
	EnWord findByWord(String word);
	
	@Transactional
	@Modifying
	@Query("SELECT e from EnWord e WHERE e.word LIKE %?1%")
	List<EnWord> search(String keyword);
}
