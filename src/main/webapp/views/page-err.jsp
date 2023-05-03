<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lỗi</title>

<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>


<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
	<div class="wrapper_op">
		<header>
			<i class="bx bx-cookie"></i>
			<h2 style="color: red;">${mss}</h2>
		</header>

		<div class="data">
			<p>
				Quay lại trang chủ?
			</p>
		</div>

		<div class="buttons">
			<a class="button" href="home" id="acceptBtn" style="text-align: center;">Chấp nhận</a>
			<a class="button" href="javascript:void(0)" id="declineBtn" style="text-align: center;">Cancel</a>
		
		</div>
	</div>

	<script type="text/javascript" src="js/alert.js"></script>
</body>
</html>