package com.cafe24.shoppingmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.dto.PageInfo;
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

	@Transactional
	public Map<String, Object> getList(PageInfo pageInfo) {
		Integer cnt = productDAO.totalCount();

		pageInfo.setTotalCount(cnt);

		List<ProductVO> productList = productDAO.getList(pageInfo);
		
		for (ProductVO vo : productList) {
			List<ProductImgVO> imgList = productImgDAO.getImgs(vo.getNo());
			vo.setProductImgList(imgList);
		}
		
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("pageInfo", pageInfo);
		map.put("productList", productList);
		
		return map;
	}

	@Transactional
	public Integer insert(ProductVO productVO) {
		
		// 상품상세 없이 요청왔을시 false값 리턴
		if (productVO.getProductDetailList() == null) {
			return -1;
		}
		
		// 상품 등록
		ProductVO vo = productDAO.insert(productVO);
		
		// 상품 상세 등록
		for(ProductDetailVO detailVO: vo.getProductDetailList()) {
			// 상품 등록 후 no 가져와 상품 상세 등록
			detailVO.setProduct_no(vo.getNo());
			productDetailDAO.insert(detailVO);
		}
		
		// 상품 이미지 등록
		if (vo.getProductImgList() != null) {
			for(ProductImgVO imgVO : vo.getProductImgList()) {
				// 상품 등록 후 no 가져와 상품 이미지 등록
				imgVO.setProduct_no(vo.getNo());
				productImgDAO.insert(imgVO);
			}
		}
		
		return vo.getNo();
	}

	@Transactional
	public void deleteAll() {
		productImgDAO.deleteAll();
		productDetailDAO.deleteAll();
		productDAO.deleteAll();
	}

	@Transactional
	public ProductVO getProduct(Integer no) {
		ProductVO vo = productDAO.getProduct(no);
		
		if(vo==null) { return null;}
		
		vo.setProductImgList(productImgDAO.getImgs(no));
		vo.setProductDetailList(productDetailDAO.getDetails(no));
		
		return vo;
	}

}
