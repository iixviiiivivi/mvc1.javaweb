<%@page import="model.OrderList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<OrderList> orderLists = (List<OrderList>)session.getAttribute("orderLists");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>購物清單</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<style type="text/css">
		#memberShop{
			color:black;
			background-color:#F5F5F7	
		}
		
		h3{
			margin-bottom:15px;
		}
		
		table{
			width:100%;
			border-collapse:collapse;
			text-align:center;
		}
		
		table, tr, td{
			border:1px solid black;
		}
		
		tr:nth-child(odd){
			background-color:#D2D2D2;
		}
		
		tr:hover{
			background-color:lightgreen;
		}
		
		p{
			margin-top:15px;
		}
		
		button {
			width: auto;
			background-color: #0070C9;
			padding:3px 5px;
			color: white;
			border-radius: 5px;
		}
	</style>
	
	<script type="text/javascript">
		$(function(){
			$("button").click(function(){
				var orderID = $("#orderID").val();
				if(confirm("確定取消訂單編號:"+ orderID +"?")){
					$.post("/LoremIpsum/memberShop/deleteOrders?orderID="+orderID,function(data){
						$("body").html(data);
					})
					.done(function(){ console.log("取消訂單成功"); })
					.fail(function(){ alert("取消訂單失敗"); });
				}
			});
		});
	</script>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>購物清單</h3>
			<table>
				<tr>
					<td>訂單編號</td>
					<td>產品名稱</td>
					<td>尺寸</td>
					<td>顏色</td>
					<td>數量</td>
					<td>價格</td>
					<td>總價</td>
					<td>訂購日期</td>
					<td>訂單狀態</td>
				</tr>	
				<%for(OrderList ol: orderLists){ %>
				<tr>
					<td class="id"><%=ol.getOrderID() %></td>
					<td><%=ol.getProductName() %></td>
					<td><%=ol.getSize() %></td>
					<td><%=ol.getColor() %></td>
					<td><%=ol.getQuantity() %></td>
					<td><%=ol.getPrice() %></td>
					<td><%=ol.getQuantity() * ol.getPrice() %></td>
					<td><%=ol.getOrderDate().substring(0, 10) %></td>
					<td><%=ol.getOrderStatus() %></td>
				</tr>			
				<%} %>
			</table>
			<p>輸入訂單編號取消: <input type="text" id="orderID" size="3"> <button>取消訂單</button></p>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>	
</body>
</html>