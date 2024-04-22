
/**
 * Lê công thiên bảo 
 * 17/04/2024 
 */
$(document).ready(function() {
    $('.btn-xoa').click(function() {
       

 	// Lấy giá trị của trường input có id là "id-task"
        var id_project = $(this).attr("id-project");
        var currentRole = $(this).attr("current-role");
       	This = $(this);
   
       if (currentRole == 1 || currentRole == 2){
		    // Gọi API thông qua AJAX
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/crmapp05/api/delete-project?id_project="+ id_project,
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
				This.closest('tr').remove()
			}else{
				alert(result.message)
			}
		});
	   }else{
		  window.location.href = "404.jsp";
	   }

       
    });
});