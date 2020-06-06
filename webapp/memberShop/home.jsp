<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>購物管理</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<style type="text/css">
		#memberShop{
			color:black;
			background-color:#F5F5F7	
		}
		
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
			<h3>購物管理</h3>
			<a href="/LoremIpsum/memberShop/readOrders">查詢購物清單</a><br>
			
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>	
</body>
</html>