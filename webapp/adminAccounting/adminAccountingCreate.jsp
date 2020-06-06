<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>會計系統:新增會計資料</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminAccounting/adminAccountingCreate.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>新增會計資料</h3>	
			<form action="/LoremIpsum/adminAccounting/create" method="post">
				<table>
					<tr>
						<td>訂單號碼</td>
						<td><input type="text" name="orderID" ></td>
					</tr>
					<tr>
						<td>進貨號碼</td>
						<td><input type="text" name="purchaseID" ></td>
					</tr>
					<tr>
						<td>成本</td>
						<td><input type="text" name="cost" ></td>
					</tr>
					<tr>
						<td>價格</td>
						<td><input type="text" name="profit" ></td>
					</tr>
					<tr>
						<td>帳目日期</td>
						<td><input type="date" name="date" ></td>
					</tr>
					<tr>
						<td colspan="2"><button type="submit">新增會計資料</button></td>
					</tr>
				</table>
			</form>	
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>	
</body>
</html>