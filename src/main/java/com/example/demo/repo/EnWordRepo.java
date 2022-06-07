package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.EnWord;

public interface EnWordRepo extends JpaRepository<EnWord, Long> {
	Optional<EnWord> findById(Long id);
	EnWord findByWord(String word);
}
