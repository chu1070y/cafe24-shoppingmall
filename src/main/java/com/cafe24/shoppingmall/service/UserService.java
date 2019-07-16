package com.cafe24.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.repository.UserDAO;
import com.cafe24.shoppingmall.vo.LoginVO;
import com.cafe24.shoppingmall.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	public Boolean checkId(String id) {
		return userDAO.get(id) != null;
	}

	public UserVO registerMember(UserVO vo) {
		
		return userDAO.insert(vo);
	}

	public Boolean deleteAll() {
		return userDAO.deleteAll();
	}

	public UserVO login(LoginVO vo) {
		return userDAO.login(vo);
	}

	public UserVO getUser(String id) {
		return userDAO.get(id);
	}

	public Boolean update(UserVO vo) {
		return userDAO.update(vo);
	}

	public Boolean delete(String id) {
		return userDAO.delete(id);
	}
}
