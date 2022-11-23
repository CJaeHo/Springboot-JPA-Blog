package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

// 이 3개는 시큐리티 셋트임
@Configuration					// Bean 등록 : 스프링 컨테이너(IoC)에서 객체를 관리할 수 있게 하는 것을 말함.
@EnableWebSecurity			// 시큐리티 필터가 등록이 됨.
@EnableGlobalMethodSecurity(prePostEnabled = true)		// 특정 주소로 접근을하면 권한 및 인증을 미리 체크하겠다는 의미임.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired		// DI하는거임
		private PrincipalDetailService principalDetailService; 
	
		@Bean		// 선언시 IoC가 됨.
		public BCryptPasswordEncoder encodePWD() {
			// new BCryptPasswordEncoder() 얘를 이제 스프링이 관리하게됨
			return new BCryptPasswordEncoder();
		}
		
		// 시큐리티가 대신 로그인을 해줄 때 password를 가로채는데
		// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야하기에
		// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교 할 수가 있음!!
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			// 로그인을 진행하는 놈 : principalDetailService
			// auth.userDetailsService(principalDetailService)가 인코더 할 놈이 encodePWD()이다.
			// 그리고 여기서 DB에 있는 해쉬랑 비교를 함!
			auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
		}
		
	
		// 필터.. 커스터마이징하는거임.
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.csrf().disable()								// csrf 토큰 비활성화 (테스트시 걸어두는게 좋음)
				.authorizeRequests()						// request가 들어올 때
					.antMatchers("/"
											 , "/auth/**"
											 , "/js/**"
											 , "/css/**"
											 , "/image/**")		// 해당 경로들로 들어오면
					.permitAll()									// 전부 허용할꺼야
					.anyRequest()								// 다른 request가 들어올 때는
					.authenticated()							// 인증이되어야해
				.and()
					.formLogin()
					.loginPage("/auth/loginForm")		// 인증이 필요한 상황이 발생하면 /auth/loginForm으로 이동하게만듬.
					.loginProcessingUrl("/auth/loginProc")			// 스프링 시큐리티가 해당 주소로 로그인을 가로채서 대신 로그인을 해줌. 그래서 Controller에 만들지않음..
					.defaultSuccessUrl("/");					// 그리고 로그인하면 / 경로로 이동해시켜줌
		}
}
