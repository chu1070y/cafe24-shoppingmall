package com.cafe24.shoppingmall.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.NomemberVO;

@Repository
public class NomemberDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public NomemberVO get(NomemberVO nomemberVO) {
		return sqlSession.selectOne("nomember.get", nomemberVO);
	}

	public NomemberVO insert(NomemberVO nomemberVO) {
		sqlSession.insert("nomember.insert", nomemberVO);
		return nomemberVO;
	}

	public Boolean deleteAll() {
		return 1 == sqlSession.delete("nomember.deleteAll");
	}
	
	
}
