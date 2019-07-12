package com.cafe24.shoppingmall.service;

import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.vo.LoginVO;
import com.cafe24.shoppingmall.vo.api.ProductApiVO;

@Service
public class AdminService {

	public Boolean login(LoginVO adminVO) {
		// admin 로그인 프로세스
		LoginVO vo = new LoginVO();
		vo.setId("chu1070y");
		vo.setPw("12345678!z");
		
		return vo.equals(adminVO);
	}

	public void productRegister(ProductApiVO vo) {
		// 상품 등록 프로세스
		// 1. 상품 등록 후 상품번호를 받아온다.
		// 2. 상품번호를 이용해 상품 이미지를 등록한다.
		// 3. 상품번호를 이용해 재고, 진열상태를 등록한다.
		
		System.out.println(vo);
		
	}

	
	
	
}
