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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.AdminService;
import com.cafe24.shoppingmall.vo.AdminVO;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	// 관리자 등록 전 아이디 중복여부 확인
	@GetMapping(value = "/checkId")
	public ResponseEntity<JSONResult> checkId(
			@RequestParam("id") String id) {

        if(!Pattern.matches("^[a-zA-Z0-9]{4,18}$", id)){
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("아이디 입력형식 오류"));
        }
		
		return adminService.checkId(id)? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("중복")) :
					ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("사용가능"));
	}
	
	// 관리자 등록
	@PostMapping("/add")
	public ResponseEntity<JSONResult> adminAdd(@RequestBody @Valid AdminVO vo, BindingResult result) {
		
		// 아이디 중복 한번 더 체크
		if(adminService.checkId(vo.getId())) {
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
		
		adminService.add(vo);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 등록 성공"));
	}
	
	//관리자 로그인
	@PostMapping("/login")
	public ResponseEntity<JSONResult> login(@RequestBody AdminVO vo) {
        AdminVO adminVO = adminService.login(vo);
        
		return adminVO != null ? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success(adminVO)) : 
					ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("해당 정보와 일치하는 값이 없습니다."));
	}
	
	//관리자 수정
	@PostMapping("/update")
	public ResponseEntity<JSONResult> update(@RequestBody @Valid AdminVO vo, BindingResult result) {
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
		
		return adminService.update(vo) ? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 정보 수정 성공")) : 
					ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("관리자 정보 수정 실패"));
	}
	
	//관리자 삭제
	@PostMapping("/delete")
	public ResponseEntity<JSONResult> delete(@RequestBody String id) {
		
		return adminService.delete(id) ? 
				ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 정보 삭제 성공")) : 
					ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail("관리자 정보 삭제 실패"));
	}

	

}
