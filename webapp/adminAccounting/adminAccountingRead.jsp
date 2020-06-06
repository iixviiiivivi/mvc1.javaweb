<%@page import="model.Accounting"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Accounting> accountings = (List<Accounting>)session.getAttribute("accountings");
	int totalPage = (int)session.getAttribute("totalPage");
	int accountingPage = 1;
	if(session.getAttribute("page")!=null){
		accountingPage = (int)session.getAttribute("page");
	}		
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>查詢會計資料</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminAccounting/adminAccountingRead.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="/LoremIpsum/js/adminAccounting/adminAccountingRead.js"></script>
</head>

<body>
		<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>查詢會計資料</h3>	
			<table>
				<tr>
					<th>會計編號</th>
					<th>訂單編號</th>
					<th>進貨編號</th>
					<th>成本</th>
					<th>價格</th>
					<th>帳目日期</th>
					<th>管理</th>
				</tr>
				<%for(Accounting acc:accountings){ %>
				<tr>
					<td><%=acc.getAccountingID() %></td>
					<td><%=acc.getOrderID()!=null?acc.getOrderID():"-" %></td>
					<td><%=acc.getPurchaseID()!=null?acc.getPurchaseID():"-" %></td>
					<td><%=acc.getCost() %></td>
					<td><%=acc.getProfit() %></td>
					<td><%=acc.getDate().subSequence(0, 10) %></td>
					<td>
						<button onclick="editAccounting(<%=acc.getAccountingID()%>)">修改</button>
						<button onclick="deleteAccounting(<%=acc.getAccountingID()%>)">刪除</button>
					</td>
				</tr>
				<%} %>
			</table>
			<br>
			<p>
				第<select>
					<%for(int i=1;i<=totalPage;i++){ %>
						<option value=<%=i %> <%if(i==accountingPage){ %>selected<%} %>><%=i %></option>
					<%} %>
				</select>頁
			</p>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>	
</body>
</html>