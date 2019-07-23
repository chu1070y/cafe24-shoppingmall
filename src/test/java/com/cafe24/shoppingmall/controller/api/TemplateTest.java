package com.cafe24.shoppingmall.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.shoppingmall.config.TestAppConfig;
import com.cafe24.shoppingmall.config.TestWebConfig;
import com.cafe24.shoppingmall.service.CartService;
import com.cafe24.shoppingmall.service.CategoryService;
import com.cafe24.shoppingmall.service.ProductService;
import com.cafe24.shoppingmall.service.UserService;
import com.cafe24.shoppingmall.vo.CategoryVO;
import com.cafe24.shoppingmall.vo.OptionDetailVO;
import com.cafe24.shoppingmall.vo.OptionVO;
import com.cafe24.shoppingmall.vo.ProductDetailVO;
import com.cafe24.shoppingmall.vo.ProductImgVO;
import com.cafe24.shoppingmall.vo.ProductVO;
import com.cafe24.shoppingmall.vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

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
	
	@Autowired
	protected CartService cartService;

	JacksonJsonParser jsonParser = new JacksonJsonParser();
	ObjectMapper oMapper = new ObjectMapper();
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build();
	}
	
	// 카테고리 등록 
	public CategoryVO categoryAddGetNo(String name, Integer no, ResultMatcher rm) throws Exception {
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
		
		String resultString = resultActions.andReturn().getResponse().getContentAsString();
		CategoryVO categoryVO2 = oMapper.convertValue(jsonParser.parseMap(resultString).get("data"), CategoryVO.class);
		
		return categoryVO2;
	}
	
	// 회원가입
	public void registerMember(Map<String, Object> map, ResultMatcher rm) throws Exception{
		ResultActions resultActions = mockMvc
				.perform(post("/api/user/registerMember")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(map)));
		
		resultActions
			.andDo(print())
			.andExpect(rm);
	}
	
	// 로그인
	public Integer userLogin(Map<String, Object> map, ResultMatcher rm) throws Exception{
		ResultActions resultActions = mockMvc
				.perform(post("/api/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(map)));
		
		resultActions
			.andDo(print())
			.andExpect(rm);
		
		String resultString = resultActions.andReturn().getResponse().getContentAsString();
		UserVO userVO = oMapper.convertValue(jsonParser.parseMap(resultString).get("data"), UserVO.class);
		
		return userVO == null ? null : userVO.getNo();
	}
	
	// test 상품 등록
	public Integer productAddTest1(String name) throws Exception {
		ProductVO productVO = new ProductVO();
		productVO.setCode("P0000001");
		productVO.setName(name);
		productVO.setDescription("버징가~~~ ㅎㅎ");
		productVO.setPrice(15000);
		productVO.setSale_price(15000);
		productVO.setShow_product("1");

		// 이미지 넣기
		List<ProductImgVO> list1 = new ArrayList<ProductImgVO>();

		ProductImgVO productImgVO1 = new ProductImgVO();
		productImgVO1.setFilename("tshirts_img1");
		productImgVO1.setExtension(".png");
		productImgVO1.setImg_type("대표이미지");

		ProductImgVO productImgVO2 = new ProductImgVO();
		productImgVO2.setFilename("tshirts_img2");
		productImgVO2.setExtension(".png");
		productImgVO2.setImg_type("본문이미지");

		list1.add(productImgVO1);
		list1.add(productImgVO2);

		productVO.setProductImgList(list1);

		// 상품상세 넣기
		List<ProductDetailVO> list2 = new ArrayList<ProductDetailVO>();

		ProductDetailVO productDetailVO1 = new ProductDetailVO();
		productDetailVO1.setStock_use("0");

		list2.add(productDetailVO1);

		productVO.setProductDetailList(list2);

		// 상품카테고리 등록
		Integer categNo1 = categoryAddGetNo("상의", null, status().isOk()).getCategory_no();
		Integer categNo2 = categoryAddGetNo("티셔츠", categNo1, status().isOk()).getCategory_no();

		List<CategoryVO> list3 = new ArrayList<CategoryVO>();

		CategoryVO categoryVO1 = new CategoryVO();
		categoryVO1.setCategory_no(categNo1);
		CategoryVO categoryVO2 = new CategoryVO();
		categoryVO2.setCategory_no(categNo2);

		list3.add(categoryVO1);
		list3.add(categoryVO2);

		productVO.setCategoryList(list3);

		// 상품 옵션 등록
		OptionVO optionVO1 = new OptionVO();
		optionVO1.setName("색상");
		optionVO1.setNecessary("1");

		OptionDetailVO optionDetailVO1 = new OptionDetailVO();
		optionDetailVO1.setDetail_name("블랙");
		OptionDetailVO optionDetailVO2 = new OptionDetailVO();
		optionDetailVO2.setDetail_name("화이트");

		optionVO1.setOptionDetailList(Arrays.asList(optionDetailVO1, optionDetailVO2));

		OptionVO optionVO2 = new OptionVO();
		optionVO2.setName("크기");
		optionVO2.setNecessary("1");

		OptionDetailVO optionDetailVO3 = new OptionDetailVO();
		optionDetailVO3.setDetail_name("S");
		OptionDetailVO optionDetailVO4 = new OptionDetailVO();
		optionDetailVO4.setDetail_name("M");
		OptionDetailVO optionDetailVO5 = new OptionDetailVO();
		optionDetailVO5.setDetail_name("L");

		optionVO2.setOptionDetailList(Arrays.asList(optionDetailVO3, optionDetailVO4, optionDetailVO5));

		productVO.setOptionList(Arrays.asList(optionVO1, optionVO2));

		return productAddGetNo(productVO, status().isOk());
	}
	
	// 상품 등록
	public Integer productAddGetNo(ProductVO productVO, ResultMatcher rm) throws Exception {
		ResultActions resultActions = mockMvc.perform(
				post("/api/product/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(productVO)));

		resultActions.andDo(print()).andExpect(rm);
		
		String resultString = resultActions.andReturn().getResponse().getContentAsString();
		Integer productNo = oMapper.convertValue(jsonParser.parseMap(resultString).get("data"), Integer.class);

		return productNo;
	}
	
	// 특정 상품 조회
	public ProductVO productRead(Integer no, ResultMatcher rm) throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/api/product/" + no)).andDo(print()).andExpect(rm);
		
		String resultString = resultActions.andReturn().getResponse().getContentAsString();
		ProductVO productVO = oMapper.convertValue(jsonParser.parseMap(resultString).get("data"), ProductVO.class);
		
		return productVO;
	}
}
