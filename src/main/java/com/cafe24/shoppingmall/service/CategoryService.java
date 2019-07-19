package com.cafe24.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.repository.CategoryDAO;
import com.cafe24.shoppingmall.vo.CategoryVO;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDAO categoryDAO;

	public Integer insertCart(CategoryVO vo) {
		return categoryDAO.insert(vo);
	}
	
	public Boolean deleteAll() {
		return categoryDAO.deleteAll();
	}

}
