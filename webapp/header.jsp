<%@page import="model.Cart"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Member"%>    
<%
	Member m = (Member)session.getAttribute("account");
	Map<String, Cart> carts = null; 
	if(session.getAttribute("cart") != null)
		carts = (Map<String, Cart>)session.getAttribute("cart");
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>會員首頁</title>
</head>

<body>
	<header>
		<div id="logo"><a href="/LoremIpsum/index.jsp">Lorem Ipsum</a></div> 		
		<!-- 登入後顯示會員名稱 -->
		
		<div id="user">						
			<%if(m!=null){%>
				<a href="/LoremIpsum/member/home"><%=m.getUsername() %></a>
			<%} %>					
		</div>		
		<div id="cart"><span><%if(carts!=null && carts.size()!=0) %><%=carts.size() %></span><a href="/LoremIpsum/shop/create"><img src="/LoremIpsum/image/cart.png"></a></div>
	
	
		<nav>		
				<!-- 依照登入狀態與會員權限顯示導覽列內容選項 -->
				<%if(m==null){%>
					<a class="link" id="shopping" href="/LoremIpsum/shop/home">購物</a> 				
					<a class="link" id="register" href="/LoremIpsum/member/create">註冊</a> 	
					<a class="link" id="contact" href="/LoremIpsum/contact.jsp">聯繫我們</a>			
				<%}else{ %>
					<a class="link" id="shopping" href="/LoremIpsum/shop/home">購物</a>
					<a class="link" id="memberShop" href="/LoremIpsum/memberShop/home">購物管理</a>
					<a class="link" id="contact" href="/LoremIpsum/contact.jsp">聯繫我們</a>	
					<%if(m.getType().equals("管理員")){ %>					
						<a class="link" id="memberSystem" href="/LoremIpsum/adminMember/home">會員系統</a>					
						<a class="link" id="companySystem" href="/LoremIpsum/adminCompany/home">廠商系統</a>	
						<a class="link" id="productSystem" href="/LoremIpsum/adminProduct/home">產品系統</a>	
						<a class="link" id="purchaseSystem" href="/LoremIpsum/adminPurchase/home">進貨系統</a>	
						<a class="link" id="accountingSystem" href="/LoremIpsum/adminAccounting/home">會計系統</a>				
						<a class="link" id="shoppingSystem" href="/LoremIpsum/adminShop/home">購物系統</a>
					<%} %>
				<%} %>			
				
				<!-- 登入登出會員 -->
				<div id="account">
					<%if(m==null){%>
						<a class="link" id="login" href="/LoremIpsum/member/logIn">登入</a>
					<%}else{ %>
						<a class="link" href="/LoremIpsum/member/logOut">登出</a>
					<%} %>	
				</div>				
		</nav>
	</header>
</body>
</html>