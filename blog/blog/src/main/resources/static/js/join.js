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
      if(r.data=="1"){
        alert("데이터 전송 OK");
      }else if(r.data=="0"){
        alert("아이디 중복");
      }

    }).fail(function(r) {
      var message = JSON.parse(r.responseText);
         alert('서버 오류');
    });
 });