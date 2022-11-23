package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// JSP에서는 DAO 역할
// 자동으로 bean등록이 된다. -> @Repository를 생략 할 수 있다.
public interface UserRepository extends JpaRepository<User, Integer> {
	
	// 얘도 Naming 쿼리임.
	// Select * From user Where username = 1?;
	// findByxxxxxx는 규칙!
	Optional<User> findByUsername(String username);
	
	
	
}
	
	
	
	
	
	
	
	/*
	 * // 전통적인 Session을 이용한 로그인 방법
	 * 
	 * // JPA Naming 쿼리전략 // Select * From user Where username = ?1 And password =
	 * ?2; 이런 쿼리가 동작함. // 각각 ?에 username과 password가 들어감 // User
	 * findByUsernameAndPassword(String username, String password);
	 * 
	 * // OR
	 * 
	 * // @Query(value =
	 * "Select * From user Where username = ?1 And password = ?2;", nativeQuery =
	 * true) // User login(String username, String password);
	 */

