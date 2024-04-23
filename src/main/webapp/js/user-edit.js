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
		var id_user = $(this).attr("id-user");

		// Lấy giá trị của trường input có name là "firstname"
		var firstName = $("input[name='firstname']").val();
		// Lấy giá trị của trường input có name là "lastname"
		var lastName = $("input[name='lastname']").val();
		// Lấy giá trị của trường input có name là "fullname"
		var fullName = $("input[name='fullname']").val();
		// lấy giá trị của trường input có name là email
		var email = $("input[name='email']").val();
		// lấy giá trị của trường input có name là password
		var password = $("input[name='password']").val();
		// lấy giá trị của trường input có name là phone
		var phone = $("input[name='phone']").val();
		// Lấy giá trị của trường input có name là idRole
		var idRole = $("select[name='idRole']").val();


		// Gọi API thông qua AJAX
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/crmapp05/api/user-edit",
			data: { id_user: id_user, firstName: firstName, lastName: lastName, fullName: fullName, email: email, password: password, phone: phone, idRole: idRole },
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
				$(".add-information").append("<h3 style='color:green'>Cập nhật user thành công</h3>");
			} else {
				$(".add-information").empty();
				$(".add-information").append("<h3 style='color:red'>"+ result.message+"</h3>");
			}
		})

			;
	});
});