<%@page import="model.Cart"%>
<%@page import="java.util.List"%>
<%@page import="model.Stock"%>
<%@page import="java.util.Map"%>
<%@page import="model.ImageBase64"%>
<%@page import="model.ProductDetail"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ProductDetail product = (ProductDetail)session.getAttribute("productDetail");
	List<Stock> stocks= (List<Stock>)session.getAttribute("stocks");
	int totalSold = 0;
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title><%=product.getProduct().getProductName() %></title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/shop/shopProduct.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="/LoremIpsum/js/shop/shopProduct.js"></script>	
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>
			<h3>產品:<%=product.getProduct().getProductName() %></h3>
			<div>
				<img src=<%=ImageBase64.bytesArrayToBase64(product.getImage()) %>>
			</div>
			<div>
				<h4>產品資訊</h4>
				<table>
					<tr>
						<td>商品編號</td>
						<td id="id"><%=product.getProductDetailID() %></td>
					</tr>
					<tr>
						<td>商品名稱</td>
						<td><%=product.getProduct().getProductName() %></td>
					</tr>
					<tr>
						<td>顏色</td>
						<td id="color"><%=product.getColor() %></td>
					</tr>
					<tr>
						<td>尺寸/庫存</td>
						<td>
							<%for(Stock s:stocks){ 
								totalSold += s.getSold();
							%>
								<%=s.getSize() %> / <%=s.getStock()>0?s.getStock()+"件":"完售" %><br>
							<%} %><br>
							共售出:<%=totalSold %> 件
						</td>
					</tr>
					<tr>
						<td>廠商</td>
						<td><%=product.getCompany().getCompanyName() %></td>
					</tr>					
				</table>
			</div>
				<table id="tblBuy">
					<tr>
						<td>尺寸:</td>
						<td>
							<%for(int i=0;i<stocks.size();i++){ 
								Stock s = stocks.get(i);%>
								<div class="sizes" id="size<%=i %>"><%=s.getSize() %></div>
				     	    <%} %>
				     	    <span id="size" style="display:none"></span>	
						</td>
						<td>數量:</td>
						<td>
							<select id="quantity">
								<%for(int i=1;i<=10;i++ ){ %>
									<option value=<%=i %>><%=i %></option>
								<%} %>
						    </select>				
						</td>
						<td>原價:</td>
						<td id="priceBefore"></td>
						<td>折價後:</td>
						<td id="priceAfter"></td>
						<td>總價:</td>
						<td id="total"></td>
					</tr>
				</table>
				<button>加入購物車</button>				
			
		</main>
	
		<jsp:include page="../footer.jsp" />	
	</div>	
</body>

</html>