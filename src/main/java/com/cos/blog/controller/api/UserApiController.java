package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {	// username, password, email
		System.out.println("UserApiController : save 호출됨");
		// 실제로 DB에 insert를 하고 아래에서 return이 되면 됨.
		
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);		// 자바오브젝트를 JSON으로 변환해서 리턴(Jackson라이브러리)
	}
	
	// loginForm.jsp 가보면 폼태그에 경로로 /auth/loginProc라고 되어있는데
	// 그러면 Controller에 해당 경로로 맵핑을 해줘야하는데 만들지 않을 거임.
	// 이유는 Controller에 오기전에 시큐리티가 가로채갈 것이기 때문임!
	
	
	/*
	 * // 전통적인 세션을 이용한 로그인방법
	 * 
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user, HttpSession session) {
	 * System.out.println("serApiController : login 호출됨");
	 * 
	 * User principal = userService.로그인(user); // principal -> 접근주체라는 의미
	 * 
	 * // 세션 만들기 if(principal != null) { session.setAttribute("principal",
	 * principal); }
	 * 
	 * return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); }
	 */
}
