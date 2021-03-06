<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>進貨系統</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<style type="text/css">
		#purchaseSystem{
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
			<h3>進貨系統</h3>
			<a href="/LoremIpsum/adminPurchase/read">查詢進貨資料</a><br>
			<a href="/LoremIpsum/adminPurchase/readStocks">查詢庫存資料</a><br>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>	
</body>
</html>