package com.cafe24.shoppingmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.repository.CategoryDAO;
import com.cafe24.shoppingmall.vo.CategoryVO;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDAO categoryDAO;

	public CategoryVO insert(CategoryVO vo) {
		return categoryDAO.insert(vo);
	}
	
	public Boolean deleteAll() {
		return categoryDAO.deleteAll();
	}

	public List<CategoryVO> getList() {
		return categoryDAO.getList();
	}

	public Boolean update(CategoryVO vo) {
		return categoryDAO.update(vo);
	}

	public Boolean checkCategoryNo(CategoryVO vo) {
		// 상위 카테고리 번호가 null이면 해당 로직을 건너뛴다.
		if(vo.getParent() == vo.getCategory_no()) {
			return false;
		}
		
		return categoryDAO.checkCategoryNo(vo.getParent());
	}

	@Transactional
	public Boolean delete(Integer no) {
		return categoryDAO.delete(no);
	}

	public Boolean updateOrd(CategoryVO categoryVO) {
		return categoryDAO.updateOrd(categoryVO);
	}

	public List<CategoryVO> getLowList(CategoryVO vo) {
		return categoryDAO.getLowList(vo);
	}

	public Boolean updateParent(CategoryVO categoryVO) {
		return categoryDAO.updateParent(categoryVO);
	}

}
