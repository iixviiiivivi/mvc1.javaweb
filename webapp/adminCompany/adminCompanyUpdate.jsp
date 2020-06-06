<%@page import="model.Company"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Company c = (Company)session.getAttribute("company");
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>廠商系統:更新廠商</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminCompany/adminCompanyUpdate.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>
			<h3>更新廠商</h3>
			<form action="/LoremIpsum/adminCompany/update" method="post">
				<table>	
					<tr>									
						<input type="hidden" name="companyID" value=<%=c.getCompanyID() %> >					
					</tr>
					<tr>
						<td>廠商名稱:</td>
						<td><input type="text" name="companyName" value=<%=c.getCompanyName() %> ></td>
					</tr>
					<tr>
						<td>聯繫人:</td>
						<td><input type="text" name="contactName" value=<%=c.getContactName() %> ></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input type="text" name="email" value=<%=c.getEmail() %> ></td>
					</tr>
					<tr>
						<td>手機:</td>
						<td><input type="text" name="mobile" value=<%=c.getMobile() %> ></td>
					</tr>
					<tr>
						<td>國家:</td>
						<td><input type="text" name="country" value=<%=c.getCountry() %> ></td>
					</tr>
					<tr>
						<td>城市:</td>
						<td><input type="text" name="city" value=<%=c.getCity() %> ></td>
					</tr>
					<tr>
						<td>行政區:</td>
						<td><input type="text" name="district" value=<%=c.getDistrict() %> ></td>
					</tr>
					<tr>
						<td>地址:</td>
						<td><input type="text" name="address" value=<%=c.getAddress() %> ></td>
					</tr>
					<tr>
						<td>廠商狀態:</td>
						<td>
							<select name="companyStatus">
								<option value="洽談中" <%if(c.getCompanyStatus().equals("洽談中")){ %>selected<%} %>>洽談中</option>
								<option value="合作中" <%if(c.getCompanyStatus().equals("合作中")){ %>selected<%} %>>合作中</option>
								<option value="取消" <%if(c.getCompanyStatus().equals("取消")){ %>selected<%} %>>取消</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2"><button>更新廠商</button></td>
					</tr>
				</table>
			</form>	
		</main>
	
		<jsp:include page="../footer.jsp" />	
	</div>
</body>
</html>