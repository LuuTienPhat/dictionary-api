package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.entities.Feedback;
import com.example.demo.entities.PartOfSpeech;
import com.example.demo.models.ResponseObject;
import com.example.demo.repo.FeedbackRepo;
import com.example.demo.repo.PartOfSpeechRepo;
import com.example.demo.service.FeedbackService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
@Slf4j
public class FeedbackController {
	private final FeedbackService feedbackService;

	@GetMapping(value = "")
	@ResponseBody
	public ResponseEntity<ResponseObject> allButLimit(@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "sort", required = false) String sortType) {
		ResponseEntity<ResponseObject> responseEntity = null;
		List<Feedback> results = null;
		if (limit == null && sortType == null) {
			results = feedbackService.getFeedbacks();
		} else {
			results = feedbackService.getFeedbacks(0, limit);
		}

		if (results != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Fetch Feedback successfully!", results));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Failed to fetch Feedback!", results));
		}

		return responseEntity;
	}

	@PostMapping(value = "")
	public ResponseEntity<ResponseObject> newFeedback(@RequestBody Feedback newFeedback) {
		ResponseEntity<ResponseObject> responseEntity = null;
		Feedback result = feedbackService.insertFeedback(newFeedback);
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Insert Feedback successfully!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Failed to insert Feedback!", result));
		}

		return responseEntity;
	}

	@GetMapping(value = "/{id}")
	ResponseEntity<ResponseObject> one(@PathVariable Long id) {
		ResponseEntity<ResponseObject> responseEntity = null;
		Feedback result = feedbackService.getFeedback(id);
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Feedback found!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Feedback not found!", result));
		}

		return responseEntity;
	}

	@PutMapping(value = "/{id}")
	ResponseEntity<ResponseObject> replaceFeedback(@RequestBody Feedback newFeedback, @PathVariable Long id) {
		ResponseEntity<ResponseObject> responseEntity = null;
		Feedback result = feedbackService.updateFeedback(id, newFeedback);
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Update Feedback successfully!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Failed to update Feedback!", result));
		}

		return responseEntity;
	}

	@DeleteMapping(value = "/{id}")
	void deleteEmployee(@PathVariable Long id) {
		feedbackService.deleteFeedback(id);
	}
}
