<%@page import="model.OrderDetail"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<OrderDetail> orderDetails = (List<OrderDetail>)session.getAttribute("orderDetails");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>購物系統:訂單詳細資料</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminShop/adminShopOrderDetails.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>訂單詳細資料</h3>
			<table>
				<tr>
					<th>訂單編號</th>
					<th>產品詳細編號</th>
					<th>顏色</th>
					<th>尺寸</th>
					<th>數量</th>
					<th>價格</th>
				</tr>
				<%for(OrderDetail od: orderDetails){ %>
				<tr>
					<td><%=od.getOrderID() %></td>
					<td><%=od.getProductDetailID() %></td>
					<td><%=od.getColor() %></td>
					<td><%=od.getSize() %></td>
					<td><%=od.getQuantity() %></td>
					<td><%=od.getPrice() %></td>
				</tr>
				<%} %>
			</table>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>	
</body>
</html>