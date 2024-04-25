<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="/common.js"></script>
  <link rel="stylesheet" type="text/css" href="/common.css" />
  <style>
    div.container { width: 900px; margin: 0 auto; }
    span { display: inline-block; margin: 20px 30px 4px 0; padding: 4px; }
    span:nth-child(1) { font-size: 16pt; font-weight: bold; }
    table { border-collapse: collapse; width: 100%; }
    td, th { border: 1px solid gray; padding: 4px; }
    thead { background-color: #ddd; }
  </style>
</head>
<body>
<div class="container">
  <h1>교수별 담당강좌</h1>
  <c:forEach var="professor" items="${ professors }">
    <div>
      <span>${ professor.name } 교수 ${ professor.id }</span>
      <span>전화번호: ${ professor.phone }</span>
      <span>이메일: ${ professor.email }</span>
      <span>사무실: ${ professor.office}</span>
    </div>
    <table>
      <thead>
        <tr><th>id</th><th>강좌명</th><th>년도</th><th>학기</th><th>강의실</th></tr>
      </thead>
      <tbody>
        <c:forEach var="lecture" items="${ professor.lectures }">
          <tr>
            <td>${ lecture.id }</td>
            <td>${ lecture.title }</td>
            <td>${ lecture.year }</td>
            <td>${ lecture.semester }</td>
            <td>${ lecture.room }</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </c:forEach>
</div>
</body>
</html>
