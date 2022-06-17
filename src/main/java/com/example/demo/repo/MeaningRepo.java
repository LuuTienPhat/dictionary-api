package com.example.demo.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.Meaning;

public interface MeaningRepo extends JpaRepository<Meaning, Long> {
//	List<Meaning> findByEnWordId(Long id);
	
//	@Transactional
//	@Modifying
//	@Query("SELECT e from Meaning e WHERE e.meanig LIKE %?1% OR e.id LIKE %?1%")
//	List<Meaning> search(String keyword);
}