$(document).ready(function(){
	$("button").attr('disabled', true);
	
	$("#pass2, #pass1").change(function(){
		var pass1 = $("#pass1").val();
		var pass2 = $("#pass2").val();
		let pass = /^.+$/i;
		
		alert(pass.test(pass1));
		alert(pass.test(pass2));
		
		if(pass.test(pass1) && pass.test(pass2)){
			$("#passmsg").text("");				
		} else{
			$("#passmsg").text("pass can't be blank");	
		}
								
		if(pass1==pass2){
			$("#error").show();
			$("#error").html("<div style='color:blue'>password equal</div>");
			$("button").attr('disabled', false);
			$("button").addClass("button");
		}else{
			$("#error").show();
			$("#error").html("<div style='color:red'>password unequal</div>");		
			$("button").attr('disabled', true);				
		}					
	});
	
	$("button").click(function(){
		return confirm("Confirm to change password?");
	});
	
})
