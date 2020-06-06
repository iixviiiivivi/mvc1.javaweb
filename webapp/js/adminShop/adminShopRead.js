// 切換頁面
$(function(){
	$("select").change(function() {
		$.get("/LoremIpsum/adminShop/readOrders?page="+ $("select").val(), function(data) {
			$("body").html(data);
			$("select").val(page);
		});
	});
});

// 查詢訂單詳細資料
function queryOrders(id){
	$.get("/LoremIpsum/adminShop/readOrderDetails?id="+id, function(data) {
		$("body").html(data);
		window.location = "http://localhost:8080/LoremIpsum/adminShop/adminShopOrderDetails.jsp";
	});
}

// 修改訂單
function editOrders(id){
	$.get("/LoremIpsum/adminShop/updateOrder?id="+id, function(data) {
		$("body").html(data);
		window.location = "http://localhost:8080/LoremIpsum/adminShop/adminShopUpdate.jsp";
	});
}

// 取消訂單
function cancelOrders(id){
	$.get("/LoremIpsum/adminShop/deleteOrder?id="+id, function(data) {
		$("body").html(data);
	});
}
