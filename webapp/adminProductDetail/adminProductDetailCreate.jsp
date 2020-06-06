<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Company"%>
<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%
	List<Product> products = (List<Product>)session.getAttribute("products");
	List<Company> companies = (List<Company>)session.getAttribute("companies");
%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>產品系統:新增產品詳細資料</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminProductDetail/adminProductDetailCreate.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>新增產品詳細資料</h3>
			<form action="/LoremIpsum/adminProductDetail/create" method="post" enctype="multipart/form-data" >
				<table>
					<tr>
						<td>產品名稱</td>
						<td>
							<select name="product.productID">
							<%for(Product p: products){ %>
								<option value=<%=p.getProductID() %> ><%=p.getProductName() %></option>
							<%} %>
							</select>
						</td>
					</tr>
					<tr>
						<td>公司名稱</td>
						<td>
							<select name="company.companyID">
							<%for(Company c: companies){ %>
								<option value=<%=c.getCompanyID() %> ><%=c.getCompanyName() %></option>
							<%} %>
							</select>
						</td>
					</tr>
					<tr>
						<td>顏色</td>
						<td><input type="text" name="color"></td>
					</tr>
					<tr>
						<td>圖片</td>
						<td><input type="file" name="img"></td>
					</tr>
					<tr>
						<td colspan="2"><button type="submit">更新產品詳細資料</button></td>
					</tr>
				</table>
			</form>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>		
</body>
</html>