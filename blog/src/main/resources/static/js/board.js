let index={
  init:function(){
    //   let _this=this;
    //   $('#btn-write').on('click',function(){
    //       _this.save(); // 치환을 하는 이유는 그냥 this를 해버리면 일반 함수는 #btn-save를 가르키게 된다.
    //   });
    $('#btn-write').on('click',()=>{ // this를 바인딩하기 위해서. 에로우함수의 this는 let index를 가르킴
         this.save();
    });

    $('#btn-delete').on('click',()=>{
        var delOk=confirm("정말로 삭제하시겠습니까?");
        if(delOk){
            this.deleteById();
        }else{
            return false;
        }
    });

    $('#btn-update').on('click',()=>{
         this.update();
    });
    $('#btn-reply-save').on('click',()=>{
         this.replySave();
    });
  },

  replySave:function(){
      let data={
        boardId:$("#boardId").val(),
        content:$("#reply-content").val(),
      }
     console.log(data);

     $.ajax({
        type:"POST",
        url:`/api/board/${data.boardId}/reply`,
        data:JSON.stringify(data),
        contentType:"application/json; charset=utf-8",
        dataType:"json"
     }).done(function(resp){
        console.log(resp)
        alert("댓글이 등록되었습니다.");
        location.href=`/board/${data.boardId}`;
     }).fail(function(error){
        console.log(error);
        alert(JSON.stringify(error));
     });
  },

  // data가 필요없기때문에 contentType도 삭제한다.
  // 삭제성공하면 해당게시글로 돌아온다.
  replyDelete : function(boardId, replyId){
    $.ajax({
      type: "DELETE",
      url: `/api/board/${boardId}/reply/${replyId}`,
      dataType: "json"
     }).done(function(resp){
    alert("댓글삭제 성공");
    location.href = `/board/${boardId}`;
   }).fail(function(error){
    alert(JSON.stringify(error));
   });
  },

  // 수정하기
  update:function(){
      var id=$('#id').val();
         let data={
         title:$("#title").val(),
         content:$("#content").val()
      }

    $.ajax({
        type:"PUT",
        url:'/api/board/'+id,
        data:JSON.stringify(data),
        contentType:"application/json; charset=utf-8",
        dataType:"json"
    }).done(function(resp){
        alert("수정이 완료되었습니다.");
        location.href="/";
    }).fail(function(error){
        alert(JSON.stringify(error));
    });
  },

  // 삭제하기
  deleteById:function(){
      var id=$('#id').text();

      $.ajax({
        type:"DELETE",
        url:'/api/board/'+id
      }).done(function(resp){
        alert("삭제가 되었습니다.");
        location.href="/";
      }).fail(function(error){
        alert(JSON.stringify(error));
      });
  },

  // 글쓰기
  save:function(){
      let data={
         title:$('#title').val(),
         content:$('#content').val()
      }
      console.log(data);

// ajax 호출시 default가 비동기호출
// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자바오브젝트로 변환해준다.

   $.ajax({
       type:"POST",
       url:'/api/board',
       data:JSON.stringify(data), // http body 데이터
       contentType:"application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
       dataType:"json" // 요청을 서버로 해서 응답이 왔을때 기본적으로 모든것이 문자열(모양은 json)
       // => javascript 오브젝트로 변경한다.
       // 기본값은 json 으로 던져준다. text -> string
   }).done(function(resp){
       if(resp.data==1){
          alert("글쓰기가 완료되었습니다.");
          location.href="/";
       }else{
          alert("세션이 만료되었습니다.");
          location.href="/user/loginForm";
       }
   }).fail(function(error){
       alert(JSON.stringify(error));
   });
   // ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
  }
}

index.init();