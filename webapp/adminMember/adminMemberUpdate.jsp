<%@page import="model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member user = (Member)session.getAttribute("user");
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>會員系統:更新會員</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminMember/adminMemberUpdate.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>
			<h3>更新會員</h3>
			<form action="/LoremIpsum/adminMember/update" method="post">
				<table>
					<tr>									
						<input type="hidden" name="memberID" value=<%=user.getMemberID() %> >					
					</tr>
					<tr>
						<td>帳號:</td>
						<td><input type="text" name="username" value=<%=user.getUsername() %> /></td>
					</tr>
					<tr>
						<td>密碼:</td>
						<td><input type="text" name="password" value=<%=user.getPassword() %> /></td>
					</tr>
					<tr>
						<td>姓:</td>
						<td><input type="text" name="firstName" value=<%=user.getFirstName() %> /></td>
					</tr>
					<tr>
						<td>名:</td>
						<td><input type="text" name="lastName" value=<%=user.getLastName() %> /></td>
					</tr>
					<tr>
						<td>性別:</td>
						<td>
							<input type="radio" name="sex" value="male" <%if(user.getSex().equals("male")){ %>checked<%} %>>男
							<input type="radio" name="sex" value="female" <%if(user.getSex().equals("female")){ %>checked<%} %>>女
						</td>
					</tr>
					<tr>
						<td>生日:</td>
						<td><input type="date" name="birthday" value=<%=user.getBirthday() %> /></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input type="text" name="email" value=<%=user.getEmail() %> /></td>
					</tr>
					<tr>
						<td>手機:</td>
						<td><input type="text" name="mobile" value=<%=user.getMobile() %> /></td>
					</tr>
					<tr>
						<td>國家:</td>
						<td><input type="text" name="country" value=<%=user.getCountry() %> /></td>
					</tr>
					<tr>
						<td>城市:</td>
						<td><input type="text" name="city" value=<%=user.getCity() %> /></td>
					</tr>
					<tr>
						<td>區:</td>
						<td><input type="text" name="district" value=<%=user.getDistrict() %> /></td>
					</tr>
					<tr>
						<td>地址:</td>
						<td><input type="text" name="address" value=<%=user.getAddress() %> /></td>
					</tr>
					<tr>
						<td colspan="2">
							是否願意收到最新活動與消息:
							<input type="radio" name="promotion" value="yes" <%if(user.getPromotion().equals("yes")){ %>checked<%} %>>是
							<input type="radio" name="promotion" value="no" <%if(user.getPromotion().equals("no")){ %>checked<%} %>>否
						</td>
					</tr>					
					<tr>
						<td colspan="2">
							用戶權限:
							<input type="radio" name="type" value="管理員" <%if(user.getType().equals("管理員")){ %>checked<%} %>>管理員
							<input type="radio" name="type" value="用戶" <%if(user.getType().equals("用戶")){ %>checked<%} %>>用戶
						</td>				
					</tr>
					<tr>
						<td>註冊日期:</td>
						<td><input type="date" name="registerDate" value=<%=user.getRegisterDate() %> /></td>
					</tr>
					<tr>
						<td>用戶狀態:</td>
						<td><input type="text" name="memberStatus" value=<%=user.getMemberStatus() %> /></td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit">更新會員</button>
						</td>
					</tr>
				</table>
			</form>		
		</main>
	
		<jsp:include page="../footer.jsp" />
		
	</div>	
</body>
</html>