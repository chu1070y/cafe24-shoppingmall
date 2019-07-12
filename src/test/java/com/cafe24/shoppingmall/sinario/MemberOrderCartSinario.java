package com.cafe24.shoppingmall.sinario;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.config.TestAppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.cafe24.shoppingmall.service.OrderService;
import com.cafe24.shoppingmall.service.UserService;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, TestWebConfig.class })
@WebAppConfiguration
public class MemberOrderCartSinario {
	private MockMvc mockMvc;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	// 테스트를 위해 미리 세팅해야 할 것들...
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		// 사용자 회원가입 미리 해놓기
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		map.put("name", "추연훈");
		map.put("email", "chu1070y@naver.com");
		map.put("tel_phone", "010-5241-1430");
		testUserJoin(map);
	}

	// 테스트 끝난 뒤 DB정보 지우기
	@After
	public void deleteAll() {
		userService.deleteAll();
	}

	/*
	 * 테스트케이스 1: 사용자 비밀번호 틀림(실패)
	 */
	@Test
	public void testcase1() throws Exception {
		// 회원가입 미리 해놓기

		// 사용자 로그인 요청
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("id", "chu1070y");
		map1.put("pw", "12345678z!!");

		testProcess1(map1);

	}

	/*
	 * 테스트케이스 2: 주문 요청시 판매가능수량 0인 경우(실패) - 결과: 장바구니 페이지 redirect
	 */
	@Test
	public void testcase2() throws Exception {
		// 사용자 로그인 요청
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("id", "chu1070y");
		map1.put("pw", "12345678z!");

		testProcess1(map1);

		// 상품목록 요청
		testProcess2();

		// 상품 장바구니에 담기
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("memberNo", "1");
		map3.put("productDetailNo", "2");
		map3.put("quantity", "5");
		testProcess3(map3);

		// 장바구니 페이지 요청
		testProcess4();

		// 장바구니 수량 변경
		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("memberNo", "1");
		map5.put("productDetailNo", "2");
		map5.put("quantity", "2");
		testProcess5(map5);

		// 주문/결제 페이지 요청
		// 판매가능수량 0 설정
		orderService.setStockAvailNum(0);
		testProcess6();

	}
	
	/*
	 * 테스트케이스 3: 주문 성공(성공)
	 */
	@Test
	public void testcase3() throws Exception {
		// 사용자 로그인 요청
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("id", "chu1070y");
		map1.put("pw", "12345678z!");

		testProcess1(map1);

		// 상품목록 요청
		testProcess2();

		// 상품 장바구니에 담기
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("memberNo", "1");
		map3.put("productDetailNo", "2");
		map3.put("quantity", "5");
		testProcess3(map3);

		// 장바구니 페이지 요청
		testProcess4();

		// 장바구니 수량 변경
		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("memberNo", "1");
		map5.put("productDetailNo", "2");
		map5.put("quantity", "2");
		testProcess5(map5);

		// 주문/결제 페이지 요청
		orderService.setStockAvailNum(1);
		testProcess6();

		// 결제 요청
		Map<String, Object> map7 = new HashMap<String, Object>();
		map7.put("memberNo", 1);
		map7.put("orderName", "추연훈");
		map7.put("orderAddr", "경기도 일산동구 공릉천로");
		map7.put("orderTelHome", "031-524-1324");
		map7.put("orderTelPhone", "010-2145-5412");
		map7.put("orderEmail", "aaaa@naver.com");
		map7.put("receiverName", "추연훈");
		map7.put("receiverTelHome", "031-524-1324");
		map7.put("receiverTelPhone", "010-2121-5959");
		map7.put("deliverAddr", "경기도 일산동구 공릉천로");
		map7.put("deliverMsg", "경비실에 맡겨주세요");
		map7.put("deliverCost", 3000);
		map7.put("point", 1000);
		map7.put("payment", 420000);
		map7.put("payMethod", "카카오페이");
		map7.put("pw", "12345678z!");

		List<Map<String, Object>> ordersDetail = new ArrayList<Map<String, Object>>();

		Map<String, Object> map7_1 = new HashMap<String, Object>();
		map7_1.put("productDetailNo", 2);
		map7_1.put("price", 10000);
		map7_1.put("quantity", 1);

		Map<String, Object> map7_2 = new HashMap<String, Object>();
		map7_2.put("productDetailNo", 5);
		map7_2.put("price", 15000);
		map7_2.put("quantity", 2);

		ordersDetail.add(map7_1);
		ordersDetail.add(map7_2);

		map7.put("ordersDetail", ordersDetail);
		testProcess7(map7);

		// 결제 완료
		testProcess8("token 값");
	}

	//
	// 시나리오에 쓰일 함수들...
	//

	// 사용자 로그인 요청
	public void testProcess1(Map<String, Object> map) throws Exception {
		System.out.println("사용자 로그인 요청");

		ResultActions resultActions = mockMvc.perform(
				post("/api/user/login").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")));
	}

	// 상품목록 요청
	public void testProcess2() throws Exception {
		System.out.println("상품목록 요청");

		mockMvc.perform(get("/api/product/list")).andExpect(status().isOk()).andDo(print());
	}

	// 상품 장바구니에 담기
	public void testProcess3(Map<String, Object> map3) throws Exception {
		System.out.println("상품 장바구니에 담기");

		ResultActions resultActions = mockMvc.perform(
				post("/api/cart/insert").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map3)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")));
	}

	// 장바구니 페이지 요청
	public void testProcess4() throws Exception {
		System.out.println("장바구니 페이지 요청");

		mockMvc.perform(get("/api/cart")).andExpect(status().isOk()).andDo(print());
	}

	// 장바구니 수량 변경
	public void testProcess5(Map<String, Object> map5) throws Exception {
		System.out.println("장바구니 수량 변경");

		ResultActions resultActions = mockMvc.perform(
				post("/api/cart/update").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map5)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")));
	}

	// 주문/결제 페이지 요청
	public void testProcess6() throws Exception {
		System.out.println("주문/결제 페이지 요청");

		mockMvc.perform(get("/api/order"))
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$", is("주문/결제 페이지")));
	}

	// 결제 요청
	public void testProcess7(Map<String, Object> map7) throws Exception {
		System.out.println("결제 요청");

		ResultActions resultActions = mockMvc.perform(
				post("/api/order/pay").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map7)));

		resultActions.andExpect(status().isOk()).andDo(print());
	}

	// 결제 완료
	public void testProcess8(String token) throws Exception {
		System.out.println("결제 완료");

		mockMvc.perform(get("/api/order/paySuccess").param("token", token)).andExpect(status().isOk()).andDo(print());
	}

	// 테스트를 위해 사전 미리 해놔야 할 것들...
	// 회원등록 신청
	public void testUserJoin(Map<String, String> map) throws Exception {
		System.out.println("회원등록 신청");

		ResultActions resultActions = mockMvc.perform(post("/api/user/registerMember")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));

		resultActions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.result", is("success")));
	}

}
