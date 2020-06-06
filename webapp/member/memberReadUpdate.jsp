<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Member"%>   
<%
	Member m = (Member)session.getAttribute("account");
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>會員資料</title>

	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/member/memberReadUpdate.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js" ></script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>
			<h3>會員資料</h3>
			<form action="/LoremIpsum/member/update" method="post">
				<input type="hidden" name="memberID" value=<%=m.getMemberID() %>>
				<table>				
					<tr>
						<td>帳號:</td>
						<td><input type="text" name="username" id="username" value=<%=m.getUsername() %> disabled/></td>
					</tr>
					<tr>
						<td>密碼:</td>
						<td><a href="/LoremIpsum/member/updatePassword">修改密碼</a></td>
					</tr>
					<tr>
						<td>姓:</td>					
						<td><input type="text" name="lastName" value=<%=m.getLastName() %> /></td>
					</tr>
					<tr>
						<td>名:</td>
						<td><input type="text" name="firstName" value=<%=m.getFirstName() %> /></td>
					</tr>
					<tr>
						<td>性別:</td>
						<td>
							<input type="radio" name="sex" value="male" <%if(m.getSex().equals("male")){ %>checked<%} %> />男
							<input type="radio" name="sex" value="female" <%if(m.getSex().equals("female")){ %>checked<%} %> >女
						</td>
					</tr>
					<tr>
						<td>生日:</td>
						<td><input type="date" name="birthday" value=<%=m.getBirthday() %>  /></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input type="text" name="email" value=<%=m.getEmail() %> /></td>
					</tr>
					<tr>
						<td>手機:</td>
						<td><input type="text" name="mobile" value=<%=m.getMobile() %> /></td>
					</tr>
					<tr>
						<td>國家:</td>
						<td><input type="text" name="country" value=<%=m.getCountry() %> /></td>
					</tr>
					<tr>
						<td>城市:</td>
						<td><input type="text" name="city" value=<%=m.getCity() %> /></td>
					</tr>
					<tr>
						<td>區:</td>
						<td><input type="text" name="district" value=<%=m.getDistrict() %> /></td>
					</tr>
					<tr>
						<td>地址:</td>
						<td><input type="text" name="address" value=<%=m.getAddress() %> /></td>
					</tr>
					<tr>
						<td colspan="2">
							是否願意收到最新活動與消息:
							<input type="radio" name="promotion" value="yes" <%if(m.getPromotion().equals("yes")){ %>checked<%} %> >是
							<input type="radio" name="promotion" value="no" <%if(m.getPromotion().equals("no")){ %>checked<%} %> >否
						</td>
					</tr>				
					<tr>
						<td colspan="2">
							<button type="submit">更新會員資料</button>
						</td>
					</tr>
				</table>
			</form>
		</main>		
	
		<jsp:include page="../footer.jsp" />	
	</div>
</body>
</html>