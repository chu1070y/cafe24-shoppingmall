package com.cafe24.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.repository.UserDAO;
import com.cafe24.shoppingmall.vo.LoginVO;
import com.cafe24.shoppingmall.vo.UserVO;
import com.cafe24.shoppingmall.vo.api.UserApiVO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	public Boolean checkId(String id) {
		return userDAO.get(id) != null;
	}

	public UserVO registerMember(UserApiVO vo) {
		UserVO userVO = new UserVO();
		userVO.setId(vo.getId());
		userVO.setPw(vo.getPw());
		userVO.setName(vo.getName());
		userVO.setAddr(vo.getAddr());
		userVO.setEmail(vo.getEmail());
		userVO.setTel_home(vo.getTel_home());
		userVO.setTel_phone(vo.getTel_phone());
		userVO.setGender(vo.getGender());
		userVO.setBirthdate(vo.getBirthdate());
		
		return userDAO.insert(userVO);
	}

	public Boolean deleteAll() {
		return userDAO.deleteAll();
	}

	public Boolean login(LoginVO vo) {
		return userDAO.login(vo) != null? true: false;
	}
}
