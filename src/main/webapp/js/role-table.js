/**
 * Xử lý xoá role 
 */
/**
 * Lê công thiên bảo 
 * 16/04/2024 
 */
$(document).ready(function() {
	$('.btn-xoa').click(function() {

		var id_role = $(this).attr("id-role");
		var checkRole = $(this).attr("check-role");
		var This = $(this);
	
		if (checkRole == 1) {
			// Gọi API thông qua AJAX
			$.ajax({
				method: "GET",
				url: "http://localhost:8080/crmapp05/api/delete-role?id_role=" + id_role,
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
					This.closest('tr').remove()
						alert("Bạn đã xoá role thành công")
				} else {
					alert(result.message)
				}
			})
		} else {
			window.location.href = "404.jsp";

		}



	});
});