package com.cafe24.shoppingmall.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.service.OrderService;
import com.cafe24.shoppingmall.vo.api.OrderApiVO;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("")
	public String orderPage() {
		return orderService.stockCheck();
	}
	
	@PostMapping("pay")
	public String pay(@RequestBody OrderApiVO vo) {
		return orderService.pay(vo) + "로 리다이렉트";
	}
	
}
