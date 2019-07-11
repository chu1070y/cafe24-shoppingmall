package com.cafe24.shoppingmall.sinario;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
import com.cafe24.shoppingmall.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {TestAppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class UserJoinSinarioTest {
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
	 * 테스트케이스 1: 중복된 아이디 존재(실패)
	 */
	@Test
	public void testcase1() throws Exception{
		// 유저정보 입력 - 사전 등록
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("id", "chu1070y");
		map1.put("pw", "12345678z!");
		map1.put("name", "추연훈");
		map1.put("email", "chu1070y@naver.com");
		map1.put("tel_phone", "010-5241-1430");
		testProcess3(map1);
		
		// 중복 유저정보 입력
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("id", "chu1070y");
		map2.put("pw", "12345678z!");
		map2.put("name", "추연훈");
		map2.put("email", "chu1070y@naver.com");
		map2.put("tel_phone", "010-5241-1430");
		
		testProcess1();
		testProcess2(map2.get("id"));
		testProcess3(map2);
	}

	/*
	 * 테스트케이스 2: 아이디 길이 3자리(실패)
	 */
	@Test
	public void testcase2() throws Exception{
		// 유저정보 입력
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "chu");
		map.put("pw", "12345678z!");
		map.put("name", "추연훈");
		map.put("email", "chu1070y@naver.com");
		map.put("tel_phone", "010-5241-1430");
		
		testProcess1();
		testProcess2(map.get("id"));
		testProcess3(map);
	}
	
	/*
	 * 테스트케이스 3: 비밀번호 형식 특수문자 미포함(실패)
	 */
	@Test
	public void testcase3() throws Exception{
		// 유저정보 입력
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z");
		map.put("name", "추연훈");
		map.put("email", "chu1070y@naver.com");
		map.put("tel_phone", "010-5241-1430");
		
		testProcess1();
		testProcess2(map.get("id"));
		testProcess3(map);
	}
	
	/*
	 * 테스트케이스 4: 잘못된 이메일 형식(실패)
	 */
	@Test
	public void testcase4() throws Exception{
		// 유저정보 입력
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		map.put("name", "추연훈");
		map.put("email", "chu1070y");
		map.put("tel_phone", "010-5241-1430");
		
		testProcess1();
		testProcess2(map.get("id"));
		testProcess3(map);
	}
	
	/*
	 * 테스트케이스 5: 올바른 입력 양식 제출(성공)
	 */
	@Test
	public void testcase5() throws Exception{
		// 유저정보 입력
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		map.put("name", "추연훈");
		map.put("email", "chu1070y@naver.com");
		map.put("tel_phone", "010-5241-1430");
		
		testProcess1();
		testProcess2(map.get("id"));
		testProcess3(map);
	}
	
	//
	// 시나리오에 쓰일 함수들...
	//
	
	// 회원가입 페이지 요청
	public void testProcess1() throws Exception{
		System.out.println("회원가입 페이지 요청");
		mockMvc
		.perform(get("/api/user/join"))
		.andExpect(status().isOk()).andDo(print());
	}
	
	// 아이디 중복 체크 요청
	public void testProcess2(String id) throws Exception{
		System.out.println("아이디 중복 체크 요청");
		mockMvc
		.perform(get("/api/user/checkId")
		.param("id", id))
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")));
	}
	
	// 회원등록 신청
	public void testProcess3(Map<String, String> map) throws Exception{
		System.out.println("회원등록 신청");
		mockMvc
		.perform(post("/api/user/registerMember")
		.param("id", map.get("id"))
		.param("pw", map.get("pw"))
		.param("name", map.get("name"))
		.param("email", map.get("email"))
		.param("tel_phone", map.get("tel_phone")))
		.andExpect(status().isOk()).andDo(print())
		.andExpect(jsonPath("$.result", is("success")));
	}

}
