package com.cafe24.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.repository.NomemberDAO;
import com.cafe24.shoppingmall.vo.NomemberVO;

@Service
public class NomemberService {
	
	@Autowired
	private NomemberDAO nomemberDAO;

	@Transactional
	public NomemberVO get(NomemberVO nomemberVO) {
		
		NomemberVO vo = nomemberDAO.get(nomemberVO);
		
		if (vo == null) {
			vo = nomemberDAO.insert(nomemberVO);
		}
		
		return vo;
	}

	public Boolean deleteAll() {
		return nomemberDAO.deleteAll();
	}
}
