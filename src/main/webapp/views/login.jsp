<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<!-- Boxicons CSS -->
<link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css'
	rel='stylesheet'>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<section class="container_lg forms" style="background: #1f1d2b;">
		<div class="form login">
			<div class="form-content">
				<header>Đăng nhập</header>
				<c:if test="${a != null}">
					<div class="alert alert-danger" role="alert">${a}</div>
				</c:if>

				<form action="login" method="post">
					<div class="field input-field" style="margin-bottom: 40px;">
						<input name="username" type="text" placeholder="Tên đăng nhập"
							value="${username}" class="input"> <span
							style="color: red; font-size: 12px;">${errUser}</span>
					</div>

					<div class="field input-field" style="margin-bottom: 40px;">
						<input name="password" type="password" placeholder="Mật khẩu"
							value="${password}" class="password"> <i
							class='bx bx-hide eye-icon'></i> <span
							style="color: red; font-size: 12px;">${errPass}</span>
					</div>

					<div class="remenber" style="display: inline;">
						<input type="checkbox" for="i"> <span id="i">Ghi
							nhớ tôi</span>
					</div>

					<a style="position: absolute; right: 32px;" href="#"
						class="forgot-pass">Quên mật khẩu?</a>

					<div class="field button-field">
						<button>Login</button>
					</div>
				</form>

				<div class="form-link">
					<span>Don't have an account? <a href="signUp"
						class="link signup-link">Signup</a></span>
				</div>
			</div>

		</div>
	</section>

	<script type="text/javascript" src="js/login.js"></script>
</body>
</html>