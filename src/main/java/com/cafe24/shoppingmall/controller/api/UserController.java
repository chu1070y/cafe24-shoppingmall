package com.cafe24.shoppingmall.controller.api;

import java.util.List;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.UserService;
import com.cafe24.shoppingmall.vo.LoginVO;
import com.cafe24.shoppingmall.vo.UserVO;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> joinPage() {
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("회원가입 페이지"));
	}
	
	@GetMapping(value = "/checkId")
	public ResponseEntity<JSONResult> checkId(
			@RequestParam("id") String id) {

        if(!Pattern.matches("^[a-zA-Z0-9]{4,18}$", id)){
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("아이디 입력형식 오류"));
        }
		
		return userService.checkId(id)==true? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("중복")) :
					ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("사용가능"));
	}
	
	@PostMapping(value = "/registerMember")
	public ResponseEntity<JSONResult> registerMember(
			@RequestBody @Valid UserVO vo,
			BindingResult result) {
		
		// 아이디 중복 한번 더 체크
		if(userService.checkId(vo.getId())) {
			return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("중복 아이디"));
		}
		
		// 유효성 검사 실패시
		if(result.hasErrors()) {
			List<FieldError> list = result.getFieldErrors();
			String errorMsg = "";
			for(FieldError error : list) {
				errorMsg += error.getField() + "/";
			}
			errorMsg += "오류발생";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(errorMsg));
		}
		
		UserVO userVO = userService.registerMember(vo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("회원 등록 성공, User:" + userVO));
	}
	
	@PostMapping("/login")
	public ResponseEntity<JSONResult> login(@RequestBody @Valid LoginVO vo, BindingResult result) {
		// 유효성 검사 실패시
		if(result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("유효성검사로 인한 로그인 실패"));
		}
		
		return userService.login(vo) ? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("로그인 성공")) : 
					ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("로그인 실패"));
	}

}