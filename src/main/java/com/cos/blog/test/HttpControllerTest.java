package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// @Controller 사용자가 요청 -> 응답(HTML 파일)
// @RestController 사용자가 요청 -> 응답(data)
@RestController
public class HttpControllerTest {

	private static final String TAG = "HttpControllerTest";
	
	// lombok을 이용한 Getter Setter 자동 설정 확인
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m1 = new Member(1, "ssar", 1234, "email");
		System.out.println(TAG+"getter : " + m1.getId());
		m1.setId(5000);
		System.out.println(TAG+"getter : " + m1.getId());
		return "lombok test 완료";
	}
	
	// 인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다.
	// http://localhost:8181/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) {	// id=1&username=ssar&password=1234&email=ssar@naver.com // MessageConverter (스프링부트)
		return "get 요청 : " +m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	// http://localhost:8181/http/post (insert)
	@PostMapping("/http/post")	// text/plain, application/json - MIME 타입
	public String postTest(@RequestBody Member m) {	// MessageConverter (스프링부트) - 얘가 파싱해서 자동 매핑해줌
		return "post 요청 : " +m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	// http://localhost:8181/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	// http://localhost:8181/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
}
