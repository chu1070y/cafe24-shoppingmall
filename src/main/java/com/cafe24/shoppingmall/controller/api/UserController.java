package com.cafe24.shoppingmall.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.service.UserService;
import com.cafe24.shoppingmall.vo.UserVO;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public String joinPage() {
		return "회원가입 페이지";
	}
	
	@GetMapping(value = "/checkId")
	public JSONResult checkId(@RequestParam("id") String id) {
		System.out.println("id"+ id);
		return userService.checkId(id)==true? JSONResult.fail("중복"):JSONResult.success("사용가능");
	}
	
//	@ApiOperation(value = "스웩")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name="id", value="아이디", required = true, dataType = "string"),
//		@ApiImplicitParam(name="pw", value="비밀번호", required = true, dataType = "string")
//		@ApiImplicitParam(name="name", value="이름", required = true, dataType = "string"),
//		@ApiImplicitParam(name="email", value="이메일주소", required = true, dataType = "string"),
//		@ApiImplicitParam(name="tel_phone", value="휴대폰번호", required = true, dataType = "string")
//	})
	@PostMapping(value = "/registerMember")
	public JSONResult registerMember(
			@ModelAttribute @Valid UserVO vo,
			BindingResult result,
			Model model) {

		// 아이디 중복 한번 더 체크
		if(userService.checkId(vo.getId())) {
			return JSONResult.fail("중복 아이디");
		}
		
		// 유효성 검사 실패시
		if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for(ObjectError error : list) {
				System.out.println(error);
			}
			
			return JSONResult.fail("입력값 오류");
		}
		
		model.addAllAttributes(result.getModel());
		
		userService.registerMember(vo);
		return JSONResult.success("회원 등록 성공");
	}

}