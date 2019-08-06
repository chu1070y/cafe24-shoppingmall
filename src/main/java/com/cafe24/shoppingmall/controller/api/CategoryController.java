package com.cafe24.shoppingmall.controller.api;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
		// 카테고리 이름 비어있는지 검사
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		
		Set<ConstraintViolation<CategoryVO>> validatorResults = validator.validateProperty(vo, "category_name");
		
		if(validatorResults.isEmpty() == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("카테고리명 미입력")); 
		}
		
		// 상위 카테고리 존재 검사
		if (categoryService.checkCategoryNo(vo.getParent())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("상위 카테고리 없음"));
		}
		
		CategoryVO categoryVO = categoryService.insert(vo);
		
		if (vo.getParent() != null) {
			categoryService.updateOrd(categoryVO);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(
				JSONResult.success(categoryVO));
	}
	
	// 상위 카테고리 조회
	@GetMapping("list")
	public ResponseEntity<JSONResult> categoryList() {

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(categoryService.getList()));
	}
	
	// 하위 카테고리 조회
	@GetMapping("lowList")
	public ResponseEntity<JSONResult> lowCategoryList(CategoryVO vo) {

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(categoryService.getLowList(vo)));
	}
	
	// 카테고리 수정
	@PostMapping("update")
	public ResponseEntity<JSONResult> categoryUpdate(
			@RequestBody @Valid CategoryVO vo, BindingResult result
			){
		
		// 상위 카테고리 존재 검사
		if (categoryService.checkCategoryNo(vo.getParent())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("상위 카테고리 없음"));
		}
		
		// 유효성 검사 실패시
		if(result.hasErrors()) {
			List<FieldError> list = result.getFieldErrors();
			String errorMsg = "";
			for(FieldError error : list) {
				errorMsg += error.getField() + "/";
			}
			errorMsg += "미입력";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(errorMsg));
		}
		
		
		return categoryService.update(vo) ? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("카테고리 수정 성공")) :
					ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("해당 카테고리 없음"));
	}
	
	// 카테고리 삭제
	@PostMapping("delete")
	public ResponseEntity<JSONResult> categoryDelete(
			@RequestBody Integer no, BindingResult result
			){
		categoryService.delete(no);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("카테고리 삭제 성공"));
	}
	
	
}
