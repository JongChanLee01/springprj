<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
table {
	border-collapse: collapse;
}
tr {
	border: solid 1px gray;
}
td {
	padding: 5px;
	border: solid 1px gray;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<c:forEach var="i" begin="2" end="9">
				<td>
					<c:forEach var="j" begin="1" end="9">
						${i} X ${j} = ${i*j}<br>
					</c:forEach>
				</td>
			</c:forEach>
		</tr>
	</table>
	
	<hr>
	
	<table>
		<tr>
			<c:forEach var="i" begin="2" end="5">
				<td>
					<c:forEach var="j" begin="1" end="9">
						${i} X ${j} = ${i*j}<br>
					</c:forEach>
				</td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach var="i" begin="6" end="9">
				<td>
					<c:forEach var="j" begin="1" end="9">
						${i} X ${j} = ${i*j}<br>
					</c:forEach>
				</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>