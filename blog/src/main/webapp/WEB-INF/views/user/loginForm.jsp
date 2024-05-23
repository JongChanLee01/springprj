<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
<div class="container">
     <!-- <form> -->
     <!-- <form action="#" method="post"> -->
     <form action="/auth/loginProc" method="post">
         <div class="form-group">
             <label for="username">Username</label>
             <input type="text" class="form-control" id="username" name="username">
         </div>
         <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" class="form-control" name="password">
         </div>

       <div class="form-group form-check">
           <label class="form-check-label">
                 <!-- <input class="form-check-input" id="memory" type="checkbox">Remember me -->
                 <input name="remember" class="form-check-input" id="memory" type="checkbox">Remember me
         </label>
       </div>
       <button id="btn-login" class="btn btn-primary">로그인</button>
   </form>
  <!-- <button id="btn-login" class="btn btn-primary">로그인</button> -->
  <h3 class="text-red" id="h3" style="color:red">${error}</h3>
</div>
<script src="/js/user.js"></script>
<script>
    if($("#h3").html() !=""){ alert($("#h3").text()); }
</script>
<%@ include file="../layout/footer.jsp" %>