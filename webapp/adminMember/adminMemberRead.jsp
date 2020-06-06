<%@page import="java.math.BigDecimal"%>
<%@page import="model.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int totalPages = (int)session.getAttribute("totalPages");
	int memberPage = 1;
	List<Member> members = (List<Member>)session.getAttribute("members");
	if(session.getAttribute("page")!=null){
		memberPage = (int)session.getAttribute("page");
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>會員系統:會員管理</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/adminMember/adminMemberRead.css" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="/LoremIpsum/js/adminMember/adminMemberRead.js"></script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>
			<h3>會員管理</h3>
			<table>
				<tr>
					<th>ID</th>
					<th>Username</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Mobile</th>
					<th colspan="2">管理</th>
				</tr>
				<%for(Member m:members){ %>
				<tr>
					<td><%=m.getMemberID() %></td>
					<td><%=m.getUsername() %></td>
					<td><%=m.getFirstName() %></td>
					<td><%=m.getLastName() %></td>
					<td><%=m.getMobile() %></td>
					<td>
						<button onclick="editMember(<%=m.getMemberID()%>)">修改</button>
						<button onclick="deleteMember(<%=m.getMemberID()%>)">刪除</button>
					</td>
				</tr>
				<%} %>
			</table>
			<br/>
			<p>
				第<select>
				<%for(int i=1;i<=totalPages;i++){ %>
					<option value=<%=i %> <%if(memberPage==i){ %>selected<%} %>><%=i %></option>
				<%} %>
			</select>頁</p>		
		</main>
	
		<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>