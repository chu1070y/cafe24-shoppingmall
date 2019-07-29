package com.cafe24.shoppingmall.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.NomemberService;
import com.cafe24.shoppingmall.vo.NomemberVO;

@RestController
@RequestMapping(value = "/api/nomember")
public class NomemberController {
	
	@Autowired
	private NomemberService nomemberService;
	
	// 비회원 저장
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> nomember(@ModelAttribute NomemberVO nomemberVO) {
		NomemberVO no = nomemberService.get(nomemberVO);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(no));
	}
	
}
