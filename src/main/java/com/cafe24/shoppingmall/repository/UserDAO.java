package com.cafe24.shoppingmall.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.UserVO;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVO get(String id) {
		return sqlSession.selectOne("user.getById", id);
	}

	public Boolean insert(UserVO vo) {
		return 1 == sqlSession.insert("user.insert", vo);
	}
	
}
