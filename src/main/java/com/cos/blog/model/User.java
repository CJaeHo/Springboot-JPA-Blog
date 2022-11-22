package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder		// 빌더 패턴!!!
// @DynamicInsert 	// insert시에 null인 필드를 제외시켜준다.
//ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술.
@Entity		// User 클래스가 MySQL에 테이블이 생성이 된다.
public class User {

	@Id		// primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)		// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. ex) 오라클 사용? 시퀀스, MySQL 사용? auto_increment
	private int id;		// 오라클 - 시퀀스, MySQL - auto_increment
	
	@Column(nullable = false, length = 30, unique = true)		// nullable = not null, length = 데이터크기
	private String username;			// 아이디

	@Column(nullable = false, length = 100)	// 123456 -> 해쉬로 변경할꺼임(비밀번호 암호화)
	private String password;			// 비밀번호

	@Column(nullable = false, length = 50)
	private String email;					// 이메일
	
	// @ColumnDefault("'user'")		// "여기 들어가는데".. ""여기안에 ''이거 넣어줘야해. 문자라는 것을 알려주기 위해!
	// DB는 RoleType이라는게 없으니까 @Enumerated(EnumType.STRING)를 넣어준다.
	@Enumerated(EnumType.STRING)
	private RoleType role;			// Enum을 쓰는게 좋다. 왜? 도메인설정을 할 수 있음(즉, 범위가 있음. admin,user,manager 3개로 지정) // admin, user, manager 각각의 권한을 주는 컬럼
	
	@CreationTimestamp		// 시간이 자동으로 입력된다.
	private Timestamp createDate;		// 생성일자
}
