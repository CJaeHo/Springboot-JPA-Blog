package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController		// html파일이 아니라 data를 리턴해줌
public class DummyControllerTest {

	@Autowired		// 의존성 주입(DI)
	private UserRepository userRepository;
	
	// http://localhost:8000/blog/dummy/user/?
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return "삭제에 실패했습니다. 해당 id는 DB에 없습니다.";
		}
		return "삭제되었습니다. id : " + id;
	}
	
	
	
	
	// save함수는 id를 전달하지 않으면 insert를 해줌
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해줌
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해줌
	
	// email, password 변경하기
	@Transactional			// 더티 체킹하는 어노테이션 -> 함수 종료시 Commit됨. 그러면 영속화된 데이터와 비교를함. 이걸 더티체킹이라고 함.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
										// json 데이터를 요청 -> Java Object(MessageConverter의 Jackson 라이브러리가 실행됨)
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패했습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		// userRepository.save(user);
		return user;
	}
	
	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	// 한페이지당 2건에 데이터를 리턴받아 볼 예정!
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable) {
		// size = 한페이지에 데이터 갯수, sort = 정렬하는 기준,  direction = sort를 어떻게 정렬할껀데?
		
		Page<User> pagingUser = userRepository.findAll(pageable);	// getContent()를 붙이면 부가적인 내용이 안나옴!
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	
	// {id} 주소로 파라미터를 전달 받을 수 있다.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// 여기서 문제가 발생함. findById 타입은 int 타입이 아니라 Optional 타입임.
		// 빨간줄을 풀어보면,,
		// user/4를 찾으면 내가 데이터베이스에서 못찾아오게 될텐데 user가 null이 될 거잖아?
		// 그럼 return null이 이런이 되는데 그럼 프로그램에 문자가 있을 것 같은데?
		// 그러니까 Optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 리턴해!!
		// 라는 의미임.
		// Optional은 많은 함수가 있음. 
		// get() = null 나올리가 없으니까 그냥 가져갈게. 
		/* orElseGet(new Supplier<User>(){}) = 빈 객체생성해서 null만 아니게끔 함.
					User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
						@Override
						public User get() {
							// TODO Auto-generated method stub
							return new User();
						}
			 데이터가 있는 id면 .orElseGet()이 실행이 안되고 없는 id라면 실행이되면서 
			 새로운 User인 빈객체가 생성되면서 null이 안들어가게 됨.*/
		// orElseThrow(nuw Supplier<>(){});
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>(){
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환 ( 웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		// 헤더에 application./json으로 표시됨.
		return user;
	}
	
	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) {	// 데이터 형태 : key = value
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
