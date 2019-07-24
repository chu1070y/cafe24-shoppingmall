package com.cafe24.shoppingmall.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.cafe24.shoppingmall.vo.CartVO;
import com.cafe24.shoppingmall.vo.NomemberVO;
import com.cafe24.shoppingmall.vo.ProductVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class CartControllerTest extends TemplateTest {
	
	JacksonJsonParser jsonParser = new JacksonJsonParser();
	ObjectMapper oMapper = new ObjectMapper();
	
	@Override
	public void setup() {
		super.setup();
		cartService.deleteAll();
		nomemberService.deleteAll();
		userService.deleteAll();
		productService.deleteAll();
		
		// 사전 회원가입
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		map.put("name", "chuchu");
		map.put("email", "aaaaa@naver.com");
		map.put("tel_phone", "010-1234-1234");
		
		try { registerMember(map, status().isOk());} catch (Exception e) {e.printStackTrace();}
	}
	
	// 장바구니 리스트 Test Case 1. - 장바구니 리스트 조회 (성공) - 회원
	@Test
	public void cartListTest1() throws Exception {
		// 로그인 후 회원번호 갖고오기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		Integer userNo = userLogin(map, status().isOk());
		
		// 상품 등록하고 상품번호 갖고오기
		Integer productNo = productAddTest1("너네가 찾던 코트");
		// 특정 상품 정보 가져오기
		ProductVO productVO = productRead(productNo, status().isOk());
		
		// 장바구니 담기1
		CartVO cartVO = new CartVO();
		cartVO.setMember_no(userNo);
		cartVO.setProduct_detail_no(productVO.getProductDetailList().get(0).getProduct_detail_no());
		cartVO.setQuantity(1);
		cartAdd(cartVO, status().isOk());
		
		// 상품 등록하고 상품번호 갖고오기
		productNo = productAddTest1("너네가 찾던 티셔츠");
		// 특정 상품 정보 가져오기
		productVO = productRead(productNo, status().isOk());
		// 장바구니 담기2
		cartVO.setProduct_detail_no(productVO.getProductDetailList().get(0).getProduct_detail_no());
		cartVO.setQuantity(2);
		cartAdd(cartVO, status().isOk());
		
		cartList(cartVO, status().isOk());
	}
	
	// 장바구니 리스트 Test Case 2. - 장바구니 리스트 조회 (성공) - 비회원
	@Test
	public void cartListTest2() throws Exception {
		// 비회원 등록
		NomemberVO nomemberVO = nomember("sessionid_1", status().isOk());
		
		// 상품 등록하고 상품번호 갖고오기
		Integer productNo = productAddTest1("너네가 찾던 코트");
		// 특정 상품 정보 가져오기
		ProductVO productVO = productRead(productNo, status().isOk());
		
		// 장바구니 담기1
		CartVO cartVO = new CartVO();
		cartVO.setNomember_no(nomemberVO.getNo());
		cartVO.setProduct_detail_no(productVO.getProductDetailList().get(0).getProduct_detail_no());
		cartVO.setQuantity(1);
		cartAdd(cartVO, status().isOk());
		
		// 상품 등록하고 상품번호 갖고오기
		productNo = productAddTest1("너네가 찾던 티셔츠");
		// 특정 상품 정보 가져오기
		productVO = productRead(productNo, status().isOk());
		// 장바구니 담기2
		cartVO.setProduct_detail_no(productVO.getProductDetailList().get(0).getProduct_detail_no());
		cartVO.setQuantity(2);
		cartAdd(cartVO, status().isOk());
		
		cartList(cartVO, status().isOk());
	}
	
	// 장바구니 등록 Test Case 1. - 장바구니 등록 (성공) - 회원
	@Test
	public void cartAddTest1() throws Exception {
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

		cartAdd(cartVO, status().isOk());
	}
	
	// 장바구니 등록 Test Case 2. - 장바구니 등록 (성공) - 비회원
	@Test
	public void cartAddTest2() throws Exception {
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

		cartAdd(cartVO, status().isOk());
	}
	
	// 장바구니 수량 변경 Test Case 1. - 장바구니 수량 변경 (성공) - 회원
	@Test
	public void cartUpdateTest1() throws Exception {
		// 로그인 후 회원번호 갖고오기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		Integer userNo = userLogin(map, status().isOk());
		
		// 장바구니 상품 등록
		cartAddForTest("니가 원하는 청바지", userNo , null);
		
		CartVO cartVO = new CartVO();
		cartVO.setMember_no(userNo);

		// 장바구니 가져오기
		Object cartNo = cartListForTest(cartVO, status().isOk());
		
		// 장바구니 수량 변경
		cartVO.setNo((Integer) cartNo);
		cartVO.setQuantity(3);
		
		cartUpdate(cartVO, status().isOk());
	}
	
	// 장바구니 수량 변경 Test Case 2. - 장바구니 수량 변경 (성공) - 비회원
	@Test
	public void cartUpdateTest2() throws Exception {
		// 비회원 등록
		NomemberVO nomemberVO = nomember("sessionid_1", status().isOk());
		
		// 장바구니 상품 등록
		cartAddForTest("니가 원하는 청바지2", null, nomemberVO.getNo());
		
		CartVO cartVO = new CartVO();
		cartVO.setNomember_no(nomemberVO.getNo());

		// 장바구니 가져오기
		Object cartNo = cartListForTest(cartVO, status().isOk());
		
		// 장바구니 수량 변경
		cartVO.setNo((Integer) cartNo);
		cartVO.setQuantity(4);
		
		cartUpdate(cartVO, status().isOk());
	}
	
	// 장바구니 수량 변경 Test Case 3. - 존재하지 않는 장바구니 번호
	@Test
	public void cartUpdateTest3() throws Exception {
		// 비회원 등록
		NomemberVO nomemberVO = nomember("sessionid_1", status().isOk());
		
		// 장바구니 상품 등록
		cartAddForTest("니가 원하는 청바지2", null, nomemberVO.getNo());
		
		CartVO cartVO = new CartVO();
		cartVO.setNomember_no(nomemberVO.getNo());
		cartVO.setNo(-1);
		cartVO.setQuantity(4);
		
		cartUpdate(cartVO, status().isBadRequest());
	}
	
	// 장바구니 수량 변경 Test Case 4. - 수량을 -1로 입력하였을 때
	@Test
	public void cartUpdateTest4() throws Exception {
		// 비회원 등록
		NomemberVO nomemberVO = nomember("sessionid_1", status().isOk());
		
		// 장바구니 상품 등록
		cartAddForTest("니가 원하는 청바지2", null, nomemberVO.getNo());
		
		CartVO cartVO = new CartVO();
		cartVO.setNomember_no(nomemberVO.getNo());

		// 장바구니 가져오기
		Object cartNo = cartListForTest(cartVO, status().isOk());
		
		// 장바구니 수량 변경
		cartVO.setNo((Integer) cartNo);
		cartVO.setQuantity(-1);
		
		cartUpdate(cartVO, status().isBadRequest());
	}
	
	// 장바구니 상품 삭제 Test Case 1. - 장바구니 상품 삭제 (성공) - 회원
	@Test
	public void cartDeleteTest1() throws Exception {
		// 로그인 후 회원번호 갖고오기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		Integer userNo = userLogin(map, status().isOk());
		
		// 장바구니 상품 등록
		cartAddForTest("니가 원하는 청바지", userNo , null);
		
		CartVO cartVO = new CartVO();
		cartVO.setMember_no(userNo);

		// 장바구니 가져오기
		Integer cartNo = (Integer) cartListForTest(cartVO, status().isOk());
		
		// 장바구니 상품 삭제
		cartDelete(cartNo, status().isOk());
	}
	
	// 장바구니 상품 삭제 Test Case 2. - 장바구니 상품 삭제 (성공) - 비회원
	@Test
	public void cartDeleteTest2() throws Exception {
		// 비회원 등록
		NomemberVO nomemberVO = nomember("sessionid_1", status().isOk());
				
		// 장바구니 상품 등록
		cartAddForTest("니가 원하는 청바지2", null, nomemberVO.getNo());
				
		CartVO cartVO = new CartVO();
		cartVO.setNomember_no(nomemberVO.getNo());

		// 장바구니 가져오기
		Integer cartNo = (Integer) cartListForTest(cartVO, status().isOk());
		
		// 장바구니 상품 삭제
		cartDelete(cartNo, status().isOk());
	}
	
	// 장바구니 상품 삭제 Test Case 3. - 존재하지 않는 장바구니 번호
	@Test
	public void cartDeleteTest3() throws Exception {
		// 장바구니 상품 삭제
		cartDelete(-1, status().isBadRequest());
	}
	
	/*
	 * 테스트케이스에 사용될 함수들..
	 */
	// 장바구니 상품 등록
	public void cartAdd(CartVO cartVO, ResultMatcher rm) throws Exception {
		ResultActions resultActions = mockMvc.perform(
				post("/api/cart/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(cartVO)));

		resultActions.andDo(print()).andExpect(status().isOk()).andExpect(rm);
	}
	// 장바구니 자동 등록 (다른 test용)
	public void cartAddForTest(String name, Integer member_no, Integer nomember_no) throws Exception {
		// 상품 등록하고 상품번호 갖고오기
		Integer productNo = productAddTest1(name);
		// 특정 상품 정보 가져오기
		ProductVO productVO = productRead(productNo, status().isOk());
		// 장바구니 담기
		CartVO cartVO = new CartVO();
		cartVO.setMember_no(member_no);
		cartVO.setNomember_no(nomember_no);
		cartVO.setProduct_detail_no(productVO.getProductDetailList().get(0).getProduct_detail_no());
		cartVO.setQuantity(1);

		cartAdd(cartVO, status().isOk());
	}
	
	// 장바구니 리스트
	public void cartList(CartVO cartVO, ResultMatcher rm) throws Exception {
		ResultActions resultActions = 
				mockMvc.perform(get("/api/cart/list")
						.param("member_no", cartVO.getMember_no() == null ? null : Integer.toString(cartVO.getMember_no()))
						.param("nomember_no", cartVO.getNomember_no() == null ? null : Integer.toString(cartVO.getNomember_no())));
		
		resultActions.andDo(print()).andExpect(status().isOk()).andExpect(rm);
		
	}
	// 장바구니 리스트 (다른 테스트 용)
	public Object cartListForTest(CartVO cartVO, ResultMatcher rm) throws Exception {
		ResultActions resultActions = 
				mockMvc.perform(get("/api/cart/list")
						.param("member_no", cartVO.getMember_no() == null ? null : Integer.toString(cartVO.getMember_no()))
						.param("nomember_no", cartVO.getNomember_no() == null ? null : Integer.toString(cartVO.getNomember_no())));
		
		resultActions.andDo(print()).andExpect(status().isOk()).andExpect(rm);
		
		String resultString = resultActions.andReturn().getResponse().getContentAsString();
		List<Map<String, Object>> list = oMapper.convertValue(jsonParser.parseMap(resultString).get("data"), List.class);
		
		return list.get(0).get("no");
	}
	
	// 장바구니 수량 변경
	public void cartUpdate(CartVO cartVO, ResultMatcher rm) throws Exception {
		ResultActions resultActions = mockMvc.perform(
				post("/api/cart/update").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(cartVO)));

		resultActions.andDo(print()).andExpect(rm);
	}

	// 장바구니 상품 삭제
	public void cartDelete(Integer no, ResultMatcher rm) throws Exception {
		ResultActions resultActions = mockMvc.perform(
				post("/api/cart/delete").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(no)));

		resultActions.andDo(print()).andExpect(rm);
	}
	
}
