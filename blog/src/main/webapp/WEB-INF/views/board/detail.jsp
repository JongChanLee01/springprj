<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

   <div class="container">
      <button class='btn btn-secondary' onclick="history.back()">돌아가기</button>

      <button class='btn btn-warning' id="btn-update">수정</button>
      <c:if test="${board.user.id==principal.id}">
         <button class='btn btn-danger' id="btn-delete">삭제</button>
      </c:if>

      <div class="m-3">
       글번호 : <span id="id"><i>${board.id}</i></span>
       작성자 : <span><i>${board.user.username}</i></span>
      </div>

       <div class="m-3 form-group">
         <label class="form-label">제목(Title)</label>
         <h2> ${board.title}</h2>
       </div>
       <hr>
       <div class="m-3 form-group">
        <label class="form-label">내용(Content)</label>
        <div>${board.content}</div>
       </div>
       <hr>

<!--  // 댓글 -->
   <div class="card">
        <form>
         <div class="card-body">
           <textarea class="form-control" id="reply-content"></textarea>
         </div>
         <div class="card-footer">
           <button class="btn btn-primary" id="btn-reply-save" type="button">등록</button>
         </div>
       </form>
   </div>
  </div>

  <script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>