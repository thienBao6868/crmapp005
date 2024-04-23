/**
 * Lê công thiên bảo 
 * 16/04/2024 
 */
$(document).ready(function() {
	$('#form-save').submit(function(event) {
		// Ngăn chặn hành vi mặc định của form
		event.preventDefault();
		// Lấy giá trị của trường input có id là "id-task"
		var id_role = $("#form-save").attr("id-role");
		// Lấy giá trị của trường input có name là "name"
		var name = $("input[name='name']").val();
		// Lấy giá trị của trường input có name là "description"
		var description = $("input[name='description']").val();

		// Gọi API thông qua AJAX
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/crmapp05/api/role-edit",
			data: { id_role: id_role, name: name, description: description },
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
				$(".add-information").append("<h3 style='color:green'>Cập nhật Role thành công</h3>");
			} else {
				$(".add-information").empty();
				$(".add-information").append("<h3 style='color:red'>Cập nhật Role Thất bại</h3>");
			}
		})

			;
	});
});