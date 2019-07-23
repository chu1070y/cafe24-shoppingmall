package com.cafe24.shoppingmall.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.cafe24.shoppingmall.vo.CategoryVO;
import com.google.gson.Gson;

public class CategoryControllerTest extends TemplateTest{
	
	@Override
	public void setup() {
		super.setup();
		categoryService.deleteAll();
	}
	
	// 카테고리 삭제 Test Case 1. - 카테고리 삭제 (성공)
	@Test
	public void categoryDeleteTest1() throws Exception{
		Integer no = categoryAddGetNo("상의", null, status().isOk()).getCategory_no();
		
		categoryDelete(no, status().isOk());
	}
	
	// 카테고리 수정 Test Case 1. - 카테고리 수정 (성공)
	@Test
	public void categoryUpdateTest1() throws Exception{
		CategoryVO vo = categoryAddGetNo("상의", null, status().isOk());
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategory_no(vo.getCategory_no());
		categoryVO.setCategory_name("shirt");
		categoryVO.setParent(vo.getParent());
		
		categoryUpdate(categoryVO, status().isOk());
	}
	
	// 카테고리 수정 Test Case 2. - 잘못된 카테고리 번호
	@Test
	public void categoryUpdateTest2() throws Exception{
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategory_no(-1);
		categoryVO.setCategory_name("shirt");
		categoryVO.setParent(null);
		
		categoryUpdate(categoryVO, status().isBadRequest());
	}
	
	// 카테고리 수정 Test Case 3. - 해당 상위 카테고리 번호 없음
	@Test
	public void categoryUpdateTest3() throws Exception{
		CategoryVO vo = categoryAddGetNo("상의", null, status().isOk());
		
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategory_no(vo.getCategory_no());
		categoryVO.setCategory_name("shirt");
		categoryVO.setParent(-1);
		
		categoryUpdate(categoryVO, status().isBadRequest());
	}
	
	// 카테고리 리스트 Test Case 1. - 카테고리 리스트 조회 (성공)
	@Test
	public void categoryListTest1() throws Exception{
		Integer no1 = categoryAddGetNo("상의", null, status().isOk()).getCategory_no();
		Integer no2 = categoryAddGetNo("티셔츠", no1, status().isOk()).getCategory_no();
		categoryAdd("반팔 티셔츠", no2, status().isOk());
		
		Integer no3 = categoryAddGetNo("하의", null, status().isOk()).getCategory_no();
		categoryAdd("청바지", no3, status().isOk());
		
		categoryList(status().isOk());
	}
	
	// 카테고리 등록 Test Case 1. - 카테고리 등록 (성공) - 대분류 카테고리 추가
	@Test
	public void checkIdTest1() throws Exception{
		categoryAdd("상의", null, status().isOk());
	}
	
	// 카테고리 등록 Test Case 2. - 카테고리 등록 (성공) - 대분류, 중분류 카테고리 추가
	@Test
	public void checkIdTest2() throws Exception{
		Integer no = categoryAddGetNo("상의", null, status().isOk()).getCategory_no();
		categoryAdd("티셔츠", no, status().isOk());
	}
	
	// 카테고리 등록 Test Case 3. - 카테고리 등록 (성공) - 대분류, 중분류, 소분류 카테고리 추가
	@Test
	public void checkIdTest3() throws Exception{
		Integer no1 = categoryAddGetNo("상의", null, status().isOk()).getCategory_no();
		Integer no2 = categoryAddGetNo("티셔츠", no1, status().isOk()).getCategory_no();
		categoryAdd("반팔 티셔츠", no2, status().isOk());
	}
	
	// 카테고리 등록 Test Case 4. - 카테고리 이름 미입력
	@Test
	public void checkIdTest4() throws Exception{
		categoryAdd(null, null, status().isBadRequest());
	}
	
	// 카테고리 등록 Test Case 5. - 카테고리 이름 빈칸만 입력
	@Test
	public void checkIdTest5() throws Exception{
		categoryAdd(" ", null, status().isBadRequest());
	}
	
	
	/*
	 * 테스트케이스에 사용될 함수들..
	 */
	// 카테고리 등록
	public void categoryAdd(String name, Integer no, ResultMatcher rm) throws Exception {
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategory_name(name);
		categoryVO.setParent(no);
		
		ResultActions resultActions = mockMvc
				.perform(post("/api/category/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(categoryVO)));
		
		resultActions
			.andDo(print())
			.andExpect(rm);
	}
	
	// 카테고리 리스트
	public void categoryList(ResultMatcher rm) throws Exception {
		mockMvc
		.perform(get("/api/category/list"))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	// 카테고리 수정
	public void categoryUpdate(CategoryVO categoryVO, ResultMatcher rm) throws Exception {
			
		ResultActions resultActions = mockMvc
				.perform(post("/api/category/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(categoryVO)));
			
		resultActions
			.andDo(print())
		.andExpect(rm);
	}
	
	// 카테고리 삭제
	public void categoryDelete(Integer category_no, ResultMatcher rm) throws Exception {
		ResultActions resultActions = mockMvc
				.perform(post("/api/category/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(category_no)));
			
		resultActions
			.andDo(print())
			.andExpect(rm);
	}

}
