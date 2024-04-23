<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <style>
    div.container { width: 600px; margin: 50px auto; }
    input { padding: 5px; font-size: 10pt; }
    button { margin: 10px ; padding: 0.4em 2em; }
    thead th { background-color: #eee; }
    table{ border-collapse: collapse; width: 100%; }
    td, th { padding: 4px; border: 1px solid lightgray; }
    td:nth-child(4) { text-align: center; }
    tr[data-url]:hover { background-color: #ffb; cursor: pointer; }
  </style>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<div class="container">

  <h1>학생목록</h1>
  <form>
    <label>이름</label>
    <input type="text" name="srchText" value="${ srchText }" placeholder="검색조건" />
    <button type="submit">조회</button>
  </form>

  <table class="table table-bordered table-condensed">
    <thead>
      <tr>
        <th>학번</th>
        <th>이름</th>
        <th>학과</th>
        <th>이메일</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="student" items="${ students }">
        <tr data-url="detail?id=${ student.id }">
          <td>${ student.studentNo }</td>
          <td>${ student.name }</a></td>
          <td>${ student.departmentName }</td>
          <td>${ student.email }</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

</div>

<script>
$("[data-url]").click(function() {
  var url = $(this).attr("data-url");
  location.href = url;
})
</script>

</body>
</html>