<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
<!-- Boxicons CSS -->
<link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css'
	rel='stylesheet'>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<!-- Signup Form -->
	<section class="container_lg forms" style="background: #1f1d2b;">
		<div class="form signup show-signup">
			<div class="form-content show-signup">
				<header>Đăng ký</header>
				<c:if test="${mess != null}">
					<div class="alert alert-danger" role="alert">${mess}</div>
				</c:if>
				<form action="signUp" class="show-signup" method="post">
					<div class="field input-field" style="margin-bottom: 40px;">
						<input name="username" type="text" placeholder="Tên đăng nhập"
							value="${username}" class="input"> <span
							style="color: red; font-size: 12px;">${errUser}</span>
					</div>

					<div class="field input-field" style="margin-bottom: 40px;">
						<input name="password" type="password" placeholder="Tạo mật khẩu"
							value="${password}" class="password"> <span
							style="color: red; font-size: 12px;">${errPass}</span>
					</div>

					<div class="field input-field" style="margin-bottom: 40px;">
						<input name="password1" type="password" value="${cofrimPassword}"
							placeholder="Xác nhận mật khẩu" class="password"> <i
							class='bx bx-hide eye-icon'></i> <span
							style="color: red; font-size: 12px;">${errPass1}</span>
					</div>

					<div class="field button-field">
						<button type="submit">Signup</button>
					</div>
				</form>

				<div class="form-link">
					<span>Bạn đã có tài khoản? <a href="login"
						class="link login-link">Đăng nhập</a></span>
				</div>
			</div>
		</div>
	</section>

	<script type="text/javascript" src="js/login.js"></script>
</body>
</html>