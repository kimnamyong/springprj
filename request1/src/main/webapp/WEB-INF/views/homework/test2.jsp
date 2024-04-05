<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<form method="post">
			<h1>select 01</h1>

			<div>
			<select name="num" >
			<option value="one" 
			${num=="one"?"selected":""}> one</option>
			<option value="two"${num=="two"?"selected":""}> two</option>
			<option value="three"${num=="three"?"selected":""}> three</option>
			<option value="four"${num=="four"?"selected":""}> four</option>
			</select>

			</div>
			<div>
				 <input type="text" name="param1"
					value="${num}" size="10" />
			</div>

			<div>
				<button type="submit">OK</button>
			</div>
		</form>

	</div>



</body>
</html>