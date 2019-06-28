<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body class="container">
	<h1>Product Add Page</h1>
	
	<form action="req?type=product&cmd=register" method="POST" enctype="multipart/form-data">
		Name <input type="text" name="name"><br>
		Price <input type="number" name="price"><br>
		IMG <input type="file" name="imgname"><br>
		<input class="btn btn-primary" type="submit" value="아이템등록">
	</form>	
</body>
</html>