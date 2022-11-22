package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// JSP에서는 DAO 역할
// 자동으로 bean등록이 된다. -> @Repository를 생략 할 수 있다.
public interface UserRepository extends JpaRepository<User, Integer> {
	
	
}
