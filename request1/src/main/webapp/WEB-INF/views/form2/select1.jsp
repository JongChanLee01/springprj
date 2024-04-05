<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
  div.container { width: 400px; margin: 20px auto; }
  form div { margin-bottom: 10px; }
  label { display: inline-block; width: 70px; text-align: right; }
  input { width: 150px; padding: 4px; }
  select { width: 50px; padding: 4px; }
  button { padding: 0.4em 2em; margin-top: 10px; }
</style>
<title>Insert title here</title>
</head>
<body>
<div class="container">
    <form method="post">
      <h1>select 01</h1>
 
      <div>
        <label>number1:</label> 
        <input type="text" name="number1" value="${ number1 }" />
      </div>
     
      <div>
          <label>operator:</label>
          <select name="cmd">
              <option value="+" ${ cmd == "+" ? "selected" : "" }>+</option>
              <option value="-" ${ cmd == "-" ? "selected" : "" }>-</option>
              <option value="*" ${ cmd == "*" ? "selected" : "" }>*</option>
              <option value="/" ${ cmd == "/" ? "selected" : "" }>/</option>
          </select>
      </div>     
 
      <div>
        <label>number2:</label> 
        <input type="text" name="number2" value="${ number2 }" />
      </div>

      <div>
        <button type="submit">확인</button>
      </div>
    </form>
   
    <p>
        결과: ${ result } 
    <p>
  </div>
</body>
</html>