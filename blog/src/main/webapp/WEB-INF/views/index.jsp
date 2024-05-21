<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="./layout/header.jsp" %>

<style>
.card-body img{ width:100px !important; height:100px;}
.active{ background:red !important; color:white !important;}
</style>

<div class="container">
<!--
    <div class="card m-1">
     <div class="card-body">
      <h4 class="card-title">제목 적는 부분</h4>
      <p class="card-text">내용 적는 부분</p>
       <a href="#" class="btn btn-primary">상세보기</a>
     </div>
   </div>
   <div class="card m-1">
    <div class="card-body">
      <h4 class="card-title">제목 적는 부분</h4>
      <p class="card-text">내용 적는 부분</p>
      <a href="#" class="btn btn-primary">상세보기</a>
    </div>
    </div>
    <div class="card m-1">
     <div class="card-body">
     <h4 class="card-title">제목 적는 부분</h4>
     <p class="card-text">내용 적는 부분</p>
     <a href="#" class="btn btn-primary">상세보기</a>
     </div>
    </div>
-->
<c:forEach var="board" items="${boards.content}">
  <div class="card m-2">
     <div class="card-body">
         <h4 class="card-title">
            ${board.title}
            <span style="font-size:14px; font-weight:normal">[ 댓글 개수 : <c:out value="${fn:length(board.replies)}" />개 ]</span>
         </h4>
         <p class="card-text">${board.content}</p>
         <a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
         <i>조회수 : ${board.count}</i> |
         <fmt:formatDate value="${board.createDate}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedDate" />
         <i style="font-size: 14px"> 작성자 : ${board.user.username} | 작성일 : ${formattedDate}</i>
     </div>
  </div>
</c:forEach>

<!-- 페이지네이션 -->
 <nav aria-label="Page navigation example">
     <ul class="pagination justify-content-center">
      <c:choose>
       <c:when test="${boards.first}">
         <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
       </c:when>
       <c:otherwise>
          <li class="page-item"><a class="page-link" href="?page=${boards.number-1}#pagea">Previous</a></li>
       </c:otherwise>
      </c:choose>

<c:set var="page" value="${(boards.totalElements/3)}" />

  <c:forEach begin="1" end="${page+((1-page%1))%1}" step="1" varStatus="number">
    <li class="page-item">
        <a class="page-link a" href="?page=${number.index-1}#pageNext">${number.index}</a>
     </li>
 </c:forEach>

 <c:choose>
      <c:when test="${boards.last}">
          <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
     </c:when>
     <c:otherwise>
        <li class="page-item"><a class="page-link" href="?page=${boards.number+1}#pagea" id="pageNext">Next</a></li>
     </c:otherwise>
     </c:choose>
   </ul>
 </nav>
</div>

<%@ include file="./layout/footer.jsp" %>

<script>
    var pageItem=document.querySelectorAll('.page-link.a');
    const queryString = window.location.search;
    const params = {};

    queryString.substring(1).split('&').forEach(param => {
      const [key, value] = param.split('=');
      params[key] = value;
    });

    console.log(params); // { page : 0 }

    if(params.page != undefined){
      pageItem[params.page].classList.add("active");
    }else{
      pageItem[0].classList.add("active");
    }
</script>