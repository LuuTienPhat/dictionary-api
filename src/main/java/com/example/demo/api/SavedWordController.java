package com.example.demo.api;

import java.util.ArrayList;
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

import com.example.demo.domain.User;
import com.example.demo.entities.EnWord;
import com.example.demo.entities.Meaning;
import com.example.demo.entities.SavedWord;
import com.example.demo.models.SimplifiedEnWord;
import com.example.demo.repo.SavedWordRepo;
import com.example.demo.repo.SavedWordRepository;
import com.example.demo.service.FeedbackService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/savedwords")
@RequiredArgsConstructor
@Slf4j
public class SavedWordController {
	
	private final SavedWordRepository repository;
	
	@GetMapping(value = "/simplified/{userId}")
	@ResponseBody
	public List<SimplifiedEnWord> allSavedWordOfUser(@PathVariable Long userId) {

		List<EnWord> listSavedWord = repository.findSavedWord(userId);
		List<SimplifiedEnWord> toBeReturn = new ArrayList<SimplifiedEnWord>();
		for(EnWord e : listSavedWord) {

			Meaning m = new Meaning();
			m.setMeaning(e.getMeanings().get(0).getMeaning());
			m.setPartOfSpeech(e.getMeanings().get(0).getPartOfSpeech()); 
			List<Meaning> listMeaning = new ArrayList<Meaning>();
			listMeaning.add(m);
			SimplifiedEnWord enWord = new SimplifiedEnWord(e.getId(), e.getWord(), e.getViews(), e.getPronunciation(), listMeaning);

			toBeReturn.add(enWord);
		}
		return toBeReturn;
	}
	@GetMapping(value = "/id/{userId}")
	@ResponseBody
	public List<String> getAllSavedWordIdOfUser(@PathVariable Long userId) {

		return repository.getAllSavedWordId(userId);
	}
	
	@DeleteMapping(value = "/{userId}/{wordId}")
	@ResponseBody
	public String deleteSavedWord(@PathVariable Long userId, @PathVariable Long wordId) {

		repository.deleteSavedWord(userId, wordId);
		return "Unsave word successfully";
	}

	@PostMapping(value = "")
	@ResponseBody
	public String insertSavedWord(@RequestBody FormSavedWord formSavedWord) {
		
		SavedWord savedWord = new SavedWord();
		EnWord enWord = new EnWord();
		enWord.setId(formSavedWord.getWordId());
		savedWord.setEnWord(enWord);
		
		User user = new User();
		user.setId(formSavedWord.getUserId());
		savedWord.setUser(user);
		
		System.out.println(savedWord.getUser().getId()+"---------"+savedWord.getEnWord().getId());
		repository.insertSavedWord(savedWord.getUser().getId(), savedWord.getEnWord().getId());
		return "Lưu thành công";
	}
}

@Data
class FormSavedWord {
	Long userId;
	Long wordId;
}
