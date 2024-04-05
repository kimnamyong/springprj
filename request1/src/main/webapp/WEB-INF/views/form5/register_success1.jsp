
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
div.container {
	width: 310px;
	margin: 20px auto;
}

table {
	width: 300px;
	border-collapse: collapse;
}

thead tr {
	background-color: #eee;
}

td, th {
	border: 1px solid #aaa;
	padding: 5px;
}

td:nth-child(1) {
	background-color: #eee;
}
</style>
</head>
<body>

	<div class="container">

		<h1>회원가입 성공</h1>

		<table>
			<tr>
				<td>사용자 아이디</td>
				<td>${member.userid }</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${ member.name }</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${ member.email }</td>
			</tr>
			<tr>
				<td>전공ID</td>
				<td>${ member.departmentId }</td>
			</tr>
			<tr>
				<td>전공과목</td>
				<td><c:choose>
						<c:when test="${member.departmentId==1}">
							<p>소프트웨어공학</p>
						</c:when>
						<c:when test="${member.departmentId==2}">
							<p>컴퓨터공학</p>
						</c:when>
						<c:when test="${member.departmentId==3}">
							<p>정보통신공학</p>
						</c:when>
						<c:otherwise>
							<p>글로벌IT</p>
						</c:otherwise>
					</c:choose></td>
			</tr>

		</table>
	</div>

</body>
</html>
