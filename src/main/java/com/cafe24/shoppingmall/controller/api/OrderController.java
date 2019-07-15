package com.cafe24.shoppingmall.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.OrderService;
import com.cafe24.shoppingmall.vo.OrderVO;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("")
	public ResponseEntity<JSONResult> orderPage() {
		// 판매가능수량이 0인경우
		if (orderService.stockCheck() == 0) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("장바구니 페이지(상품품절 표시)"));
		}
		
		// 판매가능수량이 1인경우
		// DB에 들어가 판매가능수량 -1 업데이트
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("주문/결제 페이지"));
	}
	
	@PostMapping("pay")
	public ResponseEntity<JSONResult> pay(@RequestBody OrderVO vo) {
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(orderService.pay(vo) + "로 리다이렉트"));
	}
	
	@GetMapping("paySuccess")
	public ResponseEntity<JSONResult> pay(@RequestParam("token") String token) {
		return orderService.paySuccess(token) ? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("결제 완료 페이지")) :
					ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("결제 실패 페이지"));
	}
	
}
