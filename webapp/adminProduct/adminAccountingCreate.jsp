<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>產品系統:新增產品資料</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<style>
		h3{
			margin-bottom: 15px;
		}
		
		table{
			border-collapse: collapse;
			width: 50%;
		}
		
		td{
			border: 1px solid black;
			padding: 1px 3px;
		}
		
		button{
			width: 80%;
			background-color: #0070C9;
			color: white;
			border-radius: 15px;
			padding: 3px 0px;
		}
	</style>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>新增產品資料</h3>
			<form action="/LoremIpsum/adminProduct/create" method="post">
				<table>
					<tr>
						<td>產品名稱</td>
						<td><input type="text" name="productName"></td>
					</tr>
					<tr>
						<td colspan="2"><button type="submit">新增產品資料</button></td>
					<tr>
				</table>
			</form>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>		
</body>
</html>