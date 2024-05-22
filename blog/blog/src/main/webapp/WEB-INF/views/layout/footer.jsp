<div class="jumbotron jumbotron-fluid">
  <div class="container">
      <p>Created by LeeSunShin !!! </p>
     <p> (Tel) 010-222-6666 </p>
      <p> &copy; CopyRight All Right Reserved</p>
   </div>
 </div>

   </body>
</html>

<sec:authorize access="isAuthenticated()">
    <!-- 인증된 사용자만 이 내용을 볼 수 있음 -->
   <p>Welcome, ${pageContext.request.userPrincipal.name}!</p>
   <p>${pageContext.request.userPrincipal}!</p>
    <sec:authentication property="principal" var="principal" />
     <p>${principal}!</p>
</sec:authorize>