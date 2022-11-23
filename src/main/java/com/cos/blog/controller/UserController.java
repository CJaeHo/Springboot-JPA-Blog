package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용하겠음.
	// 그냥 주소가 / 이면 index.jsp 허용하겠음.
	// static 이하에 있는 파일들 /js/**, /css/**, /image/** 허용하겠음.
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
}
