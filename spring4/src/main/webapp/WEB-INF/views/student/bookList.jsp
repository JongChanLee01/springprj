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
  <h1>책 목록</h1>

  <table class="table table-bordered table-condensed">
    <thead>
      <tr>
        <th>아이디</th>
        <th>제 목</th>
        <th>작 가</th>
        <th>카테고리</th>
        <th>가 격</th>
        <th>제작사</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="book" items="${ book }">
        <tr>
          <td>${ book.id }</td>
          <td>${ book.title }</td>
          <td>${ book.author }</td>
          <td>${ book.categoryName }</td>
          <td>${ book.price }</td>
          <td>${ book.publisher }</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

</div>
</body>
</html>