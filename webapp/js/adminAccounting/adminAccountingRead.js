$(function() {
	$("select").change(function() {
		$.get("/LoremIpsum/adminAccounting/read/?page="
				+ $("select").val(), function(data) {
			$("body").html(data);
			$("select").val(page);
		});
	});
});

function editAccounting(id){
	$.get("/LoremIpsum/adminAccounting/update?id="+id, function(data){
		$("body").html(data);
		window.location = "http://localhost:8080/LoremIpsum/adminAccounting/adminAccountingUpdate.jsp"
	});
}

function deleteAccounting(id){
	var message = "確定刪除會計編號: "+id;
	if(confirm(message)==true){
		$.post("/LoremIpsum/adminAccounting/delete?id="+id, function(data){
			$("body").html(data);
		})
		.done(function(){ alert("刪除會計資料成功"); })
		.fail(function(){ alert("刪除會計資料失敗"); });
	}
}
