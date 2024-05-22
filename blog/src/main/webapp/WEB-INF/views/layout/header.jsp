<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
    <!-- 인증된 사용자만 이 내용을 볼 수 있음 -->
    <p>Welcome, ${pageContext.request.userPrincipal.name}!</p>
    <p>${pageContext.request.userPrincipal}!</p>
    <sec:authentication property="principal" var="principal" />
    <p>${principal}!</p>
</sec:authorize>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     <title>Insert title here</title>
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
     <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
     <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

     <!-- include summernote css/js -->
     <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
     <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
</head>
<body>
     <nav class="navbar navbar-expand-md bg-dark navbar-dark mb-3">
      <!-- Brand -->
        <a class="navbar-brand" href="/">Home</a>
       <!-- Toggler/collapsibe Button -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
           <span class="navbar-toggler-icon"></span>
       </button>

        <!-- https://young0105.tistory.com/213 -->
        <!-- jstl태그 안에는 jsp 주석을 사용하는것이 좋음 -->
        <!-- Navbar links -->
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <c:choose>
                <%-- <c:when test="${empty sessionScope.principal}"> --%>
                <c:when test="${empty principal}">
                    <ul class="navbar-nav">
                        <%--
                        <li class="nav-item"> <a class="nav-link" href="/user/loginForm">로그인</a> </li>
                        <li class="nav-item"> <a class="nav-link" href="/user/joinForm">회원가입</a> </li>
                        --%>
                        <%--
                        <li class="nav-item"> <a class="nav-link" href="/loginForm">로그인</a> </li>
                        <li class="nav-item"> <a class="nav-link" href="/joinForm">회원가입</a> </li>
                        --%>
                        <li class="nav-item"> <a class="nav-link" href="/auth/loginForm">로그인</a> </li>
                        <li class="nav-item"> <a class="nav-link" href="/auth/joinForm">회원가입</a> </li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="navbar-nav">
                        <li class="nav-item"> <a class="nav-link" href="/board/form">글쓰기</a> </li>
                        <li class="nav-item"> <a class="nav-link" href="/user/form">회원정보</a> </li>

                        <%-- <li class="nav-item"> <a class="nav-link" href="/user/logout">로그아웃</a> </li> --%>
                        <li class="nav-item"> <a class="nav-link" href="/logout">로그아웃</a> </li>

                        <%--
                        <li class="nav-item">
                            <a class="nav-link" href="/user/delete/${principal.id}">회원탈퇴</a>
                        </li>
                        --%>
                        <li class="nav-item"><a class="nav-link" href="/user/deleteForm">회원탈퇴</a> </li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
      </nav>