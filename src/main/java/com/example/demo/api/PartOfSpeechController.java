package com.example.demo.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.example.demo.entities.Category;
import com.example.demo.entities.PartOfSpeech;
import com.example.demo.models.ResponseObject;
import com.example.demo.service.PartOfSpeechService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/partofspeeches")
@RequiredArgsConstructor
@Slf4j
public class PartOfSpeechController {
	private final PartOfSpeechService partOfSpeechService;

	@GetMapping(value = "")
	@ResponseBody
	public ResponseEntity<ResponseObject> allButLimit(@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "search", required = false) String keyword) {
		ResponseEntity<ResponseObject> responseEntity = null;

		List<PartOfSpeech> result = null;
		if (keyword != null) {
			result = partOfSpeechService.getPartOfSpeeches(keyword);
		} else if (offset != null || limit != null) {
			result = partOfSpeechService.getPartOfSpeeches(offset, limit);
		} else {
			result = partOfSpeechService.getPartOfSpeeches();
		}
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Fetch categories successfully!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Failed to fetch categories!", result));
		}

		return responseEntity;
	}

	@PostMapping(value = "")
	public ResponseEntity<ResponseObject> newPartOfSpeech(@RequestBody PartOfSpeech newPartOfSpeech) {
		ResponseEntity<ResponseObject> responseEntity = null;
		PartOfSpeech result = partOfSpeechService.insertPartOfSpeech(newPartOfSpeech);
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("ok", HttpStatus.OK.value(), "Insert Part of Speech successfully!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Failed to insert Part of Speech!", result));
		}

		return responseEntity;

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseObject> one(@PathVariable Long id) {
		ResponseEntity<ResponseObject> responseEntity = null;
		PartOfSpeech result = partOfSpeechService.getPartOfSpeech(id);
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("ok", HttpStatus.OK.value(), "Search Part of Speech successfully!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Part of Speech not found!", result));
		}

		return responseEntity;
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ResponseObject> replacePartOfSpeech(@RequestBody PartOfSpeech newPartOfSpeech,
			@PathVariable Long id) {
		ResponseEntity<ResponseObject> responseEntity = null;
		PartOfSpeech result = partOfSpeechService.updatePartOfSpeech(id, newPartOfSpeech);
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("ok", HttpStatus.OK.value(), "Update Part of Speech successfully!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Failed to update Part of Speech!", result));
		}

		return responseEntity;
	}

	@DeleteMapping(value = "/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		partOfSpeechService.deletePartOfSpeech(id);
	}

	@GetMapping(value = "/count")
	@ResponseBody
	ResponseEntity<ResponseObject> count() {
		Long result = partOfSpeechService.count();
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", HttpStatus.OK.value(), "Part of Speech count", result));

	}
}
