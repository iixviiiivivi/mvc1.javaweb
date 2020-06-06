<%@page import="model.Order"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Order> orders = (List<Order>)session.getAttribute("orders");
	int totalPage = (int)session.getAttribute("totalPage");
	int orderPage = 1;
	if(session.getAttribute("page")!=null){
		orderPage = (int)session.getAttribute("page");
	}	
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>購物管理系統:查詢訂單</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminShop/adminShopRead.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="/LoremIpsum/js/adminShop/adminShopRead.js"></script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>查詢訂單</h3>
			<table>
				<tr>
					<th>訂單<br>編號</th>
					<th>會員<br>編號</th>
					<th>姓名</th>
					<th>Email</th>
					<th>手機</th>
					<th>地址</th>
					<th>訂單日期</th>
					<th>運送日期</th>
					<th>狀態</th>
					<th>管理</th>
				</tr>
				<%for(Order o:orders){ %>
				<tr>
					<td><%=o.getOrderID() %></td>
					<td><%=o.getMemberID() %></td>
					<td><%=o.getLastName()+o.getFirstName() %></td>
					<td><%=o.getEmail() %></td>
					<td><%=o.getMobile() %></td>
					<td><%=o.getCountry()+o.getCity()+o.getDistrict()+o.getAddress()%></td>
					<td><%=o.getOrderDate()!=null?o.getOrderDate().substring(0, 10):"-" %></td>
					<td><%=o.getShippingDate()!=null?o.getShippingDate().substring(0, 10):"-" %></td>
					<td><%=o.getOrderStatus() %></td>
					<td>
						<button onclick="queryOrders(<%=o.getOrderID() %>)">查詢</button>
						<button onclick="editOrders(<%=o.getOrderID() %>)">修改</button>
						<button onclick="cancelOrders(<%=o.getOrderID() %>)">取消</button>
					</td>
				</tr>
				<%} %>
			</table>
			<br>
			<p>
				第<select>
					<%for(int i=1;i<=totalPage;i++){ %>
						<option value=<%=i %> <%if(i==orderPage){ %>selected<%} %>><%=i %></option>
					<%} %>
				</select>頁
			</p>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>	
</body>
</html>