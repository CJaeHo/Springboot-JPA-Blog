package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


// @Getter @Setter
// @RequiredArgsConstructor		// final 넣은애들 생성자 만들어준대
@Data		// Data = Getter + Setter
@AllArgsConstructor		// 생성자 생성 어노테이션
@NoArgsConstructor		// Bean 생성 해주는 놈
public class Member {
	// 불변성유지하려고 final을 넣는 것.
	// 데이터 값을 변경할꺼면 쓰면 안됨. ㅇㅋ?
    // private final int id;
	private int id;
	private String username;
	private int password;
	private String email;
	
	// @Builder 패턴의 장점은 순서 상관없음
}
