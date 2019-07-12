package com.cafe24.shoppingmall.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
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
public class UserControllerTest {
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
	public void testUserPage() throws Exception{
		mockMvc
		.perform(get("/api/user/join"))
		.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testUserCheckId() throws Exception{
		mockMvc
		.perform(get("/api/user/checkId")
		.param("id", "bbbbbb"))
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")));
	}
	
	@Test
	public void testUserRegisterMember() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "bbbvv2");
		map.put("pw", "12345678z!");
		map.put("name", "chuchu");
		map.put("email", "aaaaa@naver.com");
		map.put("tel_phone", "010-1234-1234");
		
		ResultActions resultActions = mockMvc
				.perform(post("/api/user/registerMember")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(map)));
		
		resultActions
			.andExpect(status().isOk()).andDo(print())
			.andExpect(jsonPath("$.result", is("success")));
	}
	
	@Test
	public void testUserLogin() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "bbbvv1");
		map.put("pw", "12345678z!");
		
		ResultActions resultActions = mockMvc
				.perform(post("/api/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(map)));
		
		resultActions
			.andExpect(status().isOk()).andDo(print())
			.andExpect(jsonPath("$.result", is("success")));
	}
	
	

}
