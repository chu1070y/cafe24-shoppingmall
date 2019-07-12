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
	public String stockCheck() {
		
		// DB에 들어가 판매가능수량을 확인한다.
		Integer stock = this.stockAvail;
		
		// 판매가능수량이 0인경우
		if (stock == 0) {
			return "장바구니 페이지(상품품절 표시)";
		}
		
		// 판매가능수량이 1인경우
		// DB에 들어가 판매가능수량 -1 업데이트
		return "주문/결제 페이지";
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
