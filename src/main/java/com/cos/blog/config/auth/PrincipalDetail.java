package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고, 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 함. PrincipalDetail 얘가 저장됨. User가 포함되어있음.
public class PrincipalDetail implements UserDetails {
	
	private User user;		// 이렇게 품고있는 걸 콤포지션이라고 함. extends User 처럼하는건 상속이고

	// 생성자
	public PrincipalDetail(User user) {
		this.user = user;
	}
	
	@Override
	public String getPassword() {	
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 리턴함. (true : 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
		// false는 해당유저가 계정만료가 됨.
	}

	// 계정이 잠겨있지 않았는지 리턴함. (true : 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되지 않았는지 리턴함. (true : 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화(사용가능)인지 리턴함. (true : 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

	// 계정이 갖고있는 권한 목록을 리턴함. (권한이 여러개 있을 수 있어서 루프를 돌려야하는데 우리는 한개만 있으니)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();	// ArrayList는 Collection<E> 타입이기에 여기서 사용함.
		
		/*
		 * // 이방식은 자바에서는 함수로 넣을 수 없어서 오브젝트로 감싸서 함수를 넣은거임. collectors.add(new
		 * GrantedAuthority() { // GrantedAuthority() 얘가 인터페이스라 익명클래스가 만들어지고 거기에 추상메서드가
		 * 오버라이딩이 되면서 리턴
		 * 
		 * @Override public String getAuthority() { return "ROLE_" + user.getRole(); //
		 * 스프링에서 ROLE을 받을 때 앞에 ROLE_ 는 규칙임! // ROLE_USER 이런식으로 리턴됨 } });
		 */
		
		// 위 방식과 같은 결과임. 이게 람다식 표현법.
		// 이게 가능한 이유!!
		// 1. add() 함수에 들어올 파라미터는 new GrantedAuthority() 이놈 하나라서 알고있고
		// 2. new GrantedAuthority() 안에 함수는 getAuthority() 하나 뿐이니 이것도 알고있으니
		// 지워보면 남는건 return "ROLE_" + user.getRole(); 이놈 뿐이라 람다식으로 적용시킬 수 있는 것!!
		collectors.add(()->{return "ROLE_" + user.getRole();});
		
		return collectors;
	}
	
}
