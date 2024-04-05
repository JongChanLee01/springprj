<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
  div.container { width: 400px; margin: 20px auto; }
  form div { margin-bottom: 20px; }
  label { display: block; }
  input[type=text], input[type=password] { width: 200px; padding: 5px; }
  .btn { border: 1px solid gray; text-decoration: none; padding: 0.4em 1em;
    color: black; background-color: #eee; cursor: pointer; font-size: 10pt; }
  hr { margin-top: 80px; }
  .error { color: red; margin: 5px; }
</style>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	  <form method="post">
	    <h1>로그인</h1>
	    <div>
	      <label>아이디</label>
	      <input type="text" class="text" name="userid" value="${ userid }" />
	    </div>
	    <div>
	      <label>비밀번호</label>
	      <input type="password" class="text" name="password" />
	    </div>
	    <div>
	      <label>
	      	<input type="checkbox" name="autologin" ${ autologin ? "checked" : "" } /> 자동 로그인
	      </label>
	    </div>
	    <button type="submit" class="btn">로그인</button>
	    <a href="/form5/register2" class="btn">회원가입</a>
	    </form>
	    <div class="error">${ errorMsg }</div>
	 
	    <hr />
	    현재시각: <fmt:formatDate pattern="HH:mm:ss" value="${ now }" /> <br />
	    userid: ${ userid } <br />
	    password: ${ password } <br />
	    autologin: ${ autologin } <br />
	</div>
</body>
</html>