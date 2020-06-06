<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>會員管理</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<style type="text/css">
		h3{
			margin-bottom:15px;
		}
		
		a{
			text-decoration:none;
		}
	</style>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>
			<h3>會員管理</h3>
			<a href="/LoremIpsum/member/read">會員資料</a><br/>
			<a href="/LoremIpsum/member/updatePassword">修改密碼</a><br/>
			<a href="/LoremIpsum/member/delete">會員刪除</a><br/>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>