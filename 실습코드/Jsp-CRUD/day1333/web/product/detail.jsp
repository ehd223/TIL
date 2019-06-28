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
	<h1>Product Detail</h1>
	<p>
		<button id="delete_product">삭제</button>
		<button id="modify_product">수정</button>
	</p>
	<img width="150px" src="img/product/${pd.imgName}">
	<h4>${pd.id}</h4>
	<h4>${pd.name}</h4>
	<h4>${pd.price}원</h4>
	<h4>${pd.regdate} 에 입고됨</h4>
	
	<script>
		$("button#delete_product").click(function(){
			var c = confirm("삭제하시겠습니까?");
			if(c){
				location.href='req?type=product&cmd=delete&id=${pd.id}';
			}
		})
		$("button#modify_product").click(function(){
			var c = confirm("수정하시겠습니까?");
			if(c){
				location.href='req?type=product&cmd=modiform&id=${pd.id}';
			}
		})
	</script>
</body>
</html>