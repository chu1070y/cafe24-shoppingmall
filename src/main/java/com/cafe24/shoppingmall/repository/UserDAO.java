package com.cafe24.shoppingmall.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.api.UserApiVO;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserApiVO get(String id) {
		return sqlSession.selectOne("user.getById", id);
	}

	public Integer insert(UserApiVO vo) {
		sqlSession.insert("user.insert", vo);
		return vo.getPoint();
	}

	public Boolean deleteAll() {
		return 1 == sqlSession.delete("user.deleteAll");
	}
	
}
