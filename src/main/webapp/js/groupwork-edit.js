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
		

		// Gọi API thông qua AJAX
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/crmapp05/api/groupwork-edit",
			data: {id_project:id_project,name:name,start_date:start_date,end_date:end_date},
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
				alert("Cập nhật project thành công")
			}else{
				alert(result.message)
			}
		})
        
        ;
	});
});