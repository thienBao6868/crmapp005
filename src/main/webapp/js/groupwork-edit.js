/**
 * Lê công thiên bảo 
 * 16/04/2024 
 */
$(document).ready(function() {
	$('#form-save').submit(function(event) {
		// Ngăn chặn hành vi mặc định của form
		event.preventDefault();
		
		// Lấy giá trị của trường input có id là "id-project"
		var id_project = $("#form-save").attr("id-project");
		// Lấy giá trị của trường input có name là "name"
		var name = $("input[name='tenDuAn']").val();
		// Lấy giá trị của trường input có name là "ngayBatDau"
		var start_date = $("input[name='ngayBatDau']").val();
		// Lấy giá trị của trường input có name là "ngayKetThuc"
		var end_date = $("input[name='ngayKetThuc']").val();
		
		var id_leader = $("select[name='leader']").val();
		

		

		// Gọi API thông qua AJAX
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/crmapp05/api/groupwork-edit",
			data: {id_project:id_project,name:name,start_date:start_date,end_date:end_date,id_leader:id_leader},
			  success: function(response) {
                // Xử lý khi gọi API thành công
                console.log(response);
            },
            error: function(xhr, status, error) {
                // Xử lý khi gọi API gặp lỗi
                console.log(xhr.responseText);
            }
        }).done(function(result){
			if(result.data){
				$(".add-information").empty();
				$(".add-information").append("<h3 style='color:green'>Cập nhật Project thành công</h3>");
			}else{
				$(".add-information").empty();
				$(".add-information").append("<h3 style='color:red'>Cập nhật Project thất bại</h3>");
			}
		})
        
        ;
	});
});