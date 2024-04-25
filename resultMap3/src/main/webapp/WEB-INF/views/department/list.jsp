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
  <h1>학과별 소속 학생</h1>
  <c:forEach var="department" items="${ departments }">
    <div>
      <span>부서명 : ${ department.name } </span>
      <span>부서아이디 : ${ department.id } </span>
      <span>이메일: ${ department.phone }</span>
      <span>shortName: ${ department.shortName}</span>
    </div>
    <table>
      <thead>
        <tr>
        <th>순번</th>
        <th>학번</th>
        <th>학생명</th>
        <th>전화번호</th>
        <th>이메일</th>
        <th>성별</th>
        <tr>
      </thead>
      <tbody>
        <c:forEach var="student" items="${ department.students }" varStatus="status">
          <tr>
            <td>${ status.index+1}</td>
            <td>${ student.studentNo}</td>
            <td>${ student.name } </td>
            <td>${ student.phone }</td>
            <td>${ student.email }</td>
            <td>${ student.sex}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </c:forEach>
</div>
</body>
</html>
