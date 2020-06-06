<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>會計系統</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<style type="text/css">
		#accountingSystem{
			color:black;
			background-color:#F5F5F7;
		}
		
		h3{
			margin-bottom:15px;
		}
	</style>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>會計系統</h3>
			<a href="/LoremIpsum/adminAccounting/create">新增會計資料</a><br>
			<a href="/LoremIpsum/adminAccounting/read">查詢會計資料</a><br>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>	
</body>
</html>