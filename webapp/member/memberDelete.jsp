<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="BIG5">
	<title>會員:刪除會員</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	
	<style type="text/css">
	h3{
		margin-bottom:15px;
	}
	
	table{
		border-collapse:collapse;
	}
	
	table, tr, td{
		padding:3px 5px;
		border: 1px solid black;
	}
</style>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="../header.jsp" />
		
		<main>
			<h3>刪除會員</h3>
			<form action="/LoremIpsum/member/delete" method="post">
				<table border="1" cellpadding="5" cellspacing="0">
					<tr>
						<td>密碼:</td>
						<td><input type="text" name="password" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="刪除帳號" /></td>
					<tr>
				</table>			
			</form>
		</main>
	
		<jsp:include page="../footer.jsp" />	
	</div>
</body>
</html>