let index={
   init:function(){
      $('#btn-save').on('click',()=>{
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
             alert("로그인이 완료되었습니다.");
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
}

index.init();
