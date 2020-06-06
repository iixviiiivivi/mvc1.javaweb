$(function() {
	$("button").click(function(e) {
		if ($("#cartsSize").text() == "null") {
			return;
		} else {
			$("form").submit(function(e) {
				e.preventDefault();
				var mailInfo = $("form").serialize();
				$.post("/LoremIpsum/order/create", mailInfo, function(data) {
					$("body").html(data);
				}).done(function() {
					alert("購物成功")
				}).fail(function() {
					alert("購物失敗");
				});
			});
		}
	});
});

function cancelOrder(pid, size) {
	$.get("/LoremIpsum/shop/cancel?pid=" + pid + "&size=" + size,
			function(data) {
				$("body").html(data);
			}).done(function() {
		alert("ok");
	}).fail(function() {
		alert("fail");
	});
}
