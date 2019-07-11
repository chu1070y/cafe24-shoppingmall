package com.cafe24.shoppingmall.service;

import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.vo.AdminVO;

@Service
public class AdminService {

	public Boolean login(AdminVO adminVO) {
		// admin 로그인 프로세스
		AdminVO vo = new AdminVO();
		vo.setId("chu1070y");
		vo.setPw("1234");
		
		return vo.equals(adminVO);
	}

	
	
	
}
