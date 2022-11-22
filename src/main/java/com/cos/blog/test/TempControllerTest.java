package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");	// 콘솔에는 뜸. client에서는 404에러 뜸.
		
		// 파일리턴 기본경로 : src/main/resources/static
		// 리턴명 : /home.html
		// 그래야 풀경로가 src/main/resources/static/home.html로 찾아짐
		return "/home.html";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		// prefix : /WEB-INF/views/
		// suffix : .jsp
		// 풀네임 : /WEB-INF/views/test.jsp
		return "test";
	}
}
