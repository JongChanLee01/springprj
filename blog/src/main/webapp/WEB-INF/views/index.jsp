<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <html>
  <head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
     <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
       <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
 </head>
<body>
  <nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <!-- Brand -->
   <a class="navbar-brand" href="/">Home</a>

   <!-- Toggler/collapsibe Button -->
   <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
      <span class="navbar-toggler-icon"></span>
   </button>

 <!-- Navbar links -->
 <div class="collapse navbar-collapse" id="collapsibleNavbar">
   <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="/user/loginForm">로그인</a>
    </li>
    <li class="nav-item">
         <a class="nav-link" href="/user/joinForm">회원가입</a>
    </li>
  </ul>
  </div>
</nav>

<div class="container">
  <div class="card m-1">
   <img class="card-img-top" src="imgs/img_avatar1.png" alt="Card image">
    <div class="card-body">
   <h4 class="card-title">제목 적는 부분</h4>
     <p class="card-text">내용 적는 부분</p>
      <a href="#" class="btn btn-primary">상세보기</a>
   </div>
</div>

</div>
<div class="jumbotron jumbotron-fluid">
    <div class="container">
       <p>Created by Kim</p>
        <p>🛒 010-222-6666 </p>
       <p>Footer...</p>
    </div>
    </div>
  </body>
</html>