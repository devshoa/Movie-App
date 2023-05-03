<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Video</title>

<link rel="stylesheet"
	href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>

<link rel="stylesheet" type="text/css" href="css/editAccount.css">


</head>
<body>
	<div class="container" style="max-width: 1258px;">
		<header>Chỉnh sửa thông tin video</header>

		<form action="updateVideo" method="post">
			<input style="display: none;" name="id" value="${video.videoID}">
			<div class="form first">
				<div class="details personal">
					<span class="title">Thông tin video</span>

					<div class="fields">
						<div class="input-field">
							<label>Tên video</label>
							<textarea rows="" cols="" type="text" name="videoname"
								placeholder="Điền vào tên video" required>${video.nameVideo}</textarea>
						</div>

						<div class="input-field" style="position: relative;">
							<label>Mô tả</label>
							<textarea rows="" cols="" name="description" type="text"
								placeholder="Điền vào mô tả" class="password" required>${video.description}</textarea>
						</div>


						<div class="input-field">
							<label>Tác giả</label> <input type="text" name="performer"
								value="${video.performer}" placeholder="Điền vào họ tên"
								required>
						</div>

						<div class="input-field">
							<label>Hình ảnh</label> <input type="text" name="image"
								value="${video.images}" placeholder="Điền vào hình ảnh" required>
						</div>

						<div class="input-field">
							<label>Video</label> <input type="text" value="${video.links}"
								name="video" placeholder="Điền vào video" required>
						</div>

						<div class="input-field">
							<label>Thời lượng video</label> <input type="text" name="time"
								value="${video.time}" placeholder="Điền vào thời lượng" required>
						</div>


						<div class="input-field">
							<label>Thể loại</label> <select name="category"
								class="form-select" aria-label="Default select example">
								<c:forEach items="${category}" var="o">
									<option value="${o.categoryID}">${o.categoryName}</option>
								</c:forEach>
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