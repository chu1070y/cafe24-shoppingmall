package com.cafe24.shoppingmall.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
	
	@GetMapping("")
	public String orderPage() {
		return "주문/결제 페이지";
	}
}
