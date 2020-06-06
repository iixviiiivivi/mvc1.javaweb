<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>產品系統</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<style>
		h3{
			margin-bottom: 15px;
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
			<h3>產品系統</h3>
			<a href="/LoremIpsum/adminProduct/create">新增產品資料</a><br>
			<a href="/LoremIpsum/adminProduct/read">查詢產品資料</a><br><br>
			<a href="/LoremIpsum/adminProductDetail/create">新增產品詳細資料</a><br>
			<a href="/LoremIpsum/adminProductDetail/read">查詢產品詳細資料</a><br>			
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>			
</body>

</html>