$(function() {
	$("select").change(function() {
		$.get("/LoremIpsum/adminPurchase/readStocks/?page=" + $("select").val(), function(data) {
			$("body").html(data);
			$("select").val(page);
		});
	});
});

function purchaseStock(id, size){
	$.get("/LoremIpsum/adminPurchase/create?id="+id+"&size="+size, function(data){
		$("body").html(data);
		window.location = "http://localhost:8080/LoremIpsum/adminPurchase/adminPurchaseCreate.jsp";
	});
}
