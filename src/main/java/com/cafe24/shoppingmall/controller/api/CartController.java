package com.cafe24.shoppingmall.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.CartService;
import com.cafe24.shoppingmall.vo.CartVO;

@RestController
@RequestMapping(value = "/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	// 장바구니 담기
	@PostMapping("/add")
	public ResponseEntity<JSONResult> cartAdd(@RequestBody CartVO vo) {
		return cartService.insertCart(vo) ? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("장바구니 담기 성공")) : 
					ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("장바구니 담기 실패"));
	}
	
	// 장바구니 목록
	@GetMapping("/list")
	public ResponseEntity<JSONResult> cartList(@ModelAttribute CartVO vo) {
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(cartService.get(vo)));
	}
	
	// 장바구니 수량 수정
	@PostMapping("/update")
	public ResponseEntity<JSONResult> cartUpdate(@RequestBody CartVO vo) {
		
		if(vo.getQuantity() < 1) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("수량 1이상 입력 요망"));
		}
		
		return cartService.updateCart(vo) ? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("장바구니 수량변경 성공")) : 
					ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("장바구니 수량변경 실패"));
	}
	
	// 장바구니 상품 삭제
	@PostMapping("/delete")
	public ResponseEntity<JSONResult> cartDelete(@RequestBody Integer no) {
		
		return cartService.deleteCart(no) ? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("장바구니 상품 삭제 성공")) : 
					ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("장바구니 상품 삭제 실패"));
	}

}
