package com.cafe24.shoppingmall.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.cafe24.shoppingmall.dto.PageInfo;
import com.cafe24.shoppingmall.vo.CategoryVO;
import com.cafe24.shoppingmall.vo.OptionDetailVO;
import com.cafe24.shoppingmall.vo.OptionVO;
import com.cafe24.shoppingmall.vo.ProductDetailVO;
import com.cafe24.shoppingmall.vo.ProductImgVO;
import com.cafe24.shoppingmall.vo.ProductVO;
import com.google.gson.Gson;

public class ProductControllerTest extends TemplateTest {

	@Override
	public void setup() {
		super.setup();
		productService.deleteAll();
	}
	
	// ㄹ Test Case 1. - 재고상황 확인/수정(성공)
	@Test
	public void checkStockTest1() throws Exception {
		Integer no1 = productAddTest2("키가 크는 신발", 100);
		Integer no2 = productAddTest2("어깨가 넓어지는 코트", 10);
		
		// 특정 상품 정보 가져오기
		ProductVO productVO1 = productRead(no1, status().isOk());
		ProductVO productVO2 = productRead(no2, status().isOk());
		
		String[] IntegerArray = {
				Integer.toString(productVO1.getProductDetailList().get(0).getProduct_detail_no()), 
				Integer.toString(productVO2.getProductDetailList().get(0).getProduct_detail_no())
				};
		checkStock(IntegerArray, status().isOk());
	}
	
	// 재고상황 확인/수정 Test Case 2. - 재고 0인 경우(판매가능수량 0)
	@Test
	public void checkStockTest2() throws Exception {
		Integer no1 = productAddTest2("키가 크는 신발", 0);
		Integer no2 = productAddTest2("어깨가 넓어지는 코트", 0);
		
		// 특정 상품 정보 가져오기
		ProductVO productVO1 = productRead(no1, status().isOk());
		ProductVO productVO2 = productRead(no2, status().isOk());
		
		String[] IntegerArray = {
				Integer.toString(productVO1.getProductDetailList().get(0).getProduct_detail_no()), 
				Integer.toString(productVO2.getProductDetailList().get(0).getProduct_detail_no())
				};
		checkStock(IntegerArray, status().isOk());
	}

	// 상품 삭제 Test Case 1. - 상품 삭제(성공)
	@Test
	public void productDeleteTest1() throws Exception {
		Integer no = productAddTest1("빅뱅이론 티셔츠8282");
		productDelete(Integer.toString(no), status().isOk());
	}

	// 상품 삭제 Test Case 2. - 숫자 아닌 값 입력
	@Test
	public void productDeleteTest2() throws Exception {
		productDelete("가나", status().isBadRequest());
	}

	// 상품 삭제 Test Case 3. - 상품번호 없이 삭제 호출
	@Test
	public void productDeleteTest3() throws Exception {
		productDelete("", status().isBadRequest());
	}

	// 상품 수정 Test Case 1. - 상품 수정(성공) + 이미지 + 상품상세 + 카테고리 + 옵션
	@Test
	public void productUpdateTest1() throws Exception {
		Integer no = productAddTest1("빅뱅이론 티셔츠xyz");

		ProductVO productVO = new ProductVO();
		productVO.setNo(no);
		productVO.setName("빅뱅이론 티셔츠xxxxxxxxx");
		productVO.setDescription("맘마미아----------");
		productVO.setPrice(25000);
		productVO.setSale_price(20000);
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
		productDetailVO1.setOption_code("블랙/S");
		productDetailVO1.setAdd_price(1000);
		productDetailVO1.setStock_use("1");
		productDetailVO1.setStock_num(100);
		productDetailVO1.setStock_avail(100);

		ProductDetailVO productDetailVO2 = new ProductDetailVO();
		productDetailVO2.setOption_code("화이트/M");
		productDetailVO2.setAdd_price(500);
		productDetailVO2.setStock_use("0");

		list2.add(productDetailVO1);
		list2.add(productDetailVO2);

		productVO.setProductDetailList(list2);

		// 상품카테고리 수정
		Integer categNo1 = categoryAddGetNo("shirts", null, status().isOk()).getCategory_no();
		Integer categNo2 = categoryAddGetNo("t-shirts", categNo1, status().isOk()).getCategory_no();

		List<CategoryVO> list3 = new ArrayList<CategoryVO>();

		CategoryVO categoryVO1 = new CategoryVO();
		categoryVO1.setCategory_no(categNo1);
		CategoryVO categoryVO2 = new CategoryVO();
		categoryVO2.setCategory_no(categNo2);

		list3.add(categoryVO1);
		list3.add(categoryVO2);

		productVO.setCategoryList(list3);

		// 상품 옵션 수정
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

		productUpdate(productVO, status().isOk());
	}

