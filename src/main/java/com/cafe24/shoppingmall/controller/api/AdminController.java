package com.cafe24.shoppingmall.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

	@PostMapping("/login")
	public JSONResult login() {
		
		return JSONResult.success(null);
	}
	
}
