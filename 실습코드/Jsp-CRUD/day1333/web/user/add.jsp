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
	<h1>User Add Page</h1>
	
	<form id="r_form">
		ID <input type="text" name="id"><br>
		PW <input type="password" name="pw"><br>
		Name <input type="text" name="name"><br>
		<button class="btn btn-primary" value="회원가입">회원가입</button>
	</form>
	<script type="text/javascript">
		$('button').click(function(){
			var c = confirm("회원가입 하시겠습니까?");
			if(c){
				$("#r_form").attr('method', 'POST');
				$("#r_form").attr('action', 'req?type=user&cmd=register');
				$("#r_form").submit();
			}
		})
	</script>
</body>
</html>