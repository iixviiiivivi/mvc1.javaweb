<%@page import="model.Warehouse"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Integer> companyIDs = (List<Integer>)session.getAttribute("companyIDs");
	List<Integer> productDetailIDs = (List<Integer>)session.getAttribute("productDetailIDs");
	Warehouse wh = (Warehouse)session.getAttribute("warehouse");
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>進貨系統:新增進貨資料</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminPurchase/adminPurchaseCreate.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<!-- <script type="text/javascript" src="/LoremIpsum/js/adminPurchase/adminPurchaseCreate.js"></script> -->
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>新增進貨資料</h3>
			<form action="/LoremIpsum/adminPurchase/create" method="post">
				<table>
					<input type="hidden" name="size" value=<%=wh.getSize() %> ></td>
					<tr>
						<td>廠商編號</td>
						<td>
							<select name="companyID">
								<%for(int id:companyIDs){ %>
									<option value=<%=id %> <%if(wh.getCompanyID()==id){ %>selected<%} %> ><%=id %></option>
								<%} %>
							</select>
						</td>
					</tr>
					<tr>
						<td>產品詳細資料編號</td>
						<td>
							<select name="productDetailID">
								<%for(int id:productDetailIDs){ %>
									<option value=<%=id %> <%if(wh.getProductDetailID()==id){ %>selected<%} %> ><%=id %></option>
								<%} %>
							</select>
						</td>
					</tr>
					<tr>
						<td>數量</td>
						<td><input type="text" name="quantity" ></td>
					</tr>
					<tr>
						<td>成本</td>
						<td><input type="text" name="cost" ></td>
					</tr>
					<tr>
						<td>下單日期</td>
						<td><input type="date" name="orderDate" ></td>
					</tr>
					<tr>
						<td>入庫日期</td>
						<td><input type="date" name="arrivalDate" ></td>
					</tr>
					<tr>
						<td>付款狀態</td>
						<td><input type="text" name="paymentStatus" ></td>
					</tr>
					<tr>
						<td>進貨狀態</td>
						<td><input type="text" name="purchaseStatus" ></td>
					</tr>
					<tr>
						<td colspan="2"><button type="submit">新增進貨資料</button></td>
					</tr>
				</table>
			</form>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>	
</body>
</html>