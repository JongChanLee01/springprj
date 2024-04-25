<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="/common.css" />
  <style>
    div.container { width: 900px; margin: 0 auto; }
    table { border-collapse: collapse; width: 100%; }
    td, th { border: 1px solid gray; padding: 4px; }
    thead { background-color: #ddd; }
  </style>
</head>
<body>
<div class="container">
  <h1>수강목록</h1>
  <table class="list">
    <thead>
      <tr>
        <th>id</th>
        <th>강좌명</th>
        <th>년도</th>
        <th>학기</th>
        <th>강의실</th>
        <th>교수명</th>
        <th>교수전화</th>
        <th>교수이메일</th>
        <th>교수사무실</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="lecture" items="${ lectures }">
        <tr>
          <td>${ lecture.id }</td>
          <td>${ lecture.title }</td>
          <td>${ lecture.year }</td>
          <td>${ lecture.semester }</td>
          <td>${ lecture.room }</td>
          <td>${ lecture.professor.name }</td>
          <td>${ lecture.professor.phone }</td>
          <td>${ lecture.professor.email }</td>
          <td>${ lecture.professor.office }</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
