package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.example.demo.entities.EnWord;
import com.example.demo.models.ResponseObject;
import com.example.demo.repo.EnWordRepo;
import com.example.demo.service.CategoryService;
import com.example.demo.service.EnWordService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/enwords")
@RequiredArgsConstructor
@Slf4j
public class EnWordController {
	private final EnWordService enWordService;

	@GetMapping(value = "")
	@ResponseBody
	public ResponseEntity<ResponseObject> allButLimit(
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "search", required = false) String keyword) {

		ResponseEntity<ResponseObject> responseEntity = null;
		List<EnWord> result = null;
		if (keyword != null) {
			result = enWordService.getEnWords(keyword);
		} else {
			result = enWordService.getEnWords(offset, limit);
		}
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Fetch Enwords successfully!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Failed to fetch enwords!", result));
		}

		return responseEntity;
	}

	@PostMapping(value = "")
	@ResponseBody
	public ResponseEntity<ResponseObject> newEnWord(@RequestBody EnWord newEnWord) {
		ResponseEntity<ResponseObject> responseEntity = null;
		EnWord result = enWordService.insertEnWord(newEnWord);
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Insert Enword successfully!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Failed to insert Enword!", result));
		}
		return responseEntity;
	}

	@GetMapping(value = "/{id}")
	@ResponseBody
	ResponseEntity<ResponseObject> one(@PathVariable Long id) {
		ResponseEntity<ResponseObject> responseEntity = null;
		EnWord result = enWordService.getEnWord(id);
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Enword found!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
					new ResponseObject("failed", HttpStatus.NOT_IMPLEMENTED.value(), "Enword NOT found!", result));
		}
		return responseEntity;
	}

	@PutMapping(value = "/{id}")
	@ResponseBody
	ResponseEntity<ResponseObject> replaceEnWord(@RequestBody EnWord newEnWord, @PathVariable Long id) {
		ResponseEntity<ResponseObject> responseEntity = null;
		EnWord result = enWordService.updateEnWord(id, newEnWord);
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Update Enword successfully!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Failed to update Enword!", result));
		}
		return responseEntity;
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	ResponseEntity<ResponseObject> deleteEmployee(@PathVariable Long id) {
		ResponseEntity<ResponseObject> responseEntity = null;
		try {
			enWordService.deleteEnWord(id);
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Delete Enword successfully!", null));
		} catch (Exception e) {
			// TODO: handle exception
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
					new ResponseObject("failed", HttpStatus.NOT_IMPLEMENTED.value(), "Failed to delete Enword!", null));
		}

		return responseEntity;
	}

	@GetMapping(value = "/count")
	@ResponseBody
	ResponseEntity<ResponseObject> count() {
		ResponseEntity<ResponseObject> responseEntity = null;
		Long result = enWordService.count();
		return responseEntity = ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", HttpStatus.OK.value(), "Enword count", result));

	}
}
