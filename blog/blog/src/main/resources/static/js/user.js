let index={
   init:function(){
      $('#btn-save').on('click',()=>{
         this.save();
      });

//     $('#btn-login').on('click',()=>{
//        this.login();
//      });

      // 아이디중복검사
      $('#btn-check').on('click',()=>{
             event.preventDefault();
            this.check();
      });

      $('#btn-update').on('click',()=>{
           this.update();
       });

       $('#btn-delete').on('click',()=>{
            this.delete();
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
   }, // init 끝

// delete
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
    console.log(resp)
      if(resp.data==1){
        alert("회원탈퇴가 완료되었습니다.");
        location.href="/user/logout";
      }else{
        alert("비밀번호가 잘못되었습니다.");
        return false;
      }
     }).fail(function(error){
        alert(JSON.stringify(error));
     });
  },

   update:function(){
     let data={
       id:$('#id').val(),
       username: $("#username").val(),
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
      url:'/auth/joinProc',
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





