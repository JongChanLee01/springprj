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
        <th>학번</th>
        <th>이름</th>
        <th>강좌명</th>
        <th>년도</th>
        <th>학기</th>
        <th>학점</th>
        <th>재수강</th>
        <th>철회</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="sugang" items="${ sugangs }">
        <tr>
          <td>${ sugang.id }</td>
          <td>${ sugang.student.studentNo }</td>
          <td>${ sugang.student.name }</td>
          <td>${ sugang.lecture.title }</td>
          <td>${ sugang.lecture.year }</td>
          <td>${ sugang.lecture.semester }</td>
          <td>${ sugang.grade }</td>
          <td>${ sugang.repeated }</td>
          <td>${ sugang.cancel }</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>

