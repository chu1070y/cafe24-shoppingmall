package com.cafe24.shoppingmall.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.cafe24.shoppingmall.vo.AdminVO;
import com.google.gson.Gson;

public class AdminControllerTest extends TemplateTest{

	@Override
	public void setup() {
		super.setup();
		adminService.deleteAll();
	}
	
	// 관리자 로그인 Test Case 1. - 관리자 로그인(성공)
	@Test
	public void adminLoginTest1() throws Exception{
		adminAdd("admin", "12345678z!", status().isOk());
		adminLogin("admin", "12345678z!", status().isOk());
	}
	
	// 관리자 로그인 Test Case 2. - 관리자 로그인 실패
	@Test
	public void adminLoginTest2() throws Exception{
		adminAdd("admin", "12345678z!", status().isOk());
		adminLogin("admin", "123456", status().isBadRequest());
	}
	
	// 관리자 아이디 체크 Test Case 1. - 아이디 체크 결과 사용가능(성공)
	@Test
	public void checkIdTest1() throws Exception{
		checkId("admin", status().isOk());
	}
	
	// 관리자 아이디 체크 Test Case 2. - 아이디 체크 결과 중복(성공)
	@Test
	public void checkIdTest2() throws Exception{
		adminAdd("admin", "12345678z!", status().isOk());
		checkId("admin", status().isOk());
	}
	
	// 관리자 회원가입 Test Case 1. - 관리자 정상 등록(성공)
	@Test
	public void registerMemberTest1() throws Exception{
		adminAdd("admin", "12345678z!", status().isOk());
	}
	
	// 관리자 회원가입 Test Case 2. - 비밀번호 형식 오류
	@Test
	public void registerMemberTest2() throws Exception{
		adminAdd("admin", "12345678z", status().isBadRequest());
	}
	
	/*
	 * 테스트케이스에 사용될 함수들..
	 */
	// 관리자 아이디 체크
	public void checkId(String id, ResultMatcher rm) throws Exception{
		mockMvc
		.perform(get("/api/admin/checkId")
		.param("id", id))
		.andDo(print())
		.andExpect(rm);
	}
	
	// 관리자 등록
	public void adminAdd(String id, String pw, ResultMatcher rm) throws Exception{
		AdminVO adminVO = new AdminVO();
		adminVO.setId(id);
		adminVO.setPw(pw);
		
		ResultActions resultActions = mockMvc
				.perform(post("/api/admin/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(adminVO)));
		
		resultActions
			.andDo(print())
			.andExpect(rm);
	}
	
	// 관리자 로그인
	public void adminLogin(String id, String pw, ResultMatcher rm) throws Exception{
		AdminVO adminVO = new AdminVO();
		adminVO.setId(id);
		adminVO.setPw(pw);
		
		ResultActions resultActions = mockMvc
				.perform(post("/api/admin/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(adminVO)));
		
		resultActions
			.andDo(print())
			.andExpect(rm);
	}
	
	

}
