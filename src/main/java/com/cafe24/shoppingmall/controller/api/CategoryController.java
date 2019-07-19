package com.cafe24.shoppingmall.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.CategoryService;
import com.cafe24.shoppingmall.vo.CategoryVO;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	// 카테고리 등록
	@PostMapping("add")
	public ResponseEntity<JSONResult> categoryAdd(@RequestBody CategoryVO vo) {

		if (vo.getCategory_name() == null || vo.getCategory_name().trim().length() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("카테고리 이름 필수"));
		}
		
		Integer check = categoryService.insert(vo);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("카테고리 추가 성공 <no>" + check + "</no>"));
	}
	
	// 카테고리 조회
	@GetMapping("list")
	public ResponseEntity<JSONResult> categoryList() {

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(categoryService.getList()));
	}
}
