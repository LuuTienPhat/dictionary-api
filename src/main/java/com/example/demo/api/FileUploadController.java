package com.example.demo.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.example.demo.models.ResponseObject;
import com.example.demo.service.IStorageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(path = "/uploads")
public class FileUploadController {
	// Inject Storage Service here
	@Autowired
	private IStorageService storageService;
	
	@PostMapping("")
	public ResponseEntity<ResponseObject> uploadFile(@RequestPart(value = "file") String file) {
		ResponseEntity<ResponseObject> responseEntity = null;
//		try {
//			// save files to a folder => use a service
//			String generatedFileName = storageService.storeFile(file);
////            return ResponseEntity.status(HttpStatus.OK).body(
////               new ResponseObject("ok", "upload file successfully", generatedFileName)
////            );
//			responseEntity = ResponseEntity.status(HttpStatus.OK)
//					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Upload file successfully!", generatedFileName));
//			// 06a290064eb94a02a58bfeef36002483.png => how to open this file in Web Browser
//			// ?
//		} catch (Exception exception) {
//			responseEntity = ResponseEntity.status(HttpStatus.OK)
//					.body(new ResponseObject("ok", HttpStatus.OK.value(), exception.getMessage(), ""));
////            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
////               new ResponseObject("ok", exception.getMessage(), "")
////            );
//		}

		return responseEntity;
	}

	// get image's url
	@GetMapping("/files/{fileName:.+}")
	// /files/06a290064eb94a02a58bfeef36002483.png
	public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
		try {
			byte[] bytes = storageService.readFileContent(fileName);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
		} catch (Exception exception) {
			return ResponseEntity.noContent().build();
		}
	}

	// How to load all uploaded files ?
	@GetMapping("")
	public ResponseEntity<ResponseObject> getUploadedFiles() {
		ResponseEntity<ResponseObject> responseEntity = null;
		try {
			List<String> urls = storageService.loadAll().map(path -> {
				// convert fileName to url(send request "readDetailFile")
				String urlPath = MvcUriComponentsBuilder
						.fromMethodName(FileUploadController.class, "readDetailFile", path.getFileName().toString())
						.build().toUri().toString();
				return urlPath;
			}).collect(Collectors.toList());
//            return ResponseEntity.ok(new ResponseObject("ok", "List files successfully", urls));
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "List files successfully", urls));
		} catch (Exception exception) {
//            return ResponseEntity.ok(new
//                    ResponseObject("failed", "List files failed", new String[] {}));
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "List files failed", new String[] {}));
		}
		return responseEntity;
	}

}
