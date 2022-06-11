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

import com.example.demo.entities.Product;
import com.example.demo.models.ResponseObject;
import com.example.demo.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
	private final ProductService productService;

	@GetMapping(value = "")
	@ResponseBody
	public ResponseEntity<ResponseObject> allButLimit(@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "search", required = false) String keyword) {

		ResponseEntity<ResponseObject> responseEntity = null;
		List<Product> result = null;
		if (keyword != null) {
			result = productService.getProducts(keyword);
		} else {
			result = productService.getProducts();
		}
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Fetch Products successfully!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Failed to fetch Products!", result));
		}

		return responseEntity;
	}

	@PostMapping(value = "")
	@ResponseBody
	public ResponseEntity<ResponseObject> newProduct(@RequestBody Product newProduct) {
		ResponseEntity<ResponseObject> responseEntity = null;
		Product result = productService.insertProduct(newProduct);
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Insert Product successfully!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Failed to insert Product!", result));
		}
		return responseEntity;
	}

	@GetMapping(value = "/{id}")
	@ResponseBody
	ResponseEntity<ResponseObject> one(@PathVariable Long id) {
		ResponseEntity<ResponseObject> responseEntity = null;
		Product result = productService.getProduct(id);
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Product found!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
					new ResponseObject("failed", HttpStatus.NOT_IMPLEMENTED.value(), "Product NOT found!", result));
		}
		return responseEntity;
	}

	@PutMapping(value = "/{id}")
	@ResponseBody
	ResponseEntity<ResponseObject> replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {
		ResponseEntity<ResponseObject> responseEntity = null;
		Product result = productService.updateProduct(id, newProduct);
		if (result != null) {
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Update Product successfully!", result));
		} else {
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed",
					HttpStatus.NOT_IMPLEMENTED.value(), "Failed to update Product!", result));
		}
		return responseEntity;
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	ResponseEntity<ResponseObject> deleteEmployee(@PathVariable Long id) {
		ResponseEntity<ResponseObject> responseEntity = null;
		try {
			productService.deleteProduct(id);
			responseEntity = ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", HttpStatus.OK.value(), "Delete Product successfully!", null));
		} catch (Exception e) {
			// TODO: handle exception
			responseEntity = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
					new ResponseObject("failed", HttpStatus.NOT_IMPLEMENTED.value(), "Failed to delete Product!", null));
		}

		return responseEntity;
	}

	@GetMapping(value = "/count")
	@ResponseBody
	ResponseEntity<ResponseObject> count() {
		Long result = productService.count();
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", HttpStatus.OK.value(), "Product count", result));

	}
}
