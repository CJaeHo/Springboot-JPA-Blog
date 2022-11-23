<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">

	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username</label>
			<input type="text" class="form-control" placeholder="Enter username" id="username" name="username">
		</div>
		
		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
		</div>
		
		<button  id="btn-login" class="btn btn-primary">로그인</button>
	</form>


<!-- 전통적인 Session을 이용한 로그인 방법.. user.js를 이용함 -->
<!-- 	<button  id="btn-login" class="btn btn-primary">로그인</button> -->

</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>	

