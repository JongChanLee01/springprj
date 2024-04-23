<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <style>
    div.container { width: 900px; margin: 50px auto; }
    thead th { background-color: #eee; }
    table{ border-collapse: collapse; width: 100%; }
    td, th { padding: 4px; border: 1px solid lightgray; }
    td:nth-child(4) { text-align: center; }
  </style>
</head>
<body>
<div class="container">
  <h1>학과 목록</h1>

  <table class="table table-bordered table-condensed">
    <thead>
      <tr>
        <th>ID</th>
        <th>학과명</th>
        <th>줄임명</th>
        <th>전화번호</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="dpmt" items="${ department }">
        <tr>
          <td>${ dpmt.id }</td>
          <td>${ dpmt.name }</td>
          <td>${ dpmt.shortName }</td>
          <td>${ dpmt.phone }</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

</div>
</body>
</html>