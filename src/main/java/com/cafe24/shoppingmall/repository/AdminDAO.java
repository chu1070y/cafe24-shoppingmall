package com.cafe24.shoppingmall.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.AdminVO;

@Repository
public class AdminDAO {

	@Autowired
	private SqlSession sqlSession;

	public Boolean checkId(String id) {
		return sqlSession.selectOne("admin.checkId", id) != null;
	}

	public Boolean add(AdminVO vo) {
		return 1 == sqlSession.insert("admin.add", vo);
	}

	public Boolean deleteAll() {
		return 1 == sqlSession.delete("admin.deleteAll");
	}
}
