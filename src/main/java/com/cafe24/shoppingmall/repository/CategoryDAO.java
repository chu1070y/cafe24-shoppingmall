package com.cafe24.shoppingmall.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.CategoryVO;

@Repository
public class CategoryDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public Integer insert(CategoryVO vo) {
		sqlSession.insert("category.insert", vo);
		return vo.getCategory_no();
	}

	public Boolean deleteAll() {
		return 1 == sqlSession.delete("category.deleteAll");
	}

}
