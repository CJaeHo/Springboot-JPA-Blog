package com.cos.blog.config.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service			// 이때 Bean 등록됨.
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	// 스프링이 로그인 요청을 가로챌 때, username과 password 변수 2개를 가로채는데,
	// password 부분 처리는 알아서하기때문에, username이 DB에 있는지만 확인해주면 됨.
	// 그걸 loadUserByUsername() 여기서 함.
	// 얘를 오버라이딩해서 안만들면 시큐리티에서 기본적인 아이디와 비밀번호가 제공됨. user, console창에 나오는 password.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// 이렇게 User로 받으려면 findByUsername() 얘가 Optional<> 타입이기 때문에
		// .orElseThrow()를 해줘야함.
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
				});
		
		// 리턴을 해주는데.. UserDetails 타입이라 User를 return 시킬 수는 없으니
		// new PrincipalDetail()을 리턴해줘야함. 얘를 리턴하려면 이놈의 생성자가 있어야함. 없으면 null값이 됨
		return new PrincipalDetail(principal);			// 이때 일어나는게 시큐리티의 세션에 유저 정보가 저장이 됨! 
}
	
	
}
