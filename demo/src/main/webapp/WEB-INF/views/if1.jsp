<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
table {
	border-collapse: collapse;
}

td {
	padding: 5px;
	border: solid 1px gray;
}
</style>
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="now" class="java.util.Date" />

	<table>
		<tr>
			<td>시각</td>
			<td><fmt:formatDate value="${ now }" pattern="HH:mm:ss" /></td>
		</tr>
		<tr>
			<td>시</td>
			<td><fmt:formatDate value="${ now }" pattern="H" /></td>
		</tr>
		<tr>
			<td>분</td>
			<td><fmt:formatDate value="${ now }" pattern="m" /></td>
		</tr>
		<tr>
			<td>초</td>
			<td><fmt:formatDate value="${ now }" pattern="s" /></td>
		</tr>
	</table>

	<fmt:formatDate var="hour" value="${ now }" pattern="H" />
	<fmt:formatDate var="minute" value="${ now }" pattern="m" />
	<fmt:formatDate var="second" value="${ now }" pattern="s" />

	<c:if test="${ hour < 12 }">
	  Good Morning!
	</c:if>

	<c:if test="${ 12 <= hour && hour < 18 }">
	  Good Afternoon!
	</c:if>

	<c:if test="${ 18 <= hour }">
	  Good Evening!
	</c:if>

	<br /> 지금은 ${ hour }시 ${ minute }분 ${ second } 초 입니다.

	<hr>

	<table>
		<tr>
			<c:forEach var="i" begin="1" end="10">
				<c:if test="${ i % 2 == 0 }">
					<td style="background-color: #ffffcc">${ i }</td>
				</c:if>
				<c:if test="${ i % 2 != 0 }">
					<td style="background-color: #ccffcc">${ i }</td>
				</c:if>
			</c:forEach>
		</tr>
	</table>
	<hr>
	<table>
		<tr>
			<c:forEach var="i" begin="1" end="10">
				<td style='background-color: ${(i%2)==0 ? "#ffffcc":"#ccffcc"}'>${ i }</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>