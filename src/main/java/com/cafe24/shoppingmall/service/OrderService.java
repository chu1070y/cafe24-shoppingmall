package com.cafe24.shoppingmall.service;

import org.springframework.stereotype.Service;

import com.cafe24.shoppingmall.vo.api.OrderApiVO;

@Service
public class OrderService {
	
	public String pay(OrderApiVO vo) {
		// 주문 정보 캐시에 저장해놓기
		System.out.println(vo);
		// 결제 API 준비
		// 사용자 결제 API 페이지로 보내기 위한 url 리턴하기
		
		return "결제 API 페이지";
	}

	// @Transactional 처리
	public Integer stockCheck() {
		
		// DB에 들어가 판매가능수량을 확인한다.
		Integer stock = this.stockAvail;
		
		return stock;
	}
	
	// @Transactional 처리
	public Boolean paySuccess(String token) {
		// 토큰 값으로 결제정보 확인
		System.out.println(token);
		// 캐시에서 저장된 사용자 주문 정보 불러옴
		// DB에 접속하여 주문 insert
		// 주문상세 insert
		// 장바구니 update
		return true;
	}
	
	
	
	// 테스트용 판매가능수량 설정 - DB 구현시 삭제 예정
	private Integer stockAvail = 0;
	public void setStockAvailNum(Integer stock) {
		this.stockAvail = stock;
	}
	public Integer getStockAvailNum() {
		return this.stockAvail;
	}


}
