package com.cafe24.shoppingmall.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.OrderService;
import com.cafe24.shoppingmall.vo.OrderVO;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

	@Autowired
	OrderService orderService;

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

		orderService.orderAdd(orderVO);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("주문 등록 성공"));
	}

}