	// 상품 수정 Test Case 2. - 상품 수정(성공) + 이미지 미포함 + 카테고리 미포함 + 옵션 미포함
	@Test
	public void productUpdateTest2() throws Exception {
		Integer no = productAddTest1("빅뱅이론 티셔츠xyz");

		ProductVO productVO = new ProductVO();
		productVO.setNo(no);
		productVO.setName("빅뱅이론 티셔츠xxxxxxxxx");
		productVO.setDescription("맘마미아----------");
		productVO.setPrice(25000);
		productVO.setSale_price(20000);
		productVO.setShow_product("1");

		// 상품상세 넣기
		List<ProductDetailVO> list2 = new ArrayList<ProductDetailVO>();

		ProductDetailVO productDetailVO1 = new ProductDetailVO();
		productDetailVO1.setAdd_price(1000);
		productDetailVO1.setStock_use("1");
		productDetailVO1.setStock_num(100);
		productDetailVO1.setStock_avail(100);

		ProductDetailVO productDetailVO2 = new ProductDetailVO();
		productDetailVO2.setAdd_price(500);
		productDetailVO2.setStock_use("0");

		list2.add(productDetailVO1);
		list2.add(productDetailVO2);

		productVO.setProductDetailList(list2);

		productUpdate(productVO, status().isOk());
	}

	// 상품 수정 Test Case 3. - 상품상세, 카테고리, 옵션 미포함
	@Test
	public void productUpdateTest3() throws Exception {
		Integer no = productAddTest1("빅뱅이론 티셔츠xyz");

		ProductVO productVO = new ProductVO();
		productVO.setNo(no);
		productVO.setName("빅뱅이론 티셔츠xxxxxxxxx");
		productVO.setDescription("맘마미아----------");
		productVO.setPrice(25000);
		productVO.setSale_price(20000);
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

		productUpdate(productVO, status().isBadRequest());
	}

	// 특정 상품 정보 조회 Test Case 1. - 특정 상품 정보 조회(성공)
	@Test
	public void productReadTest1() throws Exception {
		Integer no = productAddTest1("빅뱅이론 티셔츠z");
		productRead(no, status().isOk());
	}

	// 특정 상품 정보 조회 Test Case 2. - 잘못된 상품번호
	@Test
	public void productReadTest2() throws Exception {
		productRead(-1, status().isBadRequest());
	}

	// 상품 리스트 조회 Test Case 1. - 상품 리스트 조회(성공)
	@Test
	public void productListTest() throws Exception {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(1);
		pageInfo.setDisplay(5);

		for (int i = 0; i < 20; i++) {
			productAddTest1("빅뱅이론 티셔츠" + i);
		}

		productList(pageInfo, status().isOk());
	}

	// 상품 등록 Test Case 1. - 상품 정상 등록(성공) - 이미지, 상세, 카테고리, 옵션
	@Test
	public void productAddTest1() throws Exception {
		ProductVO productVO = new ProductVO();
		productVO.setCode("P0000001");
		productVO.setName("빅뱅이론 티셔츠");
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
		productDetailVO1.setOption_code("쉘든용/black/100");
		productDetailVO1.setAdd_price(1000);
		productDetailVO1.setStock_use("1");
		productDetailVO1.setStock_num(100);
		productDetailVO1.setStock_avail(100);

		ProductDetailVO productDetailVO2 = new ProductDetailVO();
		productDetailVO2.setOption_code("레너드용/white/95");
		productDetailVO2.setAdd_price(500);
		productDetailVO2.setStock_use("0");

		list2.add(productDetailVO1);
		list2.add(productDetailVO2);

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

		productAdd(productVO, status().isOk());
	}

