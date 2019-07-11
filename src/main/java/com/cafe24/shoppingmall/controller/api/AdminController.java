package com.cafe24.shoppingmall.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.AdminService;
import com.cafe24.shoppingmall.vo.AdminVO;
import com.cafe24.shoppingmall.vo.ProductVO;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/login")
	public JSONResult login(@ModelAttribute AdminVO vo) {
		return adminService.login(vo) ? JSONResult.success("로그인 성공"): JSONResult.fail("로그인 실패");
	}
	
	@GetMapping("/productAdmin")
	public String adminPage() {
		return "상품관리 페이지";
	}
	
	@PostMapping("/productRegister")
	public JSONResult productRegister(
			@ModelAttribute @Valid ProductVO vo,
			BindingResult result) {
		
		// 유효성 검사 실패시
		if(result.hasErrors()) {
			List<FieldError> list = result.getFieldErrors();
			String errorMsg = "";
			for(FieldError error : list) {
				errorMsg += error.getField() + "/";
			}
			errorMsg += "오류발생";
			return JSONResult.fail(errorMsg);
		}
		
		return JSONResult.success(null);
	}
}
