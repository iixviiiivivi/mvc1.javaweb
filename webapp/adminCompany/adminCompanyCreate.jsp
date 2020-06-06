<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>廠商系統:新增廠商</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminCompany/adminCompanyCreate.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>
			<h3>新增廠商</h3>
			<form action="/LoremIpsum/adminCompany/create" method="post">
				<table>
					<tr>
						<td>廠商名稱:</td>
						<td><input type="text" name="companyName" /></td>
					</tr>
					<tr>
						<td>聯繫人:</td>
						<td><input type="text" name="contactName" /></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input type="text" name="email" /></td>
					</tr>
					<tr>
						<td>手機:</td>
						<td><input type="text" name="mobile" /></td>
					</tr>
					<tr>
						<td>國家:</td>
						<td><input type="text" name="country" /></td>
					</tr>
					<tr>
						<td>城市:</td>
						<td><input type="text" name="city" /></td>
					</tr>
					<tr>
						<td>行政區:</td>
						<td><input type="text" name="district" /></td>
					</tr>
					<tr>
						<td>地址:</td>
						<td><input type="text" name="address" /></td>
					</tr>
					<tr>
						<td>廠商狀態:</td>
						<td>
							<select name="companyStatus">
								<option value="洽談中">洽談中</option>
								<option value="合作中">合作中</option>
								<option value="取消">取消</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2"><button type="submit">新增廠商</button></td>
					</tr>
				</table>
			</form>			
		</main>
	
		<jsp:include page="../footer.jsp" />	
	</div>
</body>
</html>