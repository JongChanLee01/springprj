<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<c:forEach var="i" begin="8" end="30" step="2">
	  <div style='font-size: ${ i }pt;'>
	    ¾È³ç JSP! ${ i }pt
	  </div>
	</c:forEach>
</body>
</html>