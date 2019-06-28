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
	<h1>Product Modify form</h1>
	<form action="req?type=product&cmd=modify" method="POST" enctype="multipart/form-data">
		ID <input type="text" value="${pu.id }" name="id" readonly><br>
		Name <input type="text" name="name"><br>
		Price <input type="text" name="price"><br>
		IMG <input type="file" name="imgname"><br>
		<input type="submit" value="업뎃" class="btn btn-primary">
	</form>
</body>
</html>