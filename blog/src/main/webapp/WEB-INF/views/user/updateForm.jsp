<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
<div class="container mt-2">
<form>
    <div class="form-group">
        <label for="id">ID</label>
        <input type="text" class="form-control" id="id" readonly value="${principal.id}">
    </div>
    <div class="form-group">
        <label for="username">Username</label>
        <input type="text" id="username" class="form-control" readonly value="${principal.username}" placeholder="${principal.username}" >
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" class="form-control" value="${principal.password}">
    </div>
    <div class="form-group">
        <label for="email">이메일(e-mail)</label>
        <input type="email" id="email" class="form-control" value="${principal.email}">
    </div>
</form>
<button id="btn-update" class="btn btn-primary">회원수정완료</button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>