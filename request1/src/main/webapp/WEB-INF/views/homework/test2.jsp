<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <form method="get" action="test1">
		<select name="num">
			<option value="one" selected>one</option>
			<option value="two">two</option>
			<option value="three">three</option>
		</select>
		<input type="text" value="${num}">
		<input type="submit" value="Ok">
	</form> --%>
	
	<form method="post" action="/homework/test2">
		<select name="num" >
			<option value="one" ${num=="one"?"selected":""}> one</option>
			<option value="two"${num=="two"?"selected":""}> two</option>
			<option value="three"${num=="three"?"selected":""}> three</option>
			<option value="four"${num=="four"?"selected":""}> four</option>
		</select>
		<input type="text" value="${num}">
		<input type="submit" value="Ok">
	</form>
</body>
</html>