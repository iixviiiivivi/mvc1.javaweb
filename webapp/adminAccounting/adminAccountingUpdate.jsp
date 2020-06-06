<%@page import="model.Accounting"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Accounting acc = (Accounting)session.getAttribute("accounting");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>會計系統:更新會計資料</title>
	
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminAccounting/adminAccountingUpdate.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>會計系統:更新會計資料</h3>	
			<form action="/LoremIpsum/adminAccounting/update" method="post">
				<table>
					<input type="hidden" name="accountingID" value=<%=acc.getAccountingID() %> >
					<tr>
						<td>訂單編號</td>
						<td><input type="text" name="orderID" value=<%=acc.getOrderID()!=null?acc.getOrderID():"" %> ></td>
					</tr>
					<tr>
						<td>進貨編號</td>
						<td><input type="text" name="purchaseID" value=<%=acc.getPurchaseID()!=null?acc.getPurchaseID():"" %> ></td>
					</tr>
					<tr>
						<td>成本</td>
						<td><input type="text" name="cost" value=<%=acc.getCost() %> ></td>
					</tr>
					<tr>
						<td>價格</td>
						<td><input type="text" name="profit" value=<%=acc.getProfit() %> ></td>
					</tr>
					<tr>
						<td>帳目日期</td>
						<td><input type="date" name="date" value=<%=acc.getDate() %> ></td>
					</tr>
					<tr>
						<td colspan="2"><button type="submit">會計資料更新</button></td>
					</tr>
				</table>
			</form>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>	
</body>
</html>