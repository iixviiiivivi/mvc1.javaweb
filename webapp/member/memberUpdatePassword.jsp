<%@page import="model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String message = (String)session.getAttribute("message");
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>會員:更改密碼</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/member/memberUpdatePassword.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="/LoremIpsum/js/member/memberUpdatePassword.js"></script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>
			<h3>更改密碼</h3>
			<form action="/LoremIpsum/member/updatePassword" method="post">
				<table>
					<tr>
						<td>舊密碼:</td>
						<td><input type="text" name="password" /></td>
					</tr>
					<tr>
						<td>新密碼:</td>
						<td><input id="pass1" type="text" name="newPassword" /></td>					
					</tr>
					<tr>
						<td>密碼確認:</td>
						<td><input id="pass2" type="text"/></td>
											
					</tr>
					<tr>
						<td colspan="2"><button type="submit">變更密碼</button></td>
					<tr>
				</table>			
			</form>
			<span id="error"></span>
			<%if(message!=null && message.equals("密碼更新成功")){ %>
				<%=message %>
			<%}else if(message!=null && message.equals("密碼更新失敗")){%>
				<span style="color:red"><%=message %></span>
			<%} session.removeAttribute("message");%>
			<p id="passmsg"></p>
		</main>
	
		<jsp:include page="../footer.jsp" />	
	</div>
</body>
</html>