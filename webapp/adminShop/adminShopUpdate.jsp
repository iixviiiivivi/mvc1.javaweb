<%@page import="model.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Order order = (Order)session.getAttribute("order");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>購物系統:更新購物資料</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminShop/adminShopUpdate.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>更新購物資料</h3>
			<form action="/LoremIpsum/adminShop/updateOrder" method="post">
				<table>
					<input type="hidden" name="orderID" value=<%=order.getOrderID() %> >	
					<input type="hidden" name="memberID" value=<%=order.getMemberID() %> >					
					<tr>
						<td>姓</td>
						<td><input type="text" name="lastName" value=<%=order.getLastName() %> ></td>						
					</tr>
					<tr>
						<td>名</td>
						<td><input type="text" name="firstName" value=<%=order.getFirstName() %> ></td>						
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" name="email" value=<%=order.getEmail() %> ></td>						
					</tr>
					<tr>
						<td>手機</td>
						<td><input type="text" name="mobile" value=<%=order.getMobile() %>></td>						
					</tr>
					<tr>
						<td>國家</td>
						<td><input type="text" name="country" value=<%=order.getCountry() %>></td>						
					</tr>
					<tr>
						<td>城市</td>
						<td><input type="text" name="city" value=<%=order.getCity() %> ></td>						
					</tr>
					<tr>
						<td>區</td>
						<td><input type="text" name="district" value=<%=order.getDistrict() %> ></td>						
					</tr>
					<tr>
						<td>地址</td>
						<td><input type="text" name="address" value=<%=order.getAddress() %> ></td>						
					</tr>
					<tr>
						<td>訂單日期</td>
						<td><input type="date" name="orderDate" value=<%=order.getOrderDate() %> ></td>						
					</tr>
					<tr>
						<td>運送日期</td>
						<td><input type="date" name="shippingDate" value=<%=order.getShippingDate() %> ></td>						
					</tr>
					<tr>
						<td>狀態</td>
						<td><input type="text" name="orderStatus" value=<%=order.getOrderStatus() %> ></td>						
					</tr>
					<tr>
						<td colspan="2"><button type="submit">更新購物資料</button></td>
					</tr>
				</table>
			</form>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>	
</body>
</html>