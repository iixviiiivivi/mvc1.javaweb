<%@page import="model.Warehouse"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	List<Warehouse> warehouses = (List<Warehouse>)session.getAttribute("warehouses");
 	int totalPage = (int)session.getAttribute("totalPage");
	int warehousePage = 1;
	if(session.getAttribute("page")!=null){
		warehousePage = (int)session.getAttribute("page");
	}		
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>進貨系統:庫存資料</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminPurchase/adminPurchaseReadStocks.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="/LoremIpsum/js/adminPurchase/adminPurchaseReadStocks.js"></script>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>庫存資料</h3>
			<table>
				<tr>
					<th>產品詳細編號</th>
					<th>產品名稱</th>
					<th>顏色</th>
					<th>尺寸</th>
					<th>庫存</th>
					<th>賣出</th>
					<th>物品狀態</th>
					<th>管理</th>
				</tr>
				<%for(Warehouse wh: warehouses){ %>
					<tr>
						<td><%=wh.getProductDetailID() %></td>
						<td><%=wh.getProductName() %></td>
						<td><%=wh.getColor() %></td>
						<td><%=wh.getSize() %></td>
						<td><%=wh.getStock() %></td>
						<td><%=wh.getSold() %></td>
						<td><%=wh.getStockStatus() %></td>
						<td>
							<button onclick="purchaseStock('<%=wh.getProductDetailID()%>', '<%=wh.getSize()%>')">進貨</button>
						</td>
					</tr>
				<%} %>
			</table>
			<br>
			<p>
				第<select>
					<%for(int i=1;i<=totalPage;i++){ %>
						<option value=<%=i %> <%if(i==warehousePage){ %>selected<%} %>><%=i %></option>
					<%} %>
				</select>頁
			</p>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>	
</body>
</html>