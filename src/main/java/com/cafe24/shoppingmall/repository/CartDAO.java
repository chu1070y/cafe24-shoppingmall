package com.cafe24.shoppingmall.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.CartVO;

@Repository
public class CartDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(CartVO vo) {
		return 1 == sqlSession.insert("cart.insert", vo);
	}

	public Boolean deleteAll() {
		return 1 == sqlSession.delete("cart.deleteAll");
	}

	public Boolean update(CartVO vo) {
		return 1 == sqlSession.update("cart.update", vo);
	}

	public List<CartVO> get(CartVO vo) {
		return sqlSession.selectList("cart.getList", vo);
	}
}
