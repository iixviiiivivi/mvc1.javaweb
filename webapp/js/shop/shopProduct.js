$(function() {
	var priceBefore;
	var priceAfter;
	var quantity;

	// 選擇尺寸，顯示被選中的邊框效果
	$(".sizes").click(function() {
		var id = $(this).attr("id");
		$(".sizes").css({
			"border" : "none",
			"color" : "white"
		});
		$("#" + id).css({
			"border" : "2px solid black",
			"color" : "black"
		});
		$("#size").text($("#" + id).text());
	});

	// 尺寸變更，變更價格
	$(".sizes").click(
			function() {
				var id = $("#id").text();
				var size = $("#size").text();
				$.post(
						"/LoremIpsum/shop/price?id=" + id + "&size=" + size,
						function(data) {
							var obj = JSON.parse(data);
							priceBefore = obj.price * obj.tax;
							priceAfter = obj.price * obj.tax * obj.discount;
							quantity = $("select").val();
							$("#priceBefore").text(priceBefore.toFixed(0));
							$("#priceAfter").text(priceAfter.toFixed(0));
							$("#total")
									.text((quantity * priceAfter).toFixed(0));
						}).done(function() {
				}).fail(function() {
					alert("fail");
				})
			});

	// 數量變動，變更價格
	$("#quantity").change(function() {
		quantity = $("select").val();
		$("#total").text((quantity * priceAfter).toFixed(0));
	});

	// 加入購物車
	$("button").click(
			function() {
				var size = $("#size").text();
				var quantity = $("select").val();
				var id = $("#id").text();
				var color = $("#color").text();
				var price = $("#priceAfter").text();

				$.post(
						"/LoremIpsum/shop/cart?id=" + id + "&size=" + size
								+ "&color=" + color + "&quantity=" + quantity
								+ "&price=" + price, function(data) {
							$("body").html(data);
						}).done(function() {
					alert("加入購物車");
				}).fail(function() {
					alert("加入購物車失敗");
				})
			});
})
