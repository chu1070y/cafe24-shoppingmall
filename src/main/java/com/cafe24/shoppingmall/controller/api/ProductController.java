package com.cafe24.shoppingmall.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.ProductService;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/list")
	public ResponseEntity<JSONResult> list() {
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productService.getList()));
	}
	
}
