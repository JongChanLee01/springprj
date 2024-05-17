let index={
  init:function(){
//   let _this=this;
//   $('#btn-save').on('click',function(){
//       _this.save(); // 치환을 하는 이유는 그냥 this를 해버리면 일반 함수는 #btn-save를 가르키게 된다.
//   });
   $('#btn-save').on('click',()=>{ // this를 바인딩하기 위해서. 에로우함수의 this는 let index를 가르킴
        this.save();
   });

   $('#btn-login').on('click',()=>{
        this.login();
   });
 },
 login:function(){
      let data={
           username:$("#username").val(),
           password:$("#password").val()
      }
      $.ajax({
           type:"POST",
           url:'/api/user/login',
           data:JSON.stringify(data), // http body 데이터
           contentType:"application/json; charset=utf-8",
           dataType:"json"
      }).done(function(resp){
           if(resp.data==1){
              alert("로그인이 완료되었습니다.");
           }else{
             alert("사용자 정보가 없습니다.");
           }
           console.log(resp);
           location.href="/";
      }).fail(function(error){
           alert(JSON.stringify(error));
      });
 },

 save:function(){
  // alert("user의 save함수 호출됨");
  let data={
     username:$('#username').val(),
     password:$('#password').val(),
     email:$('#email').val()
  }
    console.log(data);

// ajax 호출시 default가 비동기호출
// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자바오브젝트로 변환해준다.

 $.ajax({
     type:"POST",
     url:'/api/user',
     data:JSON.stringify(data), // http body 데이터
     contentType:"application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
     dataType:"json" // 요청을 서버로 해서 응답이 왔을때 기본적으로 모든것이 문자열(모양은 json)
    // => javascript 오브젝트로 변경한다.
  // 기본값은 json 으로 던져준다. text -> string
   }).done(function(resp){
      alert("회원가입이 완료되었습니다.");
      console.log(resp);
      location.href="/";
   }).fail(function(error){
      alert(JSON.stringify(error));
   });
    // ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
  }
}

index.init();