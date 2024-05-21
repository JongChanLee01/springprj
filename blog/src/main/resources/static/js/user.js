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

       $('#btn-check').on('click',()=>{
            event.preventDefault();
            this.check();
       });

       $('#btn-update').on('click',()=>{
            this.update();
       });

       $('#btn-delete').on('click',()=>{
            var del=confirm("정말로 탈퇴하시겠습니까?");
            if(del){
                this.delete();
            }else{
                return false;
            }
       });

       // Remember Me
       $('#memory').on('click', () => {
          console.log($('#memory').prop("checked"))
          if($('#memory').prop("checked")==true){
            this.memory();
          }else{
            localStorage.clear();
          }
       });
       // 페이지 로딩시 아이디 비번 자동입력
       $('#username').val(localStorage.getItem("username"));
       $('#password').val(localStorage.getItem("password"));
  },

  check:function(){
    let  username=$("#username").val();
     $.ajax({
         type:"GET",
         url:'/api/user/'+username,
         contentType:"application/json; charset=utf-8"
    }).done(function(resp){
    console.log(resp);
      if(resp=="OK"){
         alert("사용할수 있는 아이디입니다.");
       }else{
         alert("아이디가 중복되었습니다.");
        $("#username").val("");
         $("#username").focus();
       }
    }).fail(function(error){
     console.log(error);
         alert(JSON.stringify(error));
    });
  },

  memory:function(){
      var username= $('#username').val();
      var password= $('#password').val();
      localStorage.setItem('username',username);
      localStorage.setItem('password',password);

      $('#username').val(localStorage.getItem("username"));
      $('#password').val(localStorage.getItem("password"));
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
             alert("아이디와 비밀번호를 확인해주세요.");
             $("#username").focus();
             return false; // 다음 단계로 진행 안함.
           }
           console.log(resp);
           location.href="/";
      }).fail(function(error){
           alert(JSON.stringify(error));
      });
 },

 update:function(){
     let data={
       id:$('#id').val(),
       password:$("#password").val(),
       email:$('#email').val()
     }
   $.ajax({
      type:"PUT",
      url:'/user',
      data:JSON.stringify(data),
      contentType:"application/json; charset=utf-8",
       dataType:"json"
   }).done(function(resp){
       alert("회원수정이 완료되었습니다.");
       console.log(resp);
       location.href="/";
   }).fail(function(error){
        alert(JSON.stringify(error));
   });
 },

 // 회원탈퇴
 delete:function(){
 var id=$('#id').val();
 let data={
     username:$("#username").val(),
     password:$("#password").val()
 }

 $.ajax({
 type:"DELETE",
 url:'/user/delete/'+id,
         data:JSON.stringify(data),
         contentType:"application/json; charset=utf-8",
         dataType:"json"
     }).done(function(resp){
       console.log(resp);
       if(resp.data==1){
          alert("회원탈퇴가 완료되었습니다.");
          console.log(resp);
          location.href="/user/logout";
       }else{
          alert("비밀번호가 잘못되었습니다.");
          return false;
       }
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
      if(resp.data==1){
          alert("회원가입이 완료되었습니다.");
          console.log(resp);
          location.href="/";
      }else{
           alert("아이디가 중복되었습니다.");
           return false;
      }
   }).fail(function(error){
      alert(JSON.stringify(error));
   });
    // ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
  }
}

index.init();