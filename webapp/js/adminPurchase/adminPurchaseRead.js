$(function(){
	$("select").change(function() {
		$.get("/LoremIpsum/adminPurchase/read/?page=" + $("select").val(), function(data) {
			$("body").html(data);
			$("select").val(page);
		});
	});
});

function editPurchase(id){
	$.get("/LoremIpsum/adminPurchase/update?id="+id, function(data){
		$("body").html(data);
		window.location = "http://localhost:8080/LoremIpsum/adminPurchase/adminPurchaseUpdate.jsp";
	});
}

function deletePurchase(id){
	alert("1")
	$.post("/LoremIpsum/adminPurchase/delete?id="+id, function(data){
		alert("2")
		$("body").html(data);
//		window.location = "http://localhost:8080/LoremIpsum/adminPurchase/read";
	});
}
