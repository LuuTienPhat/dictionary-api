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

import com.example.demo.entities.Meaning;
import com.example.demo.models.ResponseObject;
import com.example.demo.service.MeaningService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/meanings")
@RequiredArgsConstructor
@Slf4j
public class MeaningController {
	private final MeaningService meaningService;

//	@GetMapping(value = "")
//	@ResponseBody
//	public ResponseEntity<ResponseObject> allButLimit(
//			@RequestParam(value = "offset", required = false) Integer offset,
//			@RequestParam(value = "limit", required = false) Integer limit,
//			@RequestParam(value = "search", required = false) String keyword) {
//		ResponseEntity<ResponseObject> responseEntity = null;
//		
//		List<Meaning> result = null;;
//		if (keyword != null) {
//			result = meaningService.getmeanings(keyword);
//		} else {
//			result = meaningService.getmeanings();
//		}
//		if (result != null) {
//			responseEntity = ResponseEntity.status(HttpStatus.OK)
//					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Fetch meanings successfully!", result));
//		} else {
//			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
//					HttpStatus.NOT_IMPLEMENTED.value(), "Failed to fetch meanings!", result));
//		}
//
//		return responseEntity;
//	}

	@PostMapping(value = "")
	public ResponseEntity<ResponseObject> newMeaning(@RequestBody Meaning newMeaning) {
		ResponseEntity<ResponseObject> responseEntity = null;
		Meaning result = meaningService.insertMeaning(newMeaning);
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Insert Meaning successfully!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Failed to insert Meaning!", result));
		}

		return responseEntity;

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseObject> one(@PathVariable Long id) {
		ResponseEntity<ResponseObject> responseEntity = null;
		Meaning result = meaningService.getMeaning(id);
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Search Meaning successfully!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
					new ResponseObject("failed", HttpStatus.NOT_IMPLEMENTED.value(), "Meaning not found!", result));
		}

		return responseEntity;
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ResponseObject> replaceMeaning(@RequestBody Meaning newMeaning, @PathVariable Long id) {
		ResponseEntity<ResponseObject> responseEntity = null;
		Meaning result = meaningService.updateMeaning(id, newMeaning);
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Update Meaning successfully!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Failed to update Meaning!", result));
		}

		return responseEntity;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseObject> deleteEmployee(@PathVariable Long id) {
		ResponseEntity<ResponseObject> responseEntity = null;
		try {
			meaningService.deleteMeaning(id);
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Delete Meaning successfully!", null));
		} catch (Exception e) {
			// TODO: handle exception
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Failed to delete Meaning!", null));
		}

		return responseEntity;
	}
	
	@GetMapping(value = "/count")
	@ResponseBody
	ResponseEntity<ResponseObject> count() {
		Long result = meaningService.count();
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", HttpStatus.OK.value(), "Meaning count", result));

	}
}
