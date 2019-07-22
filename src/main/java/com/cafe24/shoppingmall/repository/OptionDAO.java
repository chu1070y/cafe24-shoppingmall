package com.cafe24.shoppingmall.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.shoppingmall.vo.OptionDetailVO;
import com.cafe24.shoppingmall.vo.OptionVO;

@Repository
public class OptionDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public OptionVO insert(OptionVO vo) {
		sqlSession.insert("option.insert", vo);
		return vo;
	}
	
	public OptionDetailVO insertDetail(OptionDetailVO vo) {
		sqlSession.insert("option.insertDetail", vo);
		return vo;
	}
	
	public Boolean deleteAll() {
		sqlSession.delete("option.deleteAllDetail");
		return 1 == sqlSession.delete("option.deleteAll");
	}

	public Boolean deleteByOptionVO(OptionVO optionVO) {
		sqlSession.delete("option.deleteDetail", optionVO.getOption_no());
		return 1 == sqlSession.delete("option.delete", optionVO.getProduct_no());
	}
}
