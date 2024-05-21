<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>
<div class="container">
     <form>
      <div class="form-group">
        <label> ID  </label>
        <input type="text" id="id" value="${principal.id}" readOnly class="form-control">
      </div>
      <div class="form-group">
           <label for="username">사용자아이디</label>
          <input type="text" id="username"
            value="${principal.username}" readOnly placeholder="${principal.username}" class="form-control">
      </div>
      <div class="form-group">
          <label for="password">패스워드</label>
        <input type="password" id="password" class="form-control"
         value="${principal.password}">
       </div>
     <div class="form-group">
        <label for="email">이메일</label>
         <input type="email" id="email" class="form-control"
         value="${principal.email}"
         >
     </div>
    </form>
   <button id="btn-update" class="btn btn-primary">회원수정완료</button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>