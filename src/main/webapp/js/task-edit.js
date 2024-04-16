/**
 * Xử lý submit form cập nhật lại thông tin User 
 */
/**
 * Lê công thiên bảo 
 * 16/04/2024 
 */
$(document).ready(function() {
	$('#form-save').submit(function(event) {
		// Ngăn chặn hành vi mặc định của form
		event.preventDefault();

		// Lấy giá trị của trường input có id là "id-task"
		var id_task = $(this).attr("id-task");

		// Lấy giá trị của trường input có name là "firstname"
		var id_project = $("select[name='tenDuAn']").val();
		var tenCongViec = $("input[name='tenCongViec']").val();
		var id_user = $("select[name='nguoiThucHien']").val();
		var start_date = $("input[name='ngayBatDau']").val();
		var end_date = $("input[name='ngayKetThuc']").val();
		var id_status = $("select[name='id_status']").val();

		// Gọi API thông qua AJAX
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/crmapp05/api/task-edit",
			data: { id_task: id_task, id_project: id_project, tenCongViec: tenCongViec, id_user: id_user, start_date: start_date, end_date: end_date, id_status: id_status },
			success: function(response) {
				// Xử lý khi gọi API thành công
				console.log(response);
				alert("Dữ liệu đã được lưu thành công!");
			},
			error: function(xhr, status, error) {
				// Xử lý khi gọi API gặp lỗi
				console.log(xhr.responseText);
				alert("Đã xảy ra lỗi, vui lòng thử lại sau!");
			}
		});
	});
});