	// 상품 등록 Test Case 2. - 상품 정상 등록(성공) - 이미지 없음, 재고 사용안함, 옵션 없음, 카테고리 없음
	@Test
	public void productAddTest2() throws Exception {
		ProductVO productVO = new ProductVO();
		productVO.setCode("P0000001");
		productVO.setName("빅뱅이론 티셔츠");
		productVO.setDescription("버징가~~~ ㅎㅎ");
		productVO.setPrice(15000);
		productVO.setSale_price(15000);
		productVO.setShow_product("1");

		// 상품상세 넣기
		List<ProductDetailVO> list2 = new ArrayList<ProductDetailVO>();

		ProductDetailVO productDetailVO1 = new ProductDetailVO();
		productDetailVO1.setStock_use("0");

		list2.add(productDetailVO1);

		productVO.setProductDetailList(list2);

		productAdd(productVO, status().isOk());
	}

	// 상품 등록 Test Case 3. - 상품상세 없음
	@Test
	public void productAddTest3() throws Exception {
		ProductVO productVO = new ProductVO();
		productVO.setCode("P0000001");
		productVO.setName("빅뱅이론 티셔츠");
		productVO.setDescription("버징가~~~ ㅎㅎ");
		productVO.setPrice(15000);
		productVO.setSale_price(15000);
		productVO.setShow_product("1");

		productAdd(productVO, status().isBadRequest());
	}

	// 상품 등록 Test Case 4. - 상품명 미입력
	@Test
	public void productAddTest4() throws Exception {
		ProductVO productVO = new ProductVO();
		productVO.setCode("P0000001");
		productVO.setName("");
		productVO.setDescription("버징가~~~ ㅎㅎ");
		productVO.setPrice(15000);
		productVO.setSale_price(15000);
		productVO.setShow_product("1");

		productAdd(productVO, status().isBadRequest());
	}

	// 상품 등록 Test Case 5. - 가격 미입력
	@Test
	public void productAddTest5() throws Exception {
		ProductVO productVO = new ProductVO();
		productVO.setCode("P0000001");
		productVO.setName("빅뱅이론 티셔츠");
		productVO.setDescription("버징가~~~ ㅎㅎ");
		productVO.setSale_price(15000);
		productVO.setShow_product("1");

		productAdd(productVO, status().isBadRequest());
	}

	/*
	 * 테스트케이스에 사용될 함수들..
	 */
	// 상품 등록
	public void productAdd(ProductVO productVO, ResultMatcher rm) throws Exception {
		ResultActions resultActions = mockMvc.perform(
				post("/api/product/add").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(productVO)));

		resultActions.andDo(print()).andExpect(rm);
	}

	// 상품 리스트
	public void productList(PageInfo pageinfo, ResultMatcher rm) throws Exception {
		mockMvc.perform(get("/api/product/list").param("page", pageinfo.getPage().toString()).param("display",
				pageinfo.getDisplay().toString())).andDo(print()).andExpect(rm);
	}

	// 상품 수정
	public void productUpdate(ProductVO productVO, ResultMatcher rm) throws Exception {
		ResultActions resultActions = mockMvc.perform(post("/api/product/update")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(productVO)));

		resultActions.andDo(print()).andExpect(rm);
	}

	// 상품 삭제
	public void productDelete(String no, ResultMatcher rm) throws Exception {
		ResultActions resultActions = mockMvc.perform(
				post("/api/product/delete").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(no)));

		resultActions.andDo(print()).andExpect(rm);
	}
	
	// 재고상황 확인/수정
	public void checkStock(String[] productDetailNoList, ResultMatcher rm) throws Exception {
		mockMvc.perform(get("/api/product/checkStock").param("productDetailNoList", productDetailNoList)).andDo(print()).andExpect(rm);
	}
	// test 재고상품 등록
	public Integer productAddTest2(String name, Integer stockNum) throws Exception {
		ProductVO productVO = new ProductVO();
		productVO.setCode("P0000001");
		productVO.setName(name);
		productVO.setDescription("버징가~~~ ㅎㅎ");
		productVO.setPrice(15000);
		productVO.setSale_price(15000);
		productVO.setShow_product("1");

		// 상품상세 넣기
		List<ProductDetailVO> list2 = new ArrayList<ProductDetailVO>();
		ProductDetailVO productDetailVO1 = new ProductDetailVO();
		productDetailVO1.setStock_use("1");
		productDetailVO1.setStock_num(stockNum);
		productDetailVO1.setStock_avail(stockNum);

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
}
