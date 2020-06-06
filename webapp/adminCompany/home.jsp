<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>廠商系統</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<style type="text/css">
		#companySystem{
			color:black;
			background-color:#F5F5F7;	
		}
	</style>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>
			<a href="/LoremIpsum/adminCompany/create">新增廠商</a><br/>
			<a href="/LoremIpsum/adminCompany/read">廠商管理</a><br/>
		</main>
	
		<jsp:include page="../footer.jsp" />	
	</div>
</body>
</html>