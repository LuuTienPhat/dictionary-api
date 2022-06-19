package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.EnWord;
import com.example.demo.entities.SavedWord;

public interface SavedWordRepository extends JpaRepository<SavedWord, Long> {
	
	@Query("SELECT e FROM EnWord e WHERE e.id IN (SELECT s.enWord FROM SavedWord as s inner join User as u on u.id=s.user where u.id=?1)")
	List<EnWord> findSavedWord(Long userId);
	@Query("SELECT s.enWord.id FROM SavedWord s WHERE s.user.id=?1")
	List<String> getAllSavedWordId(Long userId);
	@Transactional
	@Modifying
	@Query("DELETE FROM SavedWord s WHERE s.user.id =?1 AND s.enWord.id=?2")
	void deleteSavedWord(Long userId, Long wordId);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO saved_word (user_id, en_word_id) VALUES (?1, ?2)", nativeQuery = true)
	void insertSavedWord(Long userId, Long wordId);
	
}
