<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
<div class="container">
     <!-- <form> -->
     <form action="#" method="post">
         <div class="form-group">
             <label for="username">Username</label>
              <input type="text" class="form-control" id="username">
          </div>
         <div class="form-group">
           <label for="password">Password</label>
            <input type="password" id="password" class="form-control">
       </div>

       <div class="form-group form-check">
           <label class="form-check-label">
                 <!-- <input class="form-check-input" id="memory" type="checkbox">Remember me -->
                 <input name="remember" class="form-check-input" id="memory" type="checkbox">Remember me
         </label>
       </div>
   </form>
  <button id="btn-login" class="btn btn-primary">로그인</button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>