package com.cafe24.shoppingmall.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

public class ProductControllerTest extends TemplateTest {

	@Test
	public void testList() throws Exception {
		mockMvc.perform(get("/api/product/list")).andDo(print()).andExpect(status().isOk());
	}

}
