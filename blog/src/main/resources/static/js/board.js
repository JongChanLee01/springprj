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
       this.deleteById();
    });
  },

  // 삭제하기
  deleteById:function(){
      var id=$('#id').text();
      var delOk=confirm("정말로 삭제하시겠습니까?");
      $.ajax({
        type:"DELETE",
        url:'/api/board/'+id
      }).done(function(resp){
         if(delOk){
            alert("삭제가 되었습니다.");
            location.href="/";
            return true;
         }else{
            return false;
         }
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
       alert("글쓰기가 완료되었습니다.");
       console.log(resp);
       location.href="/";
   }).fail(function(error){
       alert(JSON.stringify(error));
   });
   // ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
  }
}

index.init();