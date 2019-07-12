package com.cafe24.shoppingmall.service;

import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.vo.api.CartApiVO;

@Service
public class CartService {
	
	public Boolean insertCart(CartApiVO vo) {
		// DB 접속해 장바구니에 상품담기
		System.out.println(vo);
		return true;
	}

}
