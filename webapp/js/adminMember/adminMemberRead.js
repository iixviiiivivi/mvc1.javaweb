// 選擇頁數後在body區置入連結取得的會員資料
$(function(){	
	$("select").change(function(){
		var page = $("select").val();			
		$.get("/LoremIpsum/adminMember/read?page=" + $("select").val(), function(data){
			$("body").html(data);
			$("select").val(page);
		});		
	});
});

// 修改會員資料
function editMember(id){	
	$.get("/LoremIpsum/adminMember/update?id="+ id, function(data){
		$("body").html(data);
	});	
}

// 刪除會員
function deleteMember(id){
	var message = "確定刪除會員ID: " + id + "?";
	if(confirm(message)==true){
		$.post("/LoremIpsum/adminMember/delete?id="+ id, function(data){
			$("body").html(data);
			$("select").val(page);
		});		
	}						
}