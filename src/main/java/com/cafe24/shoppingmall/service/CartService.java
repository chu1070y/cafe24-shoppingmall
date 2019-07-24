package com.cafe24.shoppingmall.service;

import java.util.List;

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
		return cartDAO.update(vo);
	}
	
	// junit test용
	public Boolean deleteAll() {
		return cartDAO.deleteAll();
	}

	public List<CartVO> get(CartVO vo) {
		return cartDAO.get(vo);
	}

	public Boolean deleteCart(Integer no) {
		return cartDAO.deleteCart(no);
	}

}
