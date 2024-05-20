let index={
   init:function(){
    // 글쓰기 완료 btn-write
      $('#btn-write').on('click',()=>{
         this.save();
      });
    // 삭제하기
     $('#btn-delete').on('click',()=>{
           this.deleteById();
      });

  // 수정하기
   $('#btn-update').on('click',()=>{
         this.update();
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
            title:$("#title").val(),
            content:$("#content").val(),
      };

    $.ajax({
      type:"POST",
      url:'/api/board',
      data:JSON.stringify(data),
      contentType:"application/json; charset=utf-8",
      dataType:"json"
    }).done(
     function(resp){
       alert("글쓰기가 완료되었습니다.");
          location.href="/";
    }).fail(function(error){
        alert(JSON.stringify(error));
    });
  }

}  // end

index.init();





