/**
 * Lê công thiên bảo 
 * 16/04/2024 
 */
$(document).ready(function() {
	$('#form-save').submit(function(event) {
		// Ngăn chặn hành vi mặc định của form
		event.preventDefault();

		// Lấy giá trị của trường input có name là "id_status"
		var idStatus = $("select[name='id_status']").val();

		// Lấy giá trị của trường input có id là "id-task"
		var idTask = $("#form-save").attr("id-task");

		// Gọi API thông qua AJAX
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/crmapp05/api/profile-edit",
			data: { idTask: idTask, idStatus: idStatus },
			success: function(response) {
				// Xử lý khi gọi API thành công
				console.log(response);
			},
			error: function(xhr, status, error) {
				// Xử lý khi gọi API gặp lỗi
				console.log(xhr.responseText);
			}
		}).done(function(result) {
			if (result.data) {
				$(".add-information").empty();
				$(".add-information").append("<h3 style='color:green'>Cập nhật tiến độ công việc thành công</h3>");
			} else {
				$(".add-information").empty();
				$(".add-information").append("<h3 style='color:red'>Có lỗi xảy ra khi cập nhật tiến độ công việc</h3>");
			}
		})

			;
	});
});