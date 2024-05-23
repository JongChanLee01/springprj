    <div class="jumbotron jumbotron-fluid">
      <div class="container">
         <p>Created by Lee</p>
         <p>Tel) 010-333-7777 </p>
         <p>Footer...</p>
      </div>
    </div>
    </body>
</html>

<sec:authorize access="isAuthenticated()">
    <!-- 인증된 사용자만 이 내용을 볼 수 있음 -->
   <p>Welcome, ${pageContext.request.userPrincipal.name}!</p>
   <p>${pageContext.request.userPrincipal}!</p>
    <sec:authentication property="principal" var="principal" />
     <p>${principal}!</p>
</sec:authorize>