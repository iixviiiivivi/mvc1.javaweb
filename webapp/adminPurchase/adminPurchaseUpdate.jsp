<%@page import="model.Purchase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Purchase purchase = (Purchase)session.getAttribute("purchase");
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>進貨系統:更新進貨資料</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminPurchase/adminPurchaseUpdate.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>更新進貨資料</h3>
			<form action="/LoremIpsum/adminPurchase/update" method="post">
				<table>
					<input type="hidden" name="purchaseID" value=<%=purchase.getPurchaseID() %> >
					<tr>
						<td>廠商編號</td>
						<td><input type="text" name="companyID" value=<%=purchase.getCompanyID() %> ></td>
					</tr>
					<tr>
						<td>物品詳細編號</td>
						<td><input type="text" name="productDetailID" value=<%=purchase.getProductDetailID() %> ></td>
					</tr>
					<tr>
						<td>尺寸</td>
						<td><input type="text" name="size" value=<%=purchase.getSize() %> ></td>
					</tr>
					<tr>
						<td>數量</td>
						<td><input type="text" name="quantity" value=<%=purchase.getQuantity() %> ></td>
					</tr>
					<tr>
						<td>成本</td>
						<td><input type="text" name="cost" value=<%=purchase.getCost() %> ></td>
					</tr>
					<tr>
						<td>下單日期</td>
						<td><input type="date" name="orderDate" value=<%=purchase.getOrderDate() %> ></td>
					</tr>
					<tr>
						<td>入庫日期</td>
						<td><input type="date" name="arrivalDate"  value=<%=purchase.getArrivalDate()%> ></td>
					</tr>
					<tr>
						<td>付款狀態</td>
						<td><input type="text" name="paymentStatus" value=<%=purchase.getPaymentStatus() %> ></td>
					</tr>
					<tr>
						<td>進貨狀態</td>
						<td><input type="text" name="purchaseStatus" value=<%=purchase.getPurchaseStatus() %> ></td>
					</tr>
					<tr>
						<td colspan="2"><button type="submit">更新進貨資料</button></td>
					</tr>
				</table>
			</form>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>	
</body>
</html>