<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
</head>
<body>
	<h1>안녕 JSP!1</h1>
	<c:set var="msg" value="안녕 JSP!2" />
	<h1>${ msg }</h1>
</body>
</html>