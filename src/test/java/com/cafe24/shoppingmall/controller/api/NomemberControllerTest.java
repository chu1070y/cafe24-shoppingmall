package com.cafe24.shoppingmall.controller.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

public class NomemberControllerTest extends TemplateTest{
	
	@Override
	public void setup() {
		super.setup();
		cartService.deleteAll();
		nomemberService.deleteAll();
	}
	
	// 비회원정보 조회 Test Case 1. - 비회원정보 조회(성공) - 비회원 정보가 없는 경우
	@Test
	public void nomemberTest1() throws Exception{
		nomember("sessionid_1", status().isOk());
	}
	
	// 비회원정보 조회 Test Case 2. - 비회원정보 조회(성공) - 비회원 정보 있는 경우
	@Test
	public void nomemberTest2() throws Exception{
		nomember("sessionid_1", status().isOk());
		nomember("sessionid_1", status().isOk());
	}

}
