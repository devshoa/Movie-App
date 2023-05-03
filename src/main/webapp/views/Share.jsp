<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chia sẻ</title>

<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>


<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
	<div class="wrapper_op">
		<header>
			<i class='bx bx-envelope'></i>
			<h2>${mss}</h2>
		</header>

		<div class="data">
			<form action="./share" method="post">
				<input placeholder="Nhập vào email" name="email" type="email"
					style="width: 100%; position: relative; height: 46px; padding-right: 60px !important; margin-bottom: 10px; border: none; box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px; border-radius: 10px; padding: 0 0 0 10px;">

				<button style="position: absolute; top: 102px; right: 16px;">
					<i
						style="position: absolute; font-size: 20px;
	/* right: 0; */ right: 34px; top: 43%; cursor: pointer;"
						class='bx bx-send'></i>
				</button>

				<p>Quay lại trang chủ?</p>

			</form>
		</div>

		<div class="buttons">
			<a class="button" href="home" id="acceptBtn"
				style="text-align: center;">Chấp nhận</a> <a class="button"
				href="javascript:void(0)" id="declineBtn"
				style="text-align: center;">Cancel</a>

		</div>
	</div>

	<script type="text/javascript" src="js/alert.js"></script>
</body>
</html>