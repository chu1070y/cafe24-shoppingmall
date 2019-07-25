package com.cafe24.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.repository.AdminDAO;
import com.cafe24.shoppingmall.vo.AdminVO;

@Service
public class AdminService {
	
	@Autowired
	private AdminDAO adminDAO;

	public Boolean add(AdminVO vo) {
		return adminDAO.add(vo);
	}

	public Boolean checkId(String id) {
		return adminDAO.checkId(id);
	}

	public Boolean deleteAll() {
		return adminDAO.deleteAll();
	}

	public AdminVO login(AdminVO vo) {
		return adminDAO.login(vo);
	}

	
	
	
}
