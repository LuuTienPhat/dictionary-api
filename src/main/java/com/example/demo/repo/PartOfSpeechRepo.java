package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.PartOfSpeech;

public interface PartOfSpeechRepo extends JpaRepository<PartOfSpeech, Long> {
	PartOfSpeech findByName(String name);
}
