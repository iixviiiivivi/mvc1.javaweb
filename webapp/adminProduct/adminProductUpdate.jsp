<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Product product = (Product)session.getAttribute("product");
%>    
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>產品系統:更新產品資料</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminProduct/adminProductUpdate.css">
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>更新產品資料</h3>
			<form action="/LoremIpsum/adminProduct/update" method="post">
				<table>
					<tr>
						<input type="hidden" name="productID" value=<%=product.getProductID() %>>
					</tr>
					<tr>
						<td>產品名稱</td>
						<td><input type="text" name="productName" value=<%=product.getProductName() %>></td>
					</tr>
					<tr>
						<td colspan="2"><button type="submit">更新產品資料</button></td>
					</tr>
				</table>
			</form>		
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>		
</body>
</html>