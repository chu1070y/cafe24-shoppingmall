package com.cafe24.shoppingmall.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.cafe24.shoppingmall.vo.OrderDetailVO;
import com.cafe24.shoppingmall.vo.OrderVO;
import com.cafe24.shoppingmall.vo.ProductVO;
import com.cafe24.shoppingmall.vo.UserVO;
import com.google.gson.Gson;

public class OrderControllerTest extends TemplateTest {

	@Override
	public void setup() {
		super.setup();
		cartService.deleteAll();
		nomemberService.deleteAll();
		orderService.deleteAll();
		productService.deleteAll();
		userService.deleteAll();
		
		// 사전 회원가입
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		map.put("name", "chuchu");
		map.put("email", "aaaaa@naver.com");
		map.put("tel_phone", "010-1234-1234");
		
		try { registerMember(map, status().isOk());} catch (Exception e) {e.printStackTrace();}
	}
	
	// 주문 조회(비회원) Test Case 1. - 주문 조회 (성공) - 비회원
	@Test
	public void getOrderNoUsrTest1() throws Exception {
		// 회원정보
		UserVO userVO = new UserVO();
		userVO.setName("비회원");
		userVO.setEmail("ganzzi@men.com");
		
		// 상품 등록 및 주문 정보 입력 함수
		OrderVO orderVO = orderInfo(userVO);
		
		// 주문 등록
		orderAdd(orderVO, status().isOk());		
		
		// 주문 조회(비회원)
		getOrderNoUsr("S0190724-12123221", "1234", status().isOk());	
	}
	
	// 주문 조회(비회원) Test Case 2. - 비밀번호 틀림
	@Test
	public void getOrderNoUsrTest2() throws Exception {
		// 회원정보
		UserVO userVO = new UserVO();
		userVO.setName("비회원");
		userVO.setEmail("ganzzi@men.com");
		
		// 상품 등록 및 주문 정보 입력 함수
		OrderVO orderVO = orderInfo(userVO);
		
		// 주문 등록
		orderAdd(orderVO, status().isOk());		
		
		// 주문 조회(비회원)
		getOrderNoUsr("S0190724-12123221", "123", status().isOk());	
	}
	
	// 주문 조회(회원) Test Case 1. - 주문 조회 (성공) - 회원
	@Test
	public void getOrderUsrTest1() throws Exception {
		// 로그인 하기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		Integer userNo = userLogin(map, status().isOk());
		
		// 회원정보
		UserVO userVO = userInfoRead("chu1070y");
		
		// 주문 정보 입력 함수
		OrderVO orderVO = orderInfo(userVO);
		
		// 주문 등록
		orderAdd(orderVO, status().isOk());
		
		// 주문 조회 - 회원
		getOrderUsr(userNo, status().isOk());	
	}
	
	// 주문 조회 - 회원 Test Case 2. - 주문 조회 (성공) - 해당회원 주문정보 없음
	@Test
	public void getOrderUsrTest2() throws Exception {
		getOrderUsr(3, status().isOk());	
	}
	
	// 주문 등록 Test Case 1. - 주문 등록 (성공) - 회원
	@Test
	public void orderAddTest1() throws Exception {
		// 회원정보
		UserVO userVO = userInfoRead("chu1070y");

		// 주문 정보 입력 함수
		OrderVO orderVO = orderInfo(userVO);
		
		// 주문 등록
		orderAdd(orderVO, status().isOk());		
	}
	
	// 주문 등록 Test Case 2. - 주문 등록 (성공) - 비회원
	@Test
	public void orderAddTest2() throws Exception {
		// 회원정보
		UserVO userVO = new UserVO();
		userVO.setName("비회원");
		userVO.setEmail("ganzzi@men.com");
		
		// 상품 등록 및 주문 정보 입력 함수
		OrderVO orderVO = orderInfo(userVO);
		
		// 주문 등록
		orderAdd(orderVO, status().isOk());		
	}
	
