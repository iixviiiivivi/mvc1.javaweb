function editProduct(id){
	$.get("/LoremIpsum/adminProduct/update?id=" + id, function(data){
		$("body").html(data);
		window.location = "http://localhost:8080/LoremIpsum/adminProduct/adminProductUpdate.jsp"
	})
}
