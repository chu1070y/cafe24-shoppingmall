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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.dto.PageInfo;
import com.cafe24.shoppingmall.service.UserService;
import com.cafe24.shoppingmall.vo.UserVO;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//회원가입 페이지 for 시나리오용
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public ResponseEntity<JSONResult> joinPage() {
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("회원가입 페이지"));
	}
	
	//회원가입 전 아이디 중복여부 확인
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
	
	//회원가입
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
	
	//로그인
	@PostMapping("/login")
	public ResponseEntity<JSONResult> login(@RequestBody UserVO vo, BindingResult result) {
        UserVO uservo = userService.login(vo);
		
		return uservo != null ? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(uservo)) : 
					ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("해당 정보와 일치하는 값이 없습니다."));
	}
	
	//회원정보 조회- 1명
	@GetMapping("/read")
	public ResponseEntity<JSONResult> readMember(@RequestParam("id") String id){
		UserVO vo = userService.getUser(id);
		return vo == null? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("회원 없음")): ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(vo));
	}
	
	//회원정보 수정
	@PostMapping("/update")
	public ResponseEntity<JSONResult> updateMember(@RequestBody @Valid UserVO vo, BindingResult result){
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
		
		return userService.update(vo) ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("회원정보 수정 성공")) : ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("해당 정보와 일치하는 회원이 없습니다."));
	}
	
	//회원정보 삭제
	@PostMapping("/delete")
	public ResponseEntity<JSONResult> deleteMember(@RequestBody String id){
		return userService.delete(id) ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("회원정보 삭제 성공")) : ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("해당 정보와 일치하는 회원이 없습니다."));
	}
	
	//회원정보 리스트
	@GetMapping("/list")
	public ResponseEntity<JSONResult> memberList(
			@ModelAttribute PageInfo page
			){

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(userService.userList(page)));
	}

}