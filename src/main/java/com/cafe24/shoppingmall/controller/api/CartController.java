package com.cafe24.shoppingmall.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
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
	public JSONResult cart(@RequestBody CartApiVO vo) {
		
		return cartService.insertCart(vo) ? JSONResult.success("장바구니 담기 성공") : JSONResult.fail("장바구니 담기 실패");
	}
	
	@GetMapping("")
	public String cartPage() {
		return "장바구니 페이지";
	}
	
	@PostMapping("update")
	public JSONResult update(@RequestBody CartApiVO vo) {
		
		return cartService.updateCart(vo) ? JSONResult.success("장바구니 수량변경 성공") : JSONResult.fail("장바구니 수량변경 실패");
	}
}
