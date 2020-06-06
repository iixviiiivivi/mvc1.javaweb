<%@page import="model.Company"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Company> companies = (List<Company>)session.getAttribute("companies");
	int totalPage = (int)session.getAttribute("totalPage");
	int companyPage = 1;
	if(session.getAttribute("page")!=null){
		companyPage = (int)session.getAttribute("page");
	}		
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>廠商系統:廠商管理</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminCompany/adminCompanyRead.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="/LoremIpsum/js/adminCompany/adminCompanyRead.js"></script>	
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>
			<h3>廠商管理</h3>
			<table>
				<tr>
					<th>ID</th>
					<th>廠商名稱</th>
					<th>聯繫人</th>
					<th>Email</th>
					<th>手機</th>
					<th>城市</th>
					<th>行政區</th>
					<th>地址</th>
					<th>廠商狀態</th>
					<th>更新時間</th>
					<th colspan="2">管理</th>
				</tr>
				<%for(Company c:companies){ %>
					<tr>
						<td><%=c.getCompanyID() %></td>
						<td><%=c.getCompanyName() %></td>
						<td><%=c.getContactName() %></td>
						<td><%=c.getEmail() %></td>
						<td><%=c.getMobile() %></td>
						<td><%=c.getCity() %></td>
						<td><%=c.getDistrict() %></td>
						<td><%=c.getAddress() %></td>
						<td><%=c.getCompanyStatus() %></td>
						<td><%=c.getUpdateDate() %></td>
						<td>
							<button onclick="editCompany(<%=c.getCompanyID()%>)">修改</button>
							<button onclick="deleteCompany(<%=c.getCompanyID()%>)">刪除</button>
						</td>
					</tr>
				<%} %>
			</table>
			<br/>
			<p>
				第<select>
					<%for(int i=1;i<=totalPage;i++){ %>
						<option value=<%=i %> <%if(i==companyPage){ %>selected<%} %>><%=i %></option>
					<%} %>
				</select>頁
			</p>
		</main>
	
		<jsp:include page="../footer.jsp" />	
	</div>
</body>

</html>