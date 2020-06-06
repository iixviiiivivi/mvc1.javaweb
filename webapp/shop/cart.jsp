<%@page import="model.Member"%>
<%@page import="model.Cart"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Map<String, Cart> carts = null;
	Member member = null;
	if(session.getAttribute("cart")!=null){
		carts = (Map<String, Cart>)session.getAttribute("cart");
	}
	if(session.getAttribute("account")!=null){
		member = (Member)session.getAttribute("account");
	}
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>購物車</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/shop/cart.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="/LoremIpsum/js/shop/cart.js"></script>	
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>			
			<h3>購物車</h3>
			<table>
				<tr>
					<td>產品編號</td>
					<td>尺寸</td>
					<td>顏色</td>
					<td>數量</td>
					<td>單價</td>
					<td>價格</td>
					<td>管理</td>
				</tr>
				<%
				if(carts!=null && carts.size()!=0){
					int sum = 0;
					for(String key:carts.keySet()){ 
						Cart c = carts.get(key);
						sum += c.getQuantity()*c.getPrice();
				%>
				<tr>
					<td><%=c.getId() %></td>
					<td><%=c.getSize() %></td>
					<td><%=c.getColor() %></td>
					<td><%=c.getQuantity() %></td>
					<td>$<%=c.getPrice() %></td>
					<td>$<%=c.getQuantity()*c.getPrice() %></td>
					<td><button onclick="cancelOrder('<%=c.getId().toString() %>','<%=c.getSize() %>')">取消</button></td>
				</tr>
				<%} %>
				<tr>
					<td colspan="4">總共</td>
					<td colspan="3">$<%=sum %></td>
				</tr>
				<%}else{ %>
				<tr>
					<td colspan="7" style="color:red;">請加入產品到購物車中</td>
				</tr>
			<%} %>
			</table>
			
			<br>
			<h3>寄送資訊</h3>
			<form>
				<table id="tblMail">
					<input type="hidden" name="memberID" value=<%=member.getMemberID() %> >
					<tr>
						<td>姓</td>
						<td><input type="text" name="lastName" value=<%=member.getLastName() %> ></td>
					</tr>
					<tr>
						<td>名</td>
						<td><input type="text" name="firstName" value=<%=member.getFirstName() %> ></td>
					</tr>
					<tr>
						<td>手機</td>
						<td><input type="text" name="mobile" value=<%=member.getMobile() %> ></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" name="email" value=<%=member.getMobile() %> ></td>
					</tr>
					<tr>
						<td>國家</td>
						<td><input type="text" name="country"  value=<%=member.getCountry() %> > </td>
					</tr>	
					<tr>
						<td>城市</td>
						<td><input type="text" name="city"  value=<%=member.getCity() %> > </td>
					</tr>		
					<tr>
						<td>區</td>
						<td><input type="text" name="district"  value=<%=member.getDistrict() %> > </td>
					</tr>	
					<tr>
						<td>地址</td>
						<td><input type="text" name="address"  value=<%=member.getAddress() %> > </td>
					</tr>
				</table>
				
				<div>
					<button id="buy">購買</button>
				</div>
			</form>
			<span id="cartsSize" style="display:none;"><%=carts %></span>
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>	
</body>
</html>
