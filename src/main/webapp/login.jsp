<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

<!--
  Phân tích theo cách 2: Có sẵn giao diện bên design đã thiết kế
  -B1: Đi qua từng màn hình của design 
  -B2: Tìm đối tượng trong màn hình đó 
  -B3: TÌm thuộc tính của đối tượng trong màn hình 
  -B4: Nếu trong một màn hình mà tìm được từ 2 đối tượng trở lên thì phải xác định mối quan hệ của đối tượng đó trong màn hình 
  -B5: Hình dung câu truy vấn để đáp ứng các chức năng trong màn hình. 
-->

<%
request.getAttribute("ms");
request.getAttribute("email");
request.getAttribute("password");
%>


<body>

	<div class="container">
		<div class="row mt-5">
			<div class="col-md-5 m-auto mt-5">
				<h3 class="text-center">ĐĂNG NHẬP HỆ THỐNG</h3>
				<div class="p-4 border mt-4">
					<form action="login" method="post">
						<div class="form-group">
							<label>Email</label> <input type="email" class="form-control"
								name="email" value="${email}">
						</div>
						<div class="form-group">
							<label>Mật khẩu</label> <input type="password"
								class="form-control" name="password" value="${password}">
						</div>
						<div class="form-group">
							<input type="checkbox" name="remember"> Lưu mật khẩu
						</div>
						<button type="submit" class="btn btn-primary">Đăng nhập</button>
						<c:if test="${ms != null}">
							<br />
							<br />
							<h5 style="color: red">${ms}</h5>
						</c:if>
					</form>
				</div>
			</div>
		</div>


	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
