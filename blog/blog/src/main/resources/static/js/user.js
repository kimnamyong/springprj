let index={
   init:function(){
      $('#btn-save').on('click',()=>{
         this.save();
      });
     $('#btn-login').on('click',()=>{
        this.login();
      });

// Remember me 추가
    $('#memory').on('click', () => {
      if($('#memory').prop("checked")==true){
        this.memory();
      }else{
        localStorage.clear();
      }
    });

    // 페이지로딩시 아이디비번 자동입력
    $('#username').val(localStorage.getItem("username"));
    $('#password').val(localStorage.getItem("password"));
   },

   memory:function(){
      var username=$('#username').val();
      var password=$('#password').val();
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
                  alert("사용자 정보가 없습니다.");
                  $("#username").focus();
                  return false; // 다음단계로 진행안함
            }
                 console.log(resp.data);
                 location.href="/";
       }).fail(function(error){
              alert(JSON.stringify(error));
       });

   },

   save:function(){
      let data={
             username:$("#username").val(),
             password:$("#password").val(),
             email:$('#email').val()
      };

    $.ajax({
      type:"POST",
      url:'/api/user',
      data:JSON.stringify(data), // http body 데이터
      contentType:"application/json; charset=utf-8",
      dataType:"json"
    }).done(
     function(resp){
       alert("회원가입이 완료되었습니다.");
           console.log(resp);
          location.href="/";
    }).fail(function(error){
        alert(JSON.stringify(error));
    });
  }


}  // end

index.init();





