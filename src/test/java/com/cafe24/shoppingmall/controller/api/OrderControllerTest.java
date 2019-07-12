package com.cafe24.shoppingmall.controller.api;

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
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {TestAppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class OrderControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private OrderService orderService;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void testOrderPage() throws Exception{
		orderService.setStockAvailNum(0);
		
		mockMvc
		.perform(get("/api/order"))
		.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testOrderPay() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberNo", 1);
		map.put("orderName", "추연훈");
		map.put("orderAddr", "경기도 일산동구 공릉천로");
		map.put("orderTelHome", "031-524-1324");
		map.put("orderTelPhone", "010-2145-5412");
		map.put("orderEmail", "aaaa@naver.com");
		map.put("receiverName", "추연훈");
		map.put("receiverTelHome", "031-524-1324");
		map.put("receiverTelPhone", "010-2121-5959");
		map.put("deliverAddr", "경기도 일산동구 공릉천로");
		map.put("deliverMsg", "경비실에 맡겨주세요");
		map.put("deliverCost", 3000);
		map.put("point", 1000);
		map.put("payment", 420000);
		map.put("payMethod", "카카오페이");
		map.put("pw", "12345678z!");
		
		List<Map<String, Object>> ordersDetail = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("productDetailNo", 2);
		map1.put("price", 10000);
		map1.put("quantity", 1);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("productDetailNo", 5);
		map2.put("price", 15000);
		map2.put("quantity", 2);
		
		ordersDetail.add(map1);
		ordersDetail.add(map2);
		
		map.put("ordersDetail", ordersDetail);

		
		ResultActions resultActions = mockMvc
				.perform(post("/api/order/pay")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(map)));
		
		resultActions
			.andExpect(status().isOk()).andDo(print());
	}
}
