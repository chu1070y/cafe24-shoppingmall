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
import com.cafe24.shoppingmall.service.CartService;
import com.cafe24.shoppingmall.vo.api.CartApiVO;

@RestController
@RequestMapping(value = "/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("insert")
	public ResponseEntity<JSONResult> cart(@RequestBody CartApiVO vo) {
		return cartService.insertCart(vo) ? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("장바구니 담기 성공")) : 
					ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("장바구니 담기 실패"));
	}
	
	@GetMapping("")
	public ResponseEntity<JSONResult> cartPage() {
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("장바구니 페이지"));
	}
	
	@PostMapping("update")
	public ResponseEntity<JSONResult> update(@RequestBody CartApiVO vo) {
		
		return cartService.updateCart(vo) ? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("장바구니 수량변경 성공")) : 
					ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("장바구니 수량변경 실패"));
	}
}
