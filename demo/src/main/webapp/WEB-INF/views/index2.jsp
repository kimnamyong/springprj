<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1> 안녕하세요 반갑습니다.</h1>
<h3>${user} 님  ${message}</h3>
<h3><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${ now }" /></h3>
</body>
</html>