package com.cafe24.shoppingmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.repository.ProductDAO;
import com.cafe24.shoppingmall.repository.ProductDetailDAO;
import com.cafe24.shoppingmall.repository.ProductImgDAO;
import com.cafe24.shoppingmall.vo.ProductDetailVO;
import com.cafe24.shoppingmall.vo.ProductImgVO;
import com.cafe24.shoppingmall.vo.ProductVO;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ProductImgDAO productImgDAO;
	
	@Autowired
	private ProductDetailDAO productDetailDAO;

	public List<ProductVO> getList() {
		
		return null;
	}

	@Transactional
	public Boolean insert(ProductVO productVO) {
		ProductVO vo = productDAO.insert(productVO);
		
		for(ProductImgVO imgVO : vo.getProductImgList()) {
			imgVO.setProduct_no(vo.getNo());
			productImgDAO.insert(imgVO);
		}
		
		for(ProductDetailVO detailVO: vo.getProductDetailList()) {
			detailVO.setProduct_no(vo.getNo());
			productDetailDAO.insert(detailVO);
		}
		
		return true;
	}

}
