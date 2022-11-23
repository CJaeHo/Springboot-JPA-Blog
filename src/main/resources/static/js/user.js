let index = {
	init: function () {
		$('#btn-save').on('click', () => {	// function(){}으로 사용하지않고 ()=>{}로 사용하는 이유는 this를 바인딩하기 위함! 
			this.save();
		});



		// 전통적인 Session을 이용한 로그인 방법
		// $('#btn-login').on('click', () => {	// function(){}으로 사용하지않고 ()=>{}로 사용하는 이유는 this를 바인딩하기 위함! 
		// 	this.login();
		// });
	}
	,
	save: function () {
//		alert('user의 save함수 호출')
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		// ajax 호출 시 default가 비동기 호출
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!
		$.ajax({
			// 회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),		// http body 데이터
			contentType: "application/json; charset=utf-8",		// body 데이터가 어떤 타입인지(MIME)
			dataType: "json"		// 요청을 서버로해서 응답이 왔을 때 모든 것이 문자열이면 생긴게 json이라면 javascript 오브젝트로 변경!
		}).done(function(resp) {
			// 성공 시 실행
			alert("회원가입이 완료되었습니다.");
			// alert(resp);
			// console.log(resp)
			location.href="/";
		}).fail(function(error) {
			// 실패 시 실행
			alert(JSON.stringify(error));
		});		
	}


	// 전통적인 Session을 이용한 로그인 방법
	// ,
	// login: function () {
	// 	//	alert('user의 save함수 호출')
	// 	let data = {
	// 		username: $("#username").val(),
	// 		password: $("#password").val()
	// 	}

	// 	$.ajax({
	// 		// 로그인 수행 요청
	// 		type: "POST",
	// 		url: "/api/user/login",
	// 		data: JSON.stringify(data),		// http body 데이터
	// 		contentType: "application/json; charset=utf-8",		// body 데이터가 어떤 타입인지(MIME)
	// 		dataType: "json"		// 요청을 서버로해서 응답이 왔을 때 모든 것이 문자열이면 생긴게 json이라면 javascript 오브젝트로 변경!
	// 	}).done(function(resp) {
	// 		// 성공 시 실행
	// 		alert("로그인이 완료되었습니다.");
	// 		location.href="/";
	// 	}).fail(function(error) {
	// 		// 실패 시 실행
	// 		alert(JSON.stringify(error));
	// 	});		
	// }
}

index.init();