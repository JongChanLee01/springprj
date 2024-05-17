$('#join--submit').on('click', function() {
var data = {
    username : $('#username').val(),
     password : $('#password').val(),
     email : $('#email').val()
};

$.ajax({
      type : 'POST',
      url : '/user/join2',
      data : JSON.stringify(data),
      contentType : 'application/json; charset=utf-8',
      dataType : 'json'
}).done(function(r) {
      if (r.data == "1") {
        console.log(r);
        alert('데이터 전송 OK');
        location.href = '/user/login';
      } else if (r.data == "0") {
          console.log(r);
          alert('아이디가 중복되었습니다.');
      } else {
          console.log(r);
          alert('회원가입 실패');
      }
}).fail(function(r) {
     var message = JSON.parse(r.responseText);
      console.log((message));
       alert('서버 오류');
   });
});