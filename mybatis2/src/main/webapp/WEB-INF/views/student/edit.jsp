<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <style>
    div.container { width: 600px; margin: 50px auto; font-size: 10pt; }
    form { width: 600px; margin: auto;
      box-shadow: 0 0 4px lightgray, 2px 2px 4px gray; overflow: auto; }
    div.title { font-size: 20pt; padding: 10px; background-color: #eee; }
    table { margin: 20px; }
    td { min-width: 100; padding: 5px; }
    td:nth-child(1) { text-align: right; }
    input, select { padding: 5px; }
    .btn { padding: 0.4em 1em; border: 1px solid gray;
       border-radius: 0.5em; background: linear-gradient(#fff, #ddd);
       text-decoration: none; color: black; display: inline-block; }
    button { margin: 5px 0 20px 20px; }
  </style>
</head>
<body>
<div class="container">
  <form:form method="post" modelAttribute="student">
    <div class="title">학생 ${ student.id > 0 ? "수정" : "등록" }</div>
    <table>
      <tr>
        <td>학번:</td>
        <td><form:input path="studentNo" /></td>
      </tr>
      <tr>
        <td>이름:</td>
        <td><form:input path="name" /></td>
      </tr>
      <tr>
        <td>학과</td>
        <td><form:select path="departmentId"
            itemLabel="departmentName" itemValue="id" items="${ departments }" />
      </tr>
      <tr>
        <td>전화:</td>
        <td><form:input path="phone" /></td>
      </tr>
      <tr>
        <td>성별:</td>
        <td><form:input path="sex" /></td>
      </tr>
      <tr>
        <td>이메일:</td>
        <td><form:input path="email" /></td>
      </tr>
    </table>
    <hr />
    <div>
      <button type="submit" class="btn">저장</button>
      <a href="delete?id=${student.id}" class="btn"
        onclick="return confirm('삭제하시겠습니까?')">삭제</a>
      <a href="list" class="btn">목록으로</a>
    </div>
  </form:form>
</div>
</body>
</html>