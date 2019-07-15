package com.cafe24.shoppingmall.sinario;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.config.TestAppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.cafe24.shoppingmall.service.UserService;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {TestAppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class AdminProductSinario {
	private MockMvc mockMvc;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build();
	}
	
	@After
	public void deleteAll() {
		userService.deleteAll();
	}
	
	/*
	 * 테스트케이스 1: 이름 2글자 미만(실패)
	 */
	@Test
	public void testcase1() throws Exception{
		// 관리자 로그인 요청
		testProcess1("c","12345678!z", status().isBadRequest());
	}
	
	/*
	 * 테스트케이스 2: 상품 등록(성공)
	 */
	@Test
	public void testcase2() throws Exception{
		// 관리자 로그인 요청
		testProcess1("chu1070y","12345678!z", status().isOk());
		// 상품관리 페이지 요청
		testProcess2(status().isOk());
		// 상품등록 요청
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "프렌즈 티셔츠");
		map.put("description", "덕후들을 위한 티셔츠!");
		map.put("price", "15000");
		
		testProcess3(map, status().isOk());
	}
	
	/*
	 * 테스트케이스 3: 상품 + 상품이미지 등록(성공)
	 */
	@Test
	public void testcase3() throws Exception{
		// 관리자 로그인 요청
		testProcess1("chu1070y","12345678!z", status().isOk());
		// 상품관리 페이지 요청
		testProcess2(status().isOk());
		// 상품등록 요청
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "프렌즈 티셔츠");
		map.put("description", "덕후들을 위한 티셔츠!");
		map.put("price", "15000");
		map.put("saleprice", "12500");
		map.put("optioncode", "블랙/M/슬림핏");
		map.put("addprice", "0");
		map.put("display", "1");
		
		Map<String, Object> mapImg1 = new HashMap<String, Object>();
		mapImg1.put("filename", "friendsTshirts1");
		mapImg1.put("extension", "png");
		mapImg1.put("imgType", "메인");
		
		Map<String, Object> mapImg2 = new HashMap<String, Object>();
		mapImg2.put("filename", "friendsTshirts2");
		mapImg2.put("extension", "png");
		mapImg2.put("imgType", "본문");
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list.add(mapImg1);
		list.add(mapImg2);
		
		map.put("productImgList", list);
		
		testProcess3(map, status().isOk());
	}

	/*
	 * 테스트케이스 4: 상품 + 상품이미지 + 상품재고 등록(성공)
	 */
	@Test
	public void testcase4() throws Exception{
		// 관리자 로그인 요청
		testProcess1("chu1070y","12345678!z", status().isOk());
		// 상품관리 페이지 요청
		testProcess2(status().isOk());
		// 상품등록 요청
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "프렌즈 티셔츠");
		map.put("description", "덕후들을 위한 티셔츠!");
		map.put("price", "15000");
		map.put("saleprice", "12500");
		map.put("optioncode", "블랙/M/슬림핏");
		map.put("addprice", "0");
		map.put("stockNum", "10");
		map.put("stockUse", "1");
		map.put("display", "1");
		
		Map<String, Object> mapImg1 = new HashMap<String, Object>();
		mapImg1.put("filename", "friendsTshirts1");
		mapImg1.put("extension", "png");
		mapImg1.put("imgType", "메인");
		
		Map<String, Object> mapImg2 = new HashMap<String, Object>();
		mapImg2.put("filename", "friendsTshirts2");
		mapImg2.put("extension", "png");
		mapImg2.put("imgType", "본문");
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list.add(mapImg1);
		list.add(mapImg2);
		
		map.put("productImgList", list);
		
		testProcess3(map, status().isOk());
	}	
	
	
	//
	// 시나리오에 쓰일 함수들...
	//
	public void testProcess1(String id, String pw, ResultMatcher resultMatcher) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pw", pw);
		
		ResultActions resultActions = mockMvc
				.perform(post("/api/admin/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(map)));
		
		resultActions
			.andDo(print())
			.andExpect(resultMatcher);
	}

	public void testProcess2(ResultMatcher resultMatcher) throws Exception{
		mockMvc
		.perform(get("/api/admin/productAdmin"))
		.andExpect(resultMatcher).andDo(print());
	}

	public void testProcess3(Map<String, Object> map, ResultMatcher resultMatcher) throws Exception{
		ResultActions resultActions = mockMvc
				.perform(post("/api/admin/productRegister")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(map)));
		
		resultActions
			.andExpect(resultMatcher).andDo(print());
	}
}
