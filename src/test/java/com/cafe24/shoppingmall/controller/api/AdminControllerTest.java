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
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {TestAppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class AdminControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build();
	}
	
	@Test
	public void testAdminLogin() throws Exception{
		mockMvc
		.perform(post("/api/admin/login")
		.param("id", "chu1070y")
		.param("pw", "12345678!z"))
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")));
	}

	@Test
	public void testAdminPage() throws Exception{
		mockMvc
		.perform(get("/api/admin/productAdmin"))
		.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testAdminProductRegister() throws Exception{
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
		
		ResultActions resultActions = mockMvc
				.perform(post("/api/admin/productRegister")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(map)));
		
		resultActions
			.andExpect(status().isOk()).andDo(print())
			.andExpect(jsonPath("$.result", is("success")));
	}
	
	
	
}
