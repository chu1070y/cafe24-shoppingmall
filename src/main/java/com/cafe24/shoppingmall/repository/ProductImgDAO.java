package com.cafe24.shoppingmall.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.ProductImgVO;

@Repository
public class ProductImgDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(ProductImgVO vo) {
		
		return 1 == sqlSession.insert("productImg.insert", vo);
	}
}
