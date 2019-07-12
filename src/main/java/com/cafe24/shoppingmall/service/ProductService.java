package com.cafe24.shoppingmall.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.vo.ProductVO;

@Service
public class ProductService {

	public List<ProductVO> getList() {
		
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		ProductVO vo1 = new ProductVO();
		vo1.setNo(1);
		vo1.setCode("p0000001");
		vo1.setName("프렌즈 티셔츠");
		vo1.setDescription("덕후들을 위한 티셔츠!");
		vo1.setPrice(15000);
		vo1.setSalePrice(12500);
		vo1.setDel("0");
		
		vo1.setProductDetailNo(1);
		vo1.setOptionCode("블랙/M/슬림핏");
		vo1.setAddPrice(0);
		vo1.setStockNum(10);
		vo1.setStockAvail(10);
		vo1.setStockUse("1");
		vo1.setDisplay("1");
		vo1.setDetailDel("0");
		
		list.add(vo1);
		
		vo1.setProductDetailNo(2);
		vo1.setOptionCode("화이트/S/슬림핏");
		vo1.setAddPrice(1000);
		vo1.setStockNum(15);
		vo1.setStockAvail(15);
		vo1.setStockUse("1");
		vo1.setDisplay("1");
		vo1.setDetailDel("0");
		
		list.add(vo1);
		
		return list;
	}

	public Boolean insertCart(ProductVO vo) {
		// DB 접속해 장바구니에 상품담기
		System.out.println(vo);
		return true;
	}
}
