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
	<h1>User Detail</h1>
	<button id="delete_user">삭제</button>
	<button id="modify_user">수정</button>
	<img width="150px" src="img/${ud.id}.jpg">
	<h4>${ud.id}</h4>
	<h4>${ud.pwd}</h4>
	<h4>${ud.name}</h4>
	
	<script>
		$("button#delete_user").click(function(){
			var c = confirm("삭제하시겠습니까?");
			if(c){
				location.href='req?type=user&cmd=delete&id=${ud.id}';
			}
		})
		$("button#modify_user").click(function(){
			var c = confirm("수정하시겠습니까?");
			if(c){
				location.href='req?type=user&cmd=modiform&id=${ud.id}';
			}
		})
	</script>
</body>
</html>