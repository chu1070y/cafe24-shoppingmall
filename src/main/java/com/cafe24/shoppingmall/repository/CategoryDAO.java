package com.cafe24.shoppingmall.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.CategoryVO;

@Repository
public class CategoryDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public CategoryVO insert(CategoryVO vo) {
		sqlSession.insert("category.insert", vo);
		return vo;
	}

	public Boolean deleteAll() {
		return 1 == sqlSession.delete("category.deleteAll");
	}

	public List<CategoryVO> getList() {
		return sqlSession.selectList("category.getList");
	}

	public Boolean update(CategoryVO vo) {
		return 1 == sqlSession.update("category.update", vo);
	}

	public Boolean checkCategoryNo(Integer category_high_no) {
		Integer number = sqlSession.selectOne("category.checkCategoryNo", category_high_no);
		return 0 == number;
	}

	public List<CategoryVO> getList(Integer category_no) {
		return sqlSession.selectList("category.getLowCategList", category_no);
	}

}
