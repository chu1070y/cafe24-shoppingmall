package com.cafe24.shoppingmall.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.cafe24.shoppingmall.dto.PageInfo;
import com.cafe24.shoppingmall.vo.ProductDetailVO;
import com.cafe24.shoppingmall.vo.ProductImgVO;
import com.cafe24.shoppingmall.vo.ProductVO;
import com.google.gson.Gson;

public class ProductControllerTest extends TemplateTest {
	
	@Override
	public void setup() {
		super.setup();
		productService.deleteAll();
	}
	
	// 상품 리스트 조회 Test Case 1. - 상품 리스트 조회(성공)
	@Test
	public void productListTest() throws Exception {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(1);
		pageInfo.setDisplay(5);
		
		for(int i = 0; i < 20; i++) {
			productAddTest2("빅뱅이론 티셔츠" + i);
		}
		
		productList(pageInfo, status().isOk());
	}
	
	// 상품 등록 Test Case 1. - 상품 정상 등록(성공)
	@Test
	public void productAddTest1() throws Exception{
		ProductVO productVO = new ProductVO();
		productVO.setCode("P0000001");
		productVO.setName("빅뱅이론 티셔츠");
		productVO.setDescription("버징가~~~ ㅎㅎ");
		productVO.setPrice(15000);
		productVO.setSale_price(15000);
		
		// 이미지 넣기
		List<ProductImgVO> list1 = new ArrayList<ProductImgVO>();
		
		ProductImgVO productImgVO1 = new ProductImgVO();
		productImgVO1.setFilename("tshirts_img1");
		productImgVO1.setExtension(".png");
		productImgVO1.setImg_type("대표이미지");
		
		ProductImgVO productImgVO2 = new ProductImgVO();
		productImgVO2.setFilename("tshirts_img2");
		productImgVO2.setExtension(".png");
		productImgVO2.setImg_type("본문이미지");
		
		list1.add(productImgVO1);
		list1.add(productImgVO2);
		
		productVO.setProductImgList(list1);
		
		// 상품상세 넣기
		List<ProductDetailVO> list2 = new ArrayList<ProductDetailVO>();
		
		ProductDetailVO productDetailVO1 = new ProductDetailVO();
		productDetailVO1.setOption_code("쉘든용/black/100");
		productDetailVO1.setAdd_price(1000);
		productDetailVO1.setStock_use("1");
		productDetailVO1.setStock_num(100);
		productDetailVO1.setStock_avail(100);
		productDetailVO1.setDisplay("1");
		
		ProductDetailVO productDetailVO2 = new ProductDetailVO();
		productDetailVO2.setOption_code("레너드용/white/95");
		productDetailVO2.setAdd_price(500);
		productDetailVO2.setStock_use("0");
		productDetailVO2.setDisplay("1");
		
		list2.add(productDetailVO1);
		list2.add(productDetailVO2);
		
		productVO.setProductDetailList(list2);
		
		productAdd(productVO, status().isOk());
	}
	
	// 상품 등록 Test Case 2. - 상품 정상 등록(성공) - 이미지 없음, 재고 사용안함, 옵션 없음
	@Test
	public void productAddTest2() throws Exception{
		ProductVO productVO = new ProductVO();
		productVO.setCode("P0000001");
		productVO.setName("빅뱅이론 티셔츠");
		productVO.setDescription("버징가~~~ ㅎㅎ");
		productVO.setPrice(15000);
		productVO.setSale_price(15000);
		
		// 상품상세 넣기
		List<ProductDetailVO> list2 = new ArrayList<ProductDetailVO>();
		
		ProductDetailVO productDetailVO1 = new ProductDetailVO();
		productDetailVO1.setStock_use("0");
		productDetailVO1.setDisplay("1");
		
		list2.add(productDetailVO1);
		
		productVO.setProductDetailList(list2);
		
		productAdd(productVO, status().isOk());
	}
	
	// 상품 등록 Test Case 3. - 상품상세 없음
	@Test
	public void productAddTest3() throws Exception{
		ProductVO productVO = new ProductVO();
		productVO.setCode("P0000001");
		productVO.setName("빅뱅이론 티셔츠");
		productVO.setDescription("버징가~~~ ㅎㅎ");
		productVO.setPrice(15000);
		productVO.setSale_price(15000);
		
		productAdd(productVO, status().isBadRequest());
	}
	
	// 상품 등록 Test Case 4. - 상품명 미입력
		@Test
		public void productAddTest4() throws Exception{
			ProductVO productVO = new ProductVO();
			productVO.setCode("P0000001");
			productVO.setName("");
			productVO.setDescription("버징가~~~ ㅎㅎ");
			productVO.setPrice(15000);
			productVO.setSale_price(15000);

			productAdd(productVO, status().isBadRequest());
		}
		
	// 상품 등록 Test Case 5. - 가격 미입력
		@Test
		public void productAddTest5() throws Exception{
			ProductVO productVO = new ProductVO();
			productVO.setCode("P0000001");
			productVO.setName("빅뱅이론 티셔츠");
			productVO.setDescription("버징가~~~ ㅎㅎ");
			productVO.setSale_price(15000);

			productAdd(productVO, status().isBadRequest());
		}
	
	/*
	 * 테스트케이스에 사용될 함수들..
	 */
	// 상품 등록
	public void productAdd(ProductVO productVO, ResultMatcher rm) throws Exception{
		ResultActions resultActions = mockMvc
				.perform(post("/api/product/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(productVO)));
		
		resultActions
			.andDo(print())
			.andExpect(rm);
	}
	
	// 상품 리스트
	public void productList(PageInfo pageinfo, ResultMatcher rm) throws Exception {
		mockMvc.perform(get("/api/product/list")
				.param("page", pageinfo.getPage().toString())
				.param("display", pageinfo.getDisplay().toString()))
		.andDo(print()).andExpect(rm);
	}
	// 상품 리스트전 상품 등록
	public void productAddTest2(String name) throws Exception{
		ProductVO productVO = new ProductVO();
		productVO.setCode("P0000001");
		productVO.setName(name);
		productVO.setDescription("버징가~~~ ㅎㅎ");
		productVO.setPrice(15000);
		productVO.setSale_price(15000);
		
		// 이미지 넣기
		List<ProductImgVO> list1 = new ArrayList<ProductImgVO>();
				
		ProductImgVO productImgVO1 = new ProductImgVO();
		productImgVO1.setFilename("tshirts_img1");
		productImgVO1.setExtension(".png");
		productImgVO1.setImg_type("대표이미지");
				
		ProductImgVO productImgVO2 = new ProductImgVO();
		productImgVO2.setFilename("tshirts_img2");
		productImgVO2.setExtension(".png");
		productImgVO2.setImg_type("본문이미지");
				
		list1.add(productImgVO1);
		list1.add(productImgVO2);
				
		productVO.setProductImgList(list1);
		
		// 상품상세 넣기
		List<ProductDetailVO> list2 = new ArrayList<ProductDetailVO>();
		
		ProductDetailVO productDetailVO1 = new ProductDetailVO();
		productDetailVO1.setStock_use("0");
		productDetailVO1.setDisplay("1");
		
		list2.add(productDetailVO1);
		
		productVO.setProductDetailList(list2);
		
		productAdd(productVO, status().isOk());
	}

}
