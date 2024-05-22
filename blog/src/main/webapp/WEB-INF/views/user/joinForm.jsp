<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
<div class="container">
     <!-- <form> -->
     <form action="/auth/joinProc" method="post">
         <div class="form-group">
            <label for="username">유저네임</label>
          <!-- <input type="text" id="username" class="form-control"> -->
          <input type="text" id="username" class="form-control" name="username">
          <button id="btn-check" class="btn btn-success">아이디중복검사</button>
       </div>
       <div class="form-group">
          <label for="password">패스워드</label>
        <input type="password" id="password" class="form-control">
       </div>
     <div class="form-group">
        <label for="email">이메일</label>
         <input type="email" id="email" class="form-control">
     </div>
    </form>
    <button id="btn-save" class="btn btn-primary">회원가입</button>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>