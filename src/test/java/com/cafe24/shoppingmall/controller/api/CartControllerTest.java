package com.cafe24.shoppingmall.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.cafe24.shoppingmall.vo.CartVO;
import com.cafe24.shoppingmall.vo.NomemberVO;
import com.cafe24.shoppingmall.vo.ProductVO;
import com.google.gson.Gson;

public class CartControllerTest extends TemplateTest {
	
	@Override
	public void setup() {
		super.setup();
		nomemberService.deleteAll();
		userService.deleteAll();
		cartService.deleteAll();
		
		// 사전 회원가입
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		map.put("name", "chuchu");
		map.put("email", "aaaaa@naver.com");
		map.put("tel_phone", "010-1234-1234");
		
		try { registerMember(map, status().isOk());} catch (Exception e) {e.printStackTrace();}
	}
	
	@Test
	public void testCartPage() throws Exception {
		mockMvc.perform(get("/api/cart")).andDo(print()).andExpect(status().isOk());
	}
	
	// 장바구니 등록 Test Case 1. - 장바구니 등록 (성공) - 회원
	@Test
	public void CartAddtest1() throws Exception {
		// 로그인 후 회원번호 갖고오기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		Integer userNo = userLogin(map, status().isOk());
		
		// 상품 등록하고 상품번호 갖고오기
		Integer productNo = productAddTest1("너네가 찾던 코트");
		
		// 특정 상품 정보 가져오기
		ProductVO productVO = productRead(productNo, status().isOk());
		
		// 장바구니 담기
		CartVO cartVO = new CartVO();
		cartVO.setMember_no(userNo);
		cartVO.setProduct_detail_no(productVO.getProductDetailList().get(0).getProduct_detail_no());
		cartVO.setQuantity(1);

		CartAdd(cartVO, status().isOk());
	}
	
	// 장바구니 등록 Test Case 2. - 장바구니 등록 (성공) - 비회원
	@Test
	public void CartAddtest2() throws Exception {
		// 비회원 등록
		NomemberVO nomemberVO = nomember("sessionid_1", status().isOk());
		
		// 상품 등록하고 상품번호 갖고오기
		Integer productNo = productAddTest1("너네가 찾던 코트");
		
		// 특정 상품 정보 가져오기
		ProductVO productVO = productRead(productNo, status().isOk());
		
		// 장바구니 담기
		CartVO cartVO = new CartVO();
		cartVO.setNomember_no(nomemberVO.getNo());
		cartVO.setProduct_detail_no(productVO.getProductDetailList().get(0).getProduct_detail_no());
		cartVO.setQuantity(1);

		CartAdd(cartVO, status().isOk());
	}

	@Test
	public void testCartUpdate() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberNo", "1");
		map.put("nomemberNo", null);
		map.put("productDetailNo", "2");
		map.put("quantity", "2");

		ResultActions resultActions = mockMvc.perform(
				post("/api/cart/update").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));

		resultActions.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.result", is("success")));
	}
	
	/*
	 * 테스트케이스에 사용될 함수들..
	 */
	// 장바구니 등록
	public void CartAdd(CartVO cartVO, ResultMatcher rm) throws Exception {
		ResultActions resultActions = mockMvc.perform(
				post("/api/cart/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(cartVO)));

		resultActions.andDo(print()).andExpect(status().isOk()).andExpect(rm);
	}

}
