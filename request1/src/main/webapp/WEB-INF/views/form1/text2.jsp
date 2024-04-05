<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
  div.container { width: 600px; margin: 20px auto; }
  form div { margin-bottom: 10px; }
  input { width: 200px; padding: 4px; }
  button { padding: 0.4em 3em; margin-top: 10px; }
</style>
</head>
<body>
<div class="container">
    <form method="post" action="/form1/text1">
      <h1>텍스트 입력폼 01</h1>
 
      <div>
        <label>text1:</label> 
        <input type="text" name="param1" value="${ param1 }" />
      </div>
 
      <div>
        <label>text2:</label> 
        <input type="text" name="param2" value="${ param2 }" />
      </div>
     
      <button type="submit">확인</button>
      <button type="reset">취소</button>
    </form>
    <p> <fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss" value = "${ now }" /> </p>
  </div>
</body>
</html>