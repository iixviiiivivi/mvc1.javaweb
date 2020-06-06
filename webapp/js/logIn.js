$(function(){
	$("#username").focus();
	
	let username = /^[\w\d]{2,10}$/i;
	let	password = /^[\w\d]{2,8}$/;
	
	$("#username").keyup(function(){
		if(username.test($(this).val()) ){
			$(this).removeClass("invalid");
			$(this).addClass("valid");	
			$("#username+p").hide();
		}else{
			$(this).removeClass("valid");	
			$(this).addClass("invalid");
			$("#username+p").show();
		}
	});
	
	$("#password").keyup(function(){
		if(password.test($(this).val()) ){
			$(this).removeClass("invalid");
			$(this).addClass("valid");	
			$("#password+span+p").hide();
		}else{
			$(this).removeClass("valid");	
			$(this).addClass("invalid");
			$("#password+span+p").show();
		}
	});
	
	let tag = 1;
	$("#show").click(function(){
		if(tag==1){
			$("#password").attr("type", "text");
			$("#show").text("Hide");
			tag = 0;
		} else{
			$("#password").attr("type", "password");
			$("#show").text("Show");
			tag = 1;
		}
	});
	
});
