package com.cafe24.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.repository.CartDAO;
import com.cafe24.shoppingmall.vo.CartVO;

@Service
public class CartService {
	
	@Autowired
	private CartDAO cartDAO;
	
	public Boolean insertCart(CartVO vo) {
		return cartDAO.insert(vo);
	}

	public Boolean updateCart(CartVO vo) {
		// DB 접속해 장바구니 수량 변경
		System.out.println(vo);
		return true;
	}
	
	// junit test용
	public Boolean deleteAll() {
		return cartDAO.deleteAll();
	}

}
