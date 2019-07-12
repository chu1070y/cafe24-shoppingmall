package com.cafe24.shoppingmall.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.ProductService;
import com.cafe24.shoppingmall.vo.ProductVO;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/list")
	public JSONResult list() {
		return JSONResult.success(productService.getList());
	}
	
	@PostMapping("cart")
	public JSONResult cart(@RequestBody ProductVO vo) {
		
		return productService.insertCart(vo) ? JSONResult.success("장바구니 담기 성공") : JSONResult.fail("장바구니 담기 실패");
	}
	
	
}
