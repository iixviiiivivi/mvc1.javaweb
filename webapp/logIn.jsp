<%@page import="org.springframework.web.context.request.RequestScope"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String error = (String)request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>會員登入</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/logIn.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="/LoremIpsum/js/logIn.js"></script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="header.jsp" />
		
		<main>
			<form action=<%=(error==null?"/LoremIpsum/member/logIn":"logIn") %> method="post">
				<table>
					<tr>
						<td>帳號:</td>
						<td>
							<input type="text" name="username" id="username"/>
							<p>username格式錯誤:2~10字元，不分大小寫，英文加數字</p>
						</td>
					</tr>
					<tr>
						<td>密碼:</td>
						<td>
							<input type="password" name="password" id="password"/><span id="show">顯示</span>
							<p>password格式錯誤:2~10字元，區分大小寫，英文加數字</p>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input id="submit" type="submit" value="登入" />
						</td>
					</tr>
				</table>
			</form>
			<p><a href="">忘記帳號或密碼?</a><br/></p>
			<%if(error!=null){ %>
			<p style="color: red"><%=error %></p>
			<%} %>		
		</main>
		
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>