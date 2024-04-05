<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/homework/increase">
		<input type="text" placeholder="0" value="${count}" />
		<input type="submit" value="++" />
	</form>
	<h2>증가값 : ${count}</h2>
</body>
</html>