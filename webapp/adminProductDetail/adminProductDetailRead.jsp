<%@page import="model.ImageBase64"%>
<%@page import="model.ProductDetail"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<ProductDetail> productDetails = (List<ProductDetail>)session.getAttribute("productDetails");
	int totalPages = (int)session.getAttribute("totalPages");
	int pdPage = (int)session.getAttribute("page");
%>    

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>產品系統: 查詢產品詳細資料</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminProductDetail/adminProductDetailRead.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<script type="text/javascript">
		function editProductDetail(id){
			$.get("/LoremIpsum/adminProductDetail/update?id=" + id, function(data){
				$("body").html(data);
				window.location = "http://localhost:8080/LoremIpsum/adminProductDetail/adminProductDetailUpdate.jsp";
			})
		}
	</script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>查詢產品詳細資料</h3>
			<table>
				<tr>
					<th>PD ID</th>
					<th>PID</th>
					<th>產品名稱</th>
					<th>CID</th>
					<th>公司名稱</th>
					<th>顏色</th>
					<th>圖片</th>
					<th>Action</th>
				</tr>
				<%for(ProductDetail pd: productDetails){ %>
				<tr>
					<td><%=pd.getProductDetailID() %></td>
					<td><%=pd.getProduct().getProductID() %></td>
					<td><%=pd.getProduct().getProductName() %></td>
					<td><%=pd.getCompany().getCompanyID() %></td>
					<td><%=pd.getCompany().getCompanyName() %></td>
					<td><%=pd.getColor() %>
					<td><img src=<%=ImageBase64.bytesArrayToBase64(pd.getImage(), 0.1, 0.1) %> ></td>
					<td><button onclick="editProductDetail(<%=pd.getProductDetailID() %>)">修改</button></td>
				<tr>
				<%} %>
			</table>
			
			<%for(int index=1; index <= totalPages; index++){ %>
				<a id="pagination" href="/LoremIpsum/adminProductDetail/read?page=<%=index %>"><%=index %></a>
			<%} %>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>			
</body>
</html>