package com.cafe24.shoppingmall.controller.api;

import java.util.List;

import javax.validation.Valid;

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
import com.cafe24.shoppingmall.service.AdminService;
import com.cafe24.shoppingmall.vo.LoginVO;
import com.cafe24.shoppingmall.vo.ProductVO;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/login")
	public ResponseEntity<JSONResult> login(@RequestBody @Valid LoginVO vo, BindingResult result) {
		System.out.println(vo);
		// 유효성 검사 실패시
		if(result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("유효성검사로 인한 로그인 실패"));
		}
		
		return adminService.login(vo) ? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("로그인 성공")) : 
					ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("로그인 실패"));
	}
	
	@GetMapping("/productAdmin")
	public ResponseEntity<JSONResult> adminPage() {
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("상품관리 페이지"));
	}
	
	@PostMapping("/productRegister")
	public ResponseEntity<JSONResult> productRegister(
			@RequestBody @Valid ProductVO vo,
			BindingResult result) {
		System.out.println(vo);
		// 유효성 검사 실패시
		if(result.hasErrors()) {
			List<FieldError> list = result.getFieldErrors();
			String errorMsg = "";
			for(FieldError error : list) {
				errorMsg += error.getField() + "/";
			}
			errorMsg += "오류발생";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(errorMsg));
		}
		
		adminService.productRegister(vo);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("상품 등록 성공"));
	}
}
