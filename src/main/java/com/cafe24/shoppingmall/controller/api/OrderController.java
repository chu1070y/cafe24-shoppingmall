package com.cafe24.shoppingmall.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.OrderService;
import com.cafe24.shoppingmall.vo.OrderVO;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	// 주문 등록
	@PostMapping("/add")
	public ResponseEntity<JSONResult> orderAdd(@RequestBody @Valid OrderVO orderVO, BindingResult result) {

		// 유효성 검사 실패시
		if (result.hasErrors()) {
			List<FieldError> list = result.getFieldErrors();
			String errorMsg = "";
			for (FieldError error : list) {
				errorMsg += error.getField() + "/";
			}
			errorMsg += "오류발생";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(errorMsg));
		}

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(orderService.orderAdd(orderVO)));
	}
	
	// 주문 조회 - 회원
	@GetMapping("/getOrderUsr")
	public ResponseEntity<JSONResult> orderGetOrderUsr(@RequestParam("memberNo") Integer memberNo) {

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(orderService.orderGet(memberNo)));
	}
	
	// 쥬문 조회 - 비회원
	@GetMapping("/getOrderNoUsr")
	public ResponseEntity<JSONResult> orderGetOrderNoUsr(@ModelAttribute OrderVO vo) {
		List<OrderVO> orderList = orderService.orderGetNoUser(vo);
		return orderList == null ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("주문번호나 비밀번호가 틀립니다")) : ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(orderList));
	}
	
	// 주문 상태 수정
	@PostMapping("/update")
	public ResponseEntity<JSONResult> orderUpdate(@RequestBody OrderVO vo) {
		return orderService.update(vo) ?
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("주문 상태 업데이트 성공")) :
					ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("주문 상태 업데이트 실패 - 주문번호 없음"));
	}
}
