package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	
	@GetMapping({"","/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal) {		
					// 시큐리티로 로그인하고 여기로 올껀데.. 세션이 만들어져있을거란 말이지..
					// 그럼 컨트롤러에서 세션을 어떻게 찾아..? 내가 만든 것도 아닌데..?
					// @AuthenticationPrincipal PrincipalDetail principal 이렇게 파라미터를 적어주면
					// 세션에 접근 할 수가 있음!
		
		System.out.println("로그인 사용자 아이디 : " + principal.getUsername());
		
		return "index";
	}
}
