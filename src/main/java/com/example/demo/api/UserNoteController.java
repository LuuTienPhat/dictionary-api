package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.entities.EnWord;
import com.example.demo.entities.UserNote;
import com.example.demo.repo.UserNoteRepository;

import lombok.Data;

@RestController
public class UserNoteController {

	@Autowired
	private final UserNoteRepository repository;

	public UserNoteController(UserNoteRepository repository) {
		// TODO Auto-generated constructor stub
		this.repository = repository;
	}

	@GetMapping(value = "/user-note")
	@ResponseBody
	public String getOneNote(@RequestParam(value = "wordId", required = false) Long wordId,
			@RequestParam(value = "userId", required = false) Long userId,
			@RequestParam(value = "sort", required = false) String sortType) {
		System.out.println(userId);
		if (userId == null && sortType == null) {
			return "";
		} else {
			String res = null;
			try{
				res = repository.getNote(wordId, userId).get(0).getNote();
			}catch (Exception e) {
				// TODO: handle exception
			}
			if (res == null) {
				return "";
			}
			System.out.println(res);
			return res;
		}
	}
//	@GetMapping(value = "/user-note")
//	@ResponseBody
//	public String getOneNote(@RequestBody UserNote userNote) {
//		System.out.println(
//				userNote.getUser().getId() + "-------"
//						+ userNote.getEnWord().getId() /* + "---" + userNote.getNote() */);
//		String res = repository.getNote(userNote.getEnWord().getId(), userNote.getUser().getId()).get(0).getNote();
//		if (res == null) {
//			return "";
//		}
//		System.out.println(res);
//		return res;
//	}

//	@PostMapping("/user-note/{userId}/{wordId}/{note}")
//	public String UserNote(@PathVariable int userId, @PathVariable int wordId, @PathVariable String note) {
//		if (repository.getNote(wordId, userId).size() == 0) {
//			repository.saveOneNote(wordId, userId, note);
//		} else {
//			repository.updateOneNote(wordId, userId, note);
//		}
//		return "";
//	}

	@PostMapping("/user-note")
	public String UserNote(@RequestBody FormNote formNote) {
		UserNote userNote = new UserNote();
		EnWord enWord = new EnWord();
		enWord.setId(formNote.getWordId());
		userNote.setEnWord(enWord);
		
		User user = new User();
		user.setId(formNote.getUserId());
		userNote.setUser(user);
		
		userNote.setNote(formNote.getNote());
		
		System.out.println(
				userNote.getUser().getId() + "-------" + userNote.getEnWord().getId() + "---" + userNote.getNote());
		if (repository.getNote(userNote.getEnWord().getId(), userNote.getUser().getId()).size() == 0) {
			repository.saveOneNote(userNote.getEnWord().getId(), userNote.getUser().getId(), userNote.getNote());
		} else {
			repository.updateOneNote(userNote.getEnWord().getId(), userNote.getUser().getId(), userNote.getNote());
		}
		return "Update successfully";
	}
	
	@DeleteMapping(value = "/{userId}/{wordId}")
	@ResponseBody
	public void deleteNote(@PathVariable Long userId, @PathVariable Long wordId) {

		repository.deleteNote(userId, wordId);
		return;
	}


}

@Data
class FormNote {
	Long userId;
	Long wordId;
	String note;
}
