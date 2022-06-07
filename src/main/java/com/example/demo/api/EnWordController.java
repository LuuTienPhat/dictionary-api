package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.EnWord;
import com.example.demo.repo.EnWordRepo;

@RestController
public class EnWordController {

	@Autowired
	private final EnWordRepo repository;

	public EnWordController(EnWordRepo repository) {
		// TODO Auto-generated constructor stub
		this.repository = repository;
	}

	@GetMapping(value = "/enwords")
	@ResponseBody
	public List<EnWord> allButLimit(@RequestParam(value = "offset", required = false) Integer offset, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "sort", required = false) String sortType) {

		if (limit == null && sortType == null) {
			return repository.findAll();
		} else {
			Page<EnWord> page = repository.findAll(PageRequest.of(offset, limit));
			return page.toList();
		}
	}

	@PostMapping("/enwords")
	public EnWord newEnWord(@RequestBody EnWord newEnWord) {
		return repository.save(newEnWord);
	}

	@GetMapping("enwords/{id}")
	EnWord one(@PathVariable Long id) {
		EnWord enWord = null;
//				repository.findById(id).orElseThrow(() -> new EnWordNotFoundException(id));
		return enWord;
	}

	@PutMapping("/enwords/{id}")
	EnWord replaceEnWord(@RequestBody EnWord newEnWord, @PathVariable Long id) {

		return repository.findById(id).map(enWord -> {

			enWord.setWord(newEnWord.getWord());
			enWord.setPronunciation(newEnWord.getPronunciation());
			enWord.setViews(newEnWord.getViews());

			return repository.save(enWord);
		}).orElseGet(() -> {

			newEnWord.setId(id);
			return repository.save(newEnWord);

		});
	}

	@DeleteMapping("/enwords/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
