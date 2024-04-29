<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <style>
       div.container { width: 600px; margin: 50px auto; }
       input { padding: 5px; font-size: 10pt; }
       button { margin: 10px ; padding: 0.4em 2em; }
       thead th { background-color: #eee; }
       table{ border-collapse: collapse; width: 100%; }
       td, th { padding: 4px; border: 1px solid lightgray; }
      td:nth-child(4) { text-align: center; }
      tr[data-url]:hover { background-color: #ffb; cursor: pointer; }
a{text-decoration:none; color:inherit;}
  </style>
</head>
<body>
<div class="container">
  <h1>학생목록</h1>
  <form name="form">
    <label>이름</label>
    <input type="text" name="srchText" value="${ srchText }" placeholder="검색조건" />
    <button type="submit" name="button">조회</button>
  </form>


  <table class="table table-bordered table-condensed">
    <thead>
      <tr>
        <th>학번</th>
        <th>이름</th>
        <th>학과</th>
        <th>전화</th>
        <th>성별</th>
        <th>이메일</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="student" items="${ students }">
        <tr data-url="detail?id=${ student.id }">
          <td>${ student.studentNo }</td>
          <td><a href="detail?id=${ student.id }">${ student.name }</a></td>
          <td>${ student.departmentName }</td>
          <td>${ student.phone }</td>
          <td>${ student.sex }</td>
          <td>${ student.email }</td>
        </tr>
      </c:forEach>

    <c:if test="${empty students}">
       <tr>
         <td colspan="6">찾는 학생이 존재하지 않습니다.</td>
       </tr>
    </c:if>
   </tbody>
  </table>
</div>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$("[data-url]").click(function() {
  var url = $(this).attr("data-url");
  location.href = url;
})
</script>

</body>
</html>