	// 주문 등록 Test Case 3. - 필수 입력값 없음(유효성검사)
	@Test
	public void orderAddTest3() throws Exception {
		// 주문 정보 입력 함수
		OrderVO orderVO = new OrderVO();
		
		// 주문 등록
		orderAdd(orderVO, status().isBadRequest());		
	}
	
	
	/*
	 * 테스트케이스에 사용될 함수들..
	 */
	// 주문 등록 
	public void orderAdd(OrderVO orderVO, ResultMatcher rm) throws Exception {
		ResultActions resultActions = mockMvc.perform(
				post("/api/order/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(orderVO)));

		resultActions.andDo(print()).andExpect(rm);
	}
	// 주문 정보 입력 함수
	public OrderVO orderInfo(UserVO userVO) throws Exception {
		// 상품 등록하고 상품번호 갖고오기
		Integer productNo1 = productAddTest1("호랭이 코트");
		// 특정 상품 정보 가져오기
		ProductVO productVO1 = productRead(productNo1, status().isOk());
		
		// 상품 등록하고 상품번호 갖고오기
		Integer productNo2 = productAddTest1("토깽이 신발");
		// 특정 상품 정보 가져오기
		ProductVO productVO2 = productRead(productNo2, status().isOk());
		
		OrderVO orderVO = new OrderVO();
		orderVO.setMember_no(userVO.getNo());
		orderVO.setOrder_code("S0190724-12123221");
		orderVO.setOrder_name(userVO.getName());
		orderVO.setOrder_addr("지구 어딘가");
		orderVO.setOrder_tel_home("031-111-1234");
		orderVO.setOrder_tel_phone("010-1234-5678");
		orderVO.setOrder_email(userVO.getEmail());
		orderVO.setReceiver_name(userVO.getName());
		orderVO.setReceiver_tel_home("02-222-3333");
		orderVO.setReceiver_tel_phone("010-9999-8282");
		orderVO.setDeliver_addr("너는 어느별에서 왔니-은하수 어딘가");
		orderVO.setDeliver_msg("빛의 속도로 배달 요망(지름길: 블랙홀)");
		orderVO.setDeliver_cost(2500);
		orderVO.setPoint(1000);
		orderVO.setPayment(
				productVO1.getSale_price()*2+productVO1.getProductDetailList().get(0).getAdd_price()
				+ productVO2.getSale_price()+productVO2.getProductDetailList().get(0).getAdd_price()
				+ 2500-1000
				);
		orderVO.setPay_method("kakaopay");
		orderVO.setPay_token("토큰값");
		orderVO.setStatus("결제완료");
		orderVO.setPw("1234");
		
		OrderDetailVO detailVO1 = new OrderDetailVO();
		detailVO1.setProduct_detail_no(productVO1.getProductDetailList().get(0).getProduct_detail_no());
		detailVO1.setPrice(productVO1.getSale_price());
		detailVO1.setQuantity(2);
		
		OrderDetailVO detailVO2 = new OrderDetailVO();
		detailVO2.setProduct_detail_no(productVO2.getProductDetailList().get(0).getProduct_detail_no());
		detailVO2.setPrice(productVO2.getSale_price());
		detailVO2.setQuantity(1);
		
		orderVO.setOrderDetail(Arrays.asList(detailVO1, detailVO2));
		
		return orderVO;
	}
	
	// 주문 조회(회원)
	public void getOrderUsr(Integer userNo, ResultMatcher rm) throws Exception {
		ResultActions resultActions = mockMvc.perform(
				get("/api/order/getOrderUsr").param("memberNo", Integer.toString(userNo)));

		resultActions.andDo(print()).andExpect(rm);
	}
	
	// 주문 조회(비회원)
	public void getOrderNoUsr(String order_code, String pw, ResultMatcher rm) throws Exception {
		ResultActions resultActions = mockMvc.perform(
				get("/api/order/getOrderNoUsr").param("order_code", order_code).param("pw", pw));

		resultActions.andDo(print()).andExpect(rm);
	}
	
}
