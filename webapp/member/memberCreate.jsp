<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>會員註冊</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/member/memberCreate.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>
			<h3>會員註冊</h3>
			<form action="/LoremIpsum/member/create" method="post" >
				<table>
					<tr>
						<td>帳號:</td>
						<td><input type="text" name="username"/></td>
					</tr>
					<tr>
						<td>密碼:</td>
						<td><input type="text" name="password"/></td>
					</tr>
					<tr>
						<td>姓:</td>
						<td><input type="text" name="firstName"/></td>
					</tr>
					<tr>
						<td>名:</td>
						<td><input type="text" name="lastName"/></td>
					</tr>
					<tr>
						<td>性別:</td>
						<td>
							<input type="radio" name="sex" value="male">男
							<input type="radio" name="sex" value="female">女
						</td>
					</tr>
					<tr>
						<td>生日:</td>
						<td><input type="date" name="birthday"/></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input type="text" name="email"/></td>
					</tr>
					<tr>
						<td>手機:</td>
						<td><input type="text" name="mobile"/></td>
					</tr>
					<tr>
						<td>國家:</td>
						<td><input type="text" name="country"/></td>
					</tr>
					<tr>
						<td>城市:</td>
						<td><input type="text" name="city"/></td>
					</tr>
					<tr>
						<td>區:</td>
						<td><input type="text" name="district"/></td>
					</tr>
					<tr>
						<td>地址:</td>
						<td><input type="text" name="address"/></td>
					</tr>
					<tr>
						<td colspan="2">
							是否願意收到最新活動與消息:
							<input type="radio" name="promotion" value="yes">是
							<input type="radio" name="promotion" value="no">否
						</td>
					</tr>
					<tr>
						<td colspan="2">
							已讀取下述會員及隱私條款並同意:
							<input type="radio" name="agreement" value="yes">同意
							<input type="radio" name="agreement" value="no">不同意
						</td>				
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit" >註冊</button>
						</td>
					</tr>
				</table>
			</form>
		</main>
			
		<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>