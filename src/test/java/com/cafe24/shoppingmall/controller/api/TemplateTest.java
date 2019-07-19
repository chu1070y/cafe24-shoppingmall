package com.cafe24.shoppingmall.controller.api;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.config.TestAppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.cafe24.shoppingmall.service.CategoryService;
import com.cafe24.shoppingmall.service.ProductService;
import com.cafe24.shoppingmall.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {TestAppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class TemplateTest {
	protected MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext webApplicationContext;
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected ProductService productService;
	
	@Autowired
	protected CategoryService categoryService;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build();
	}
	
	public String parsingNo(String text) {
		
		Integer idx1 = text.lastIndexOf("<no>") + 4;
		Integer idx2 = text.lastIndexOf("</no>");
		System.out.println(idx1 + ":" + idx2);
		return text.substring(idx1,idx2);
	}
	
	public String[] parsingNo2(String text) {
		String[] result = new String[2];
		
		Integer idx1 = text.lastIndexOf("<no>") + 4;
		Integer idx2 = text.lastIndexOf("</no>");
		result[0] = text.substring(idx1,idx2);
		
		Integer idx3 = text.lastIndexOf("<no2>") + 5;
		Integer idx4 = text.lastIndexOf("</no2>");
		result[1] = text.substring(idx3,idx4);
		
		return result;
	}
}
