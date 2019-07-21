package com.cafe24.shoppingmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.shoppingmall.dto.PageInfo;
import com.cafe24.shoppingmall.repository.CategoryDAO;
import com.cafe24.shoppingmall.repository.ProductDAO;
import com.cafe24.shoppingmall.repository.ProductDetailDAO;
import com.cafe24.shoppingmall.repository.ProductImgDAO;
import com.cafe24.shoppingmall.vo.CategoryVO;
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
	
	@Autowired
	private CategoryDAO categoryDAO;

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
		
		// 상품 카테고리 등록
		if (vo.getCategoryList() != null) {
			for(CategoryVO categVO : vo.getCategoryList()) {
				categVO.setProduct_no(vo.getNo());
				categoryDAO.insertCategoryProduct(categVO);
			}
		}
		
		return vo.getNo();
	}
	
	// junit 테스트용 테이블 전부 삭제 로직
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
	
	// 상품 수정
	@Transactional
	public Boolean update(ProductVO productVO){
		// 상품상세 없이 요청왔을시 false값 리턴
		if (productVO.getProductDetailList() == null) {
			return false;
		}
		
		// 상품번호 없을 시 false값 리턴
		Integer no = productVO.getNo();
		if(no == null) {
			return false;
		}
		
		// 상품 수정
		productDAO.update(productVO);
		
		// 상품 상세 수정
		for(ProductDetailVO detailVO: productVO.getProductDetailList()) {
			
			// 새로운 상품 상세 정보를 추가했을 경우
			if (detailVO.getProduct_detail_no() == null) {
				detailVO.setProduct_no(no);
				productDetailDAO.updateDel(no);
				productDetailDAO.insert(detailVO);
				continue;
			}
			
			// 기존 상품 상세 정보를 삭제한 경우
			if (detailVO.getDetail_del() == "1") {
				productDetailDAO.updateDetailDel(no);
				continue;
			}
			
			// 기존 상품 상세 정보를 수정한 경우
			productDetailDAO.update(detailVO);		
		}
		
		// 상품 이미지 수정
		if (productVO.getProductImgList() != null) {
			productImgDAO.deleteByNo(no);
			for(ProductImgVO imgVO: productVO.getProductImgList()) {
				imgVO.setProduct_no(no);
				productImgDAO.insert(imgVO);
			}
		}
		
		// 상품 카테고리 수정
		if (productVO.getCategoryList() != null) {
			categoryDAO.deleteCategProductByProductNo(no);
			for(CategoryVO categVO: productVO.getCategoryList()) {
				categVO.setProduct_no(no);
				categoryDAO.insertCategoryProduct(categVO);
			}
		}
		
		return true;
	}

	// 상품 삭제
	@Transactional
	public Boolean delete(Integer no) {
		
		productDAO.updateDel(no);
		productDetailDAO.updateDel(no);
		productImgDAO.deleteByNo(no);
		
		return true;
	}

}
