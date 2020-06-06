<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>會員系統</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<style type="text/css">
		#memberSystem{
			color:black;
			background-color:#F5F5F7	
		}
	</style>
</head>
<body>
<div id="wrapper">
	<jsp:include page="../header.jsp" />
	
	<main>
		<a href="/LoremIpsum/adminMember/create">新增會員</a><br/>
		<a href="/LoremIpsum/adminMember/read">會員管理</a><br/>
	</main>

	<jsp:include page="../footer.jsp" />
</div>	
</body>
</html>