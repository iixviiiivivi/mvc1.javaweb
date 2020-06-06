<%@page import="model.ImageBase64"%>
<%@page import="model.ProductDetail"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<ProductDetail> productDetails = (List<ProductDetail>)session.getAttribute("productDetails");
	int totalPages = (int)session.getAttribute("totalPages");
	int pdpage = (int)session.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>購物中心</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<style type="text/css">
		#shopping{
			color:black;
			background-color:#F5F5F7;	
		}
		
		main{
			margin-bottom:60px;
		}		
		
		.grid-container{
			display:grid;
			grid-template-columns:repeat(auto-fit, minmax(300px, 1fr));
			justify-items:center;
			grid-gap:5px;
		}
		
		.grid-container h3{
			margin-bottom:15px;
			grid-column:1/-1;
			justify-self:start;
		}	
		
		.grid-item{
			display:grid;
			justify-items:center;
		}
		
		.grid-item img{
			width:100%;
		}
		
		.grid-item p{
			padding:10px;
		}	
		
		#page{
			text-align:center;
		}
	</style>
	
	<script type="text/javascript">
		$(function(){
			// 分頁
			$("select").change(function(){	
				$.get("/LoremIpsum/shop/home/?page="+ $("select").val(), function(data){
					$("body").html(data);
					$("select").val($("select").val());
				});
			});	
		});
	</script>
</head>

<body>
<div id="wrapper">
	<jsp:include page="../header.jsp" />
	
	<jsp:include page="../footer.jsp" />
	
	<main>
		<div class="grid-container">
			<h3>購物中心</h3>	
			<%for(ProductDetail pd: productDetails){ %>
			<div class="grid-item">
				<a href="/LoremIpsum/shop/product?id=<%=pd.getProductDetailID() %>"><img src=<%=ImageBase64.bytesArrayToBase64(pd.getImage()) %> /></a>
				<p><%=pd.getProduct().getProductName() %></p>
			</div>
			<%} %>
		</div>
		
		<div id="page">
			第<select>
				<%for(int i=1;i<=totalPages;i++){ %>
					<option value=<%=i %> <%if(pdpage==i){ %>selected<%} %> >
						<%= i%>
					</option>
				<%} %>
			</select>頁
		</div>
	</main>

		
</div>	
</body>
</html>