<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Product> products = (List<Product>)session.getAttribute("products");
	int totalPages = (int)session.getAttribute("totalPages");
	int productPage = (int)session.getAttribute("page");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>產品系統:查詢產品資料</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminProduct/adminProductRead.css" >
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="/LoremIpsum/js/adminProduct/adminProductRead.js"></script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>查詢產品資料</h3>
			<table>
				<tr>
					<th>ID</th>
					<th>產品名稱</th>
					<th>Actions</th>
				</tr>
				<%for(Product p: products){ %>
					<tr>
						<td><%=p.getProductID() %></td>
						<td><%=p.getProductName() %></td>
						<td><button onclick="editProduct(<%=p.getProductID() %>)">修改</button></td>
					</tr>
				<%} %>
			</table>		
			
			頁數:
			<%for(int index=1;index<=totalPages;index++){ %>
				<a id="pagination" href="/LoremIpsum/adminProduct/read?page=<%=index %>"><%=index %></a>
			<%} %>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>		
</body>

</html>