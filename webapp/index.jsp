<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Lorem Ipsum</title>
	<link rel="stylesheet" type="text/css" href="/LoremIpsum/css/layout.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	<style type="text/css">
		#image1, #image2{
			width:45%;			
		}
		
		#img{
			text-align:center;
		}	
	</style>
	
	<script type="text/javascript">
		var id1 = 2;
		var id2 = 3;
		
		function autoPlay(selector, src, id, time){
			time = setInterval(function(){
				$(selector).attr("src", src + id +'.jpg');
				id++;
				if(id==4) id=1;
			}, time);
		}
			
		$(function(){
			$("#image1").attr("src",'/LoremIpsum/image/a1.jpg');
			$("#image2").attr("src",'/LoremIpsum/image/b2.jpg');
			autoPlay("#image1", '/LoremIpsum/image/a', id1, 1500);	
			autoPlay("#image2", '/LoremIpsum/image/b', id2, 2000);		
		});
	</script>
</head>

<body>
	<div id="wrapper">
		<jsp:include page="header.jsp" />
		
		<main>			
			<div id="img">
				<img id="image1" alt="" src="">
				<img id="image2" alt="" src="">
			</div>		
		</main>
	
		<jsp:include page="footer.jsp" />
	</div>	
</body>

</html>
