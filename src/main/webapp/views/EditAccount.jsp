<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Account</title>

<link rel="stylesheet"
	href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>

<link rel="stylesheet" type="text/css" href="css/editAccount.css">


</head>
<body>
	<div class="container">
		<header>Chỉnh sửa người dùng</header>

		<form action="updateAccount" method="post">
			<input style="display: none;" name="id" value="${account.authorID}"> 
			<div class="form first">
				<div class="details personal">
					<span class="title">Thông tin cá nhân</span>

					<div class="fields">
						<div class="input-field">
							<label>Tên đăng nhập</label> <input type="text"
								value="${account.userName}" name="username"
								placeholder="Điền vào tên đăng nhập" required>
						</div>

						<div class="input-field" style="position: relative;">
							<label>Mật khẩu</label> <input name="password" type="password"
								value="${account.password}" placeholder="Điền vào mật khẩu"
								class="password" required> <i
								style="position: absolute; top: 50%; right: 10px; cursor: pointer;"
								class='bx bx-hide eye-icon'></i> <span
								style="color: red; font-size: 12px;">${errPass}</span>
						</div>


						<div class="input-field">
							<label>Họ và tên</label> <input type="text" name="fullname"
								value="${account.fullname}"
								placeholder="Điền vào họ tên" required>
						</div>

						<div class="input-field">
							<label>Hình ảnh</label> <input type="text" name="image"
								value="${account.avartar}" placeholder="Điền vào hình ảnh"
								required>
						</div>

						<div class="input-field">
							<label>Email</label> <input type="email" value="${account.email}"
								name="email" placeholder="Điền vào email" required>
						</div>

						<div class="input-field">
							<label>Số điện thoại</label> <input type="number"
								name="phonenumber" value="${account.phoneNumber}"
								placeholder="Điền vào số điện thoại" required>
						</div>

						<div class="input-field">
							<label>Ngày tạo</label> <input value="${account.createdAt}"
								name="createdat" type="datetime-local" required>
						</div>

						<div class="input-field">
							<label>Ngày cập nhật</label> <input type="datetime-local"
								name="updateAt" value="${account.updateAt}" required>
						</div>
						
						
						<div class="input-field">
							<label>Phân quyền</label> <select required name="role">
								<option disabled>Chọn phân quyền</option>
								<option value="true">Admin</option>
								<option value="false">User</option>
							</select>
						</div>


					</div>
				</div>

				<div class="details ID">
					<button class="nextBtn" type="submit">
						<span class="btnText">Cập nhật</span> <i class="uil uil-navigator"></i>
					</button>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript" src="js/showPass.js"></script>
</body>
</html>