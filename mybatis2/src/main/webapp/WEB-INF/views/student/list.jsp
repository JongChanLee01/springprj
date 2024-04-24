<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <style>
    div.container { width: 800px; margin: 50px auto; }
    thead th { background-color: #eee; }
    table{ border-collapse: collapse; width: 100%; }
    td, th { padding: 4px; border: 1px solid lightgray; }
    td:nth-child(4) { text-align: center; }
    .btn { padding: 0.4em 1em; border: 1px solid gray;
       border-radius: 0.5em; background: linear-gradient(#fff, #ddd);
       text-decoration: none; color: black; display: inline-block; }
  </style>
</head>
<body>
<div class="container">

  <h1>학생목록</h1>

  <a href="create" class="btn">학생등록</a>
  <table class="table table-bordered table-condensed">
    <thead>
      <tr>
        <th>학번</th>
        <th>이름</th>
        <th>학과</th>
        <th>전화번호</th>
        <th>성별</th>
        <th>이메일</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="student" items="${ students }">
        <tr>
          <td>${ student.studentNo }</td>
          <td><a href="edit?id=${ student.id }">${ student.name }</td>
          <td>${ student.departmentName }</td>
          <td>${ student.phone }</td>
          <td>${ student.sex }</td>
          <td>${ student.email }</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

</div>
</body>
</html>