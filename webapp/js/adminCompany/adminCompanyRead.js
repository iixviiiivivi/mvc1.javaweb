$(function() {
	$("select").change(function() {
		$.get("/LoremIpsum/adminCompany/read/?page="
				+ $("select").val(), function(data) {
			$("body").html(data);
		});
	});
});

function editCompany(id) {
	$.get("/LoremIpsum/adminCompany/update?id=" + id, function(data) {
		$("body").html(data);
	});
}

function deleteCompany(id) {
	var message = "確定刪除廠商ID: " + id + "?";
	if (confirm(message) == true) {
		$.get("/LoremIpsum/adminCompany/delete?id=" + id, function(data) {
			$("body").html(data);
		});
	}
}