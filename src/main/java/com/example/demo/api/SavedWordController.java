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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.EnWord;
import com.example.demo.repo.SavedWordRepo;
import com.example.demo.service.FeedbackService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/savedwords")
@RequiredArgsConstructor
@Slf4j
public class SavedWordController {
	private final SavedWordRepo repository;

//	public SavedWordController(SavedWordRepository repository) {
//		// TODO Auto-generated constructor stub
//		this.repository = repository;
//	}

	
	@GetMapping(value = "/savedword/{userId}")
	@ResponseBody
	public List<EnWord> allSavedWordOfUser(@PathVariable Long userId) {

		return repository.findSavedWord(userId);
	}
	
//	@GetMapping(value = "/enwords")
//	@ResponseBody
//	public List<EnWord> allButLimit(@RequestParam(value = "limit", required = false) Integer limit,
//			@RequestParam(value = "sort", required = false) String sortType) {
//
//		if (limit == null && sortType == null) {
//			return repository.findAll();
//		} else {
//			Page<EnWord> page = repository.findAll(PageRequest.of(0, limit));
//			return page.toList();
//		}
//	}

//	@PostMapping("/enwords")
//	public EnWord newEnWord(@RequestBody EnWord newEnWord) {
//		return repository.save(newEnWord);
//	}
//
//	@GetMapping("enwords/{id}")
//	EnWord one(@PathVariable int id) {
//		EnWord enWord = repository.findById(id).orElseThrow(() -> new EnWordNotFoundException(id));
//		return enWord;
//	}
//
//	@PutMapping("/enwords/{id}")
//	EnWord replaceEnWord(@RequestBody EnWord newEnWord, @PathVariable Integer id) {
//
//		return repository.findById(id).map(enWord -> {
//
//			enWord.setWord(newEnWord.getWord());
//			enWord.setPronunciation(newEnWord.getPronunciation());
//			enWord.setViews(newEnWord.getViews());
//
//			return repository.save(enWord);
//		}).orElseGet(() -> {
//
//			newEnWord.setId(id);
//			return repository.save(newEnWord);
//
//		});
//	}
//
//	@DeleteMapping("/enwords/{id}")
//	void deleteEmployee(@PathVariable Integer id) {
//		repository.deleteById(id);
//	}
}
