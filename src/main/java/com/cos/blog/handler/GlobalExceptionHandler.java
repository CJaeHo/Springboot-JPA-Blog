package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice			// 모든 페이지에서 에러가 발생했을 때 이 페이지로 오게하는 어노테이션
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)		// Exception = 모든 Exception 종류의 부모임.
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
}
