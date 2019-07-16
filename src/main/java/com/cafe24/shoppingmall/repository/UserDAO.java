package com.cafe24.shoppingmall.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.dto.PageInfo;
import com.cafe24.shoppingmall.vo.UserVO;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVO get(String id) {
		return sqlSession.selectOne("user.getById", id);
	}
	
	public UserVO get(Integer no) {
		return sqlSession.selectOne("user.getByNo", no);
	}

	public UserVO insert(UserVO vo) {
		sqlSession.insert("user.insert", vo);
		return vo;
	}

	public Boolean deleteAll() {
		return 1 == sqlSession.delete("user.deleteAll");
	}
	
	public UserVO login(UserVO vo) {
		return sqlSession.selectOne("user.login", vo);
	}

	public Boolean update(UserVO vo) {
		return 1 == sqlSession.update("user.update", vo);
	}

	public Boolean delete(String id) {
		return 1 == sqlSession.delete("user.delete", id);
	}

	public List<UserVO> userList(PageInfo page) {
		return sqlSession.selectList("user.getList", page);
	}
}
