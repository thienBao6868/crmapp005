
/**
 * Lê công thiên bảo 
 * 17/04/2024 
 */
$(document).ready(function() {
    $('.btn-xoa').click(function() {
       

 	// Lấy giá trị của trường input có id là "id-task"
        var id_task = $(this).attr("id-task");
        var id_role = $(this).attr("id-role");
       	This = $(this);
   
       if(id_role == 1 || id_role == 2){
		    // Gọi API thông qua AJAX
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/crmapp05/api/delete-task?id_task="+ id_task,
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
		})
        
        ;
		   
	   }else{
		   window.location.href = "404.jsp";
	   }

       
    });
});