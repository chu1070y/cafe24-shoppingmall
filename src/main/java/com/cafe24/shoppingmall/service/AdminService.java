package com.cafe24.shoppingmall.service;

import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.vo.LoginVO;

@Service
public class AdminService {

	public Boolean login(LoginVO adminVO) {
		// admin 로그인 프로세스
		LoginVO vo = new LoginVO();
		vo.setId("chu1070y");
		vo.setPw("1234");
		
		return vo.equals(adminVO);
	}

	
	
	
}
