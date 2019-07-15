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

import com.google.gson.Gson;

public class UserControllerTest extends TemplateTest{
	
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
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	public void testUserPage() throws Exception{
		mockMvc
		.perform(get("/api/user/join"))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testUserCheckId() throws Exception{
		mockMvc
		.perform(get("/api/user/checkId")
		.param("id", "bbbbbb"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.result", is("success")));
	}
	
	@Test
	public void testUserRegisterMember() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "bbbvv1");
		map.put("pw", "12345678z!");
		map.put("name", "chuchu");
		map.put("email", "aaaaa@naver.com");
		map.put("tel_phone", "010-1234-1234");
		
		ResultActions resultActions = mockMvc
				.perform(post("/api/user/registerMember")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(map)));
		
		resultActions
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.result", is("success")));
	}
	

	
	

}
