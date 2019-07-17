package com.cafe24.shoppingmall.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.dto.PageInfo;
import com.cafe24.shoppingmall.service.ProductService;
import com.cafe24.shoppingmall.vo.ProductVO;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	// 특정 상품 정보 조회
	@GetMapping("/{no}")
	public ResponseEntity<JSONResult> productRead(@PathVariable("no") Integer no) {		
		ProductVO vo = productService.getProduct(no);
		
		if(vo == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("해당 no에 대한 상품이 없습니다."));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo));
	}
	
	// 상품 리스트 조회
	@GetMapping("/list")
	public ResponseEntity<JSONResult> list(@ModelAttribute PageInfo pageInfo) {
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(productService.getList(pageInfo)));
	}
	
	// 상품 등록
	@PostMapping("/add")
	public ResponseEntity<JSONResult> addProduct(
			@RequestBody @Valid ProductVO productVO, 
			BindingResult result) {
		
		// 가입 오류시 에러 출력
	      if (result.hasErrors()) {
	         List<ObjectError> list = result.getAllErrors();
	         for (ObjectError error : list) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(error.getDefaultMessage()));
	         }
	      }
		
		Integer checkVO = productService.insert(productVO);
		return checkVO != -1 ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("상품 정상 등록 <no>" + checkVO + "<no/>")) : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("상품 등록 실패"));
	}
	
	
	
	
	
}
