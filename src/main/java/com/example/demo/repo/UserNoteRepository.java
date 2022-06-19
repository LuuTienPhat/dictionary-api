package com.example.demo.repo;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.EnWord;
import com.example.demo.entities.UserNote;

public interface UserNoteRepository extends JpaRepository<UserNote, Long> {
	@Query("SELECT u FROM UserNote u WHERE u.enWord.id=?1 AND u.user.id=?2")
	List<UserNote> getNote(Long wordId, Long userId);
	@Transactional
	@Modifying
	@Query(value="INSERT INTO user_note(word_id, user_id, note) VALUES (?1,?2,?3)", nativeQuery=true)
	void saveOneNote(Long wordId, Long userId, String note);
	@Transactional
	@Modifying
	@Query(value = "UPDATE user_note SET note = ?3 WHERE word_id = ?1 AND user_id = ?2", nativeQuery=true)
	void updateOneNote(Long wordId, Long userId, String note);
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM user_note WHERE word_id = ?2 AND user_id = ?1", nativeQuery=true)
	void deleteNote(Long userId, Long wordId);

}
