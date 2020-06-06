<%@page import="model.Purchase"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Purchase> purchases = (List<Purchase>)session.getAttribute("purchases");
	int totalPage = (int)session.getAttribute("totalPage");
	int purchasePage = 1;
	if(session.getAttribute("page")!=null){
		purchasePage = (int)session.getAttribute("page");
	}	
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>進貨系統:查詢進貨資料</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminPurchase/adminPurchaseRead.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="/LoremIpsum/js/adminPurchase/adminPurchaseRead.js"></script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>查詢進貨資料</h3>
			<table>
				<tr>
					<th>進貨編號</th>
					<th>廠商編號</th>
					<th>產品詳細編號</th>
					<th>尺寸</th>
					<th>數量</th>
					<th>成本</th>
					<th>下單日期</th>
					<th>入庫日期</th>
					<th>付款狀態</th>
					<th>進貨狀態</th>
					<th>管理</th>
				</tr>
				<%for(Purchase p: purchases){ %>
				<tr>
					<td><%=p.getPurchaseID() %></td>
					<td><%=p.getCompanyID() %></td>
					<td><%=p.getProductDetailID() %></td>
					<td><%=p.getSize() %></td>
					<td><%=p.getQuantity() %></td>
					<td><%=p.getCost() %></td>
					<td><%=p.getOrderDate()!=null?p.getOrderDate().substring(0, 10):"-" %></td>
					<td><%=p.getArrivalDate()!=null?p.getArrivalDate().substring(0, 10):"-" %></td>
					<td><%=p.getPaymentStatus() %></td>
					<td><%=p.getPurchaseStatus() %></td>
					<td>
						<button onclick="editPurchase(<%=p.getPurchaseID() %>)">修改</button>
						<button onclick="deletePurchase(<%=p.getPurchaseID() %>)">刪除</button>
					</td>
				</tr>
				<%} %>
			</table>
			<br>
			<p>
				第<select>
					<%for(int i=1;i<=totalPage;i++){ %>
						<option value=<%=i %> <%if(i==purchasePage){ %>selected<%} %>><%=i %></option>
					<%} %>
				</select>頁
			</p>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>	
</body>
</html>