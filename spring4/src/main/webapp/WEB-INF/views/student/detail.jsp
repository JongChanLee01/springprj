<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <style>
    div.container { width: 600px; margin: 50px auto; }
    body { font-family: 굴림체; }
    table { width: 500px;  border-collapse: collapse; }
    table td:nth-child(1) { background-color: #eee; }
    td { padding: 5px; border: 1px solid lightgray; }
  </style>
</head>
<body>

<div class="container">

<h1>학생 조회</h1>
<%--
<c:if test="${empty student}">
   <tr>
      <td>없는 사용자 입니다.</td>
    </tr>
</c:if>
--%>

<table>

    <c:choose>
      <c:when test="${empty student}">
       <tr>
            <td>없는 사용자 입니다.</td>
          </tr>
      </c:when>
      <c:otherwise>
      <tr>
          <td>ID</td>
          <td>${ student.id }</td>
        </tr>
        <tr>
          <td>학번</td>
          <td>${ student.studentNo }</td>
        </tr>  <tr>
          <td>이름</td>
          <td>${ student.name }</td>
        </tr>
        <tr>
          <td>성별</td>
          <td>${ student.sex }</td>
        </tr>
        <tr>
          <td>학과명</td>
          <td>${ student.departmentName }</td>
        </tr>
        <tr>
          <td>이메일</td>
          <td>${ student.email }</td>
        </tr>
      </c:otherwise>
    </c:choose>

</table>
<a href="javascript:window.history.back()">돌아가기</a>

</div>
</body>
</html>