<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
   <form class="container">
     <input type="hidden" id="id" value="${board.id}" />
     <div class="mb-3">
        <label class="form-label">제목(Title)</label>
       <input type="text" class="form-control" name="title" id="title" value="${board.title}">
    </div>
   <div class="mb-3">
      <label class="form-label">내용(Content)</label>
        <textarea class="form-control summernote" rows="3" name="content" id="content">
           ${board.content}
         </textarea>
     </div>
     </form>
   <button type="submit" class="btn btn-primary" id="btn-update">수정완료</button>
  </div>


 <script>
  $('.summernote').summernote({
    placeholder: 'Hello summernote 5',
    tabsize: 2,
    height: 300
  });
 </script>
 <script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>