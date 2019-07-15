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

public class CartControllerTest extends TemplateTest {

	@Test
	public void testCartPage() throws Exception {
		mockMvc.perform(get("/api/cart")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testCartInsert() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberNo", "1");
		map.put("nomemberNo", "null");
		map.put("productDetailNo", "2");
		map.put("quantity", "5");

		ResultActions resultActions = mockMvc.perform(
				post("/api/cart/insert").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));

		resultActions.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.result", is("success")));
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

}
