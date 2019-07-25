package com.cafe24.shoppingmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.repository.CartDAO;
import com.cafe24.shoppingmall.vo.CartVO;
import com.cafe24.shoppingmall.vo.ProductVO;

@Service
public class CartService {
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private ProductService productService;
	
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

	@Transactional
	public List<CartVO> get(CartVO vo) {
		List<CartVO> cartList = cartDAO.get(vo);
		
		for(CartVO cartVO : cartList) {
			Integer productNo = productService.getProductNo(cartVO.getProduct_detail_no());
			ProductVO productVO = productService.getProduct(productNo);
			cartVO.setProductInfo(productVO);
		}
		
		return cartDAO.get(vo);
	}

	public Boolean deleteCart(Integer no) {
		return cartDAO.deleteCart(no);
	}

}
