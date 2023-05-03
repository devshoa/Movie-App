<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý Video</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/manager.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body background="var(--theme-bg)" style="height: 100vh;">
	<div class="container">
		<jsp:include page="sharePage/left.jsp"></jsp:include>
		<div class="wrapper">
			<jsp:include page="sharePage/header.jsp"></jsp:include>

			<div class="container" style="margin: 140px 0 0 0; box-shadow: none;">
				<div class="table-wrapper"
					style="width: 60%; margin-left: 249px; margin-right: 10000px">
					<div class="table-title" style="background: #252936;">
						<div class="row">
							<div class="col-sm-6">
								<h2>
									Quản lý <b>video</b>
								</h2>
							</div>
							<div class="col-sm-6">
								<a href="#addEmployeeModal" class="btn btn-success"
									data-toggle="modal"><i class="material-icons">&#xE147;</i>
									<span>Thêm video mới</span></a> <a href="#deleteEmployeeModal"
									class="btn btn-danger" data-toggle="modal"><i
									class="material-icons">&#xE15C;</i> <span>Xóa</span></a>
							</div>
						</div>
					</div>
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Tên vidao</th>
								<th>Mô tả</th>
								<th>Tác giả</th>
								<th>Hình ảnh</th>
								<th>Video</th>
								<th>Thời lượng</th>
								<th>Lượt xem</th>
								<th>Thích</th>
								<th>Lươt chia sẻ</th>
								<th>Thể loại</th>
								<th>Người đăng</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listVD}" var="o">
								<tr style="border: none;">
									<td><textarea rows="" cols=""
											style="background: no-repeat; border: none;">${o.nameVideo}</textarea>
									</td>
									<td><textarea rows="" cols=""
											style="background: no-repeat; border: none;">${o.description}</textarea>
									</td>
									<td>${o.performer}</td>
									<td><img alt="" src="${o.images}"></td>
									<td><video src="${o.links}"></video></td>
									<td>${o.time}</td>
									<td>${o.views}</td>
									<td>${o.likes}</td>
									<td>${o.shares}</td>
									<td>${o.category.categoryName}</td>
									<td>${o.account.fullname}</td>

									<td><a href="loadVideo?id=${o.videoID}" class="edit"
										data-toggle="modal"><i class="material-icons"
											data-toggle="tooltip" title="Edit">&#xE254;</i></a> <a
										href="delete?id=${o.videoID}" class="delete"
										data-toggle="modal"><i class="material-icons"
											data-toggle="tooltip" title="Delete">&#xE872;</i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="clearfix">
						<div class="hint-text">
						</div>
						<ul class="pagination">
							<c:forEach begin="0" end="${endP}" var="i">
								<li class="page-item ${tag ==i ? "active" : ""}"><a
									href="managerVideo?index=${i}" class="page-link">${i}</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<!-- Edit Modal HTML -->
			<div id="addEmployeeModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<form action="addVideo" method="post">
							<div class="modal-header">
								<h4 class="modal-title">Thêm video</h4>
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label>Tên video</label> <input name="videoname" type="text"
										class="form-control" required>
								</div>
								<div class="form-group">
									<label>Mô tả</label> <input name="description" type="text"
										class="form-control" required>
								</div>
								<div class="form-group">
									<label>Tác giả</label> <input name="performer" type="text"
										class="form-control" required>
								</div>
								<div class="form-group">
									<label>Hình ảnh</label> <input name="image" type="text"
										class="form-control" required>
								</div>
								<div class="form-group">
									<label>Videos</label> <input name="video" type="text"
										class="form-control" required>
								</div>
								<div class="form-group">
									<label>Thời lượng</label> <input name="time" type="text"
										class="form-control" required>
								</div>
								<div class="input-field">
									<label>Thể loại</label> <select name="category"
										class="form-select" aria-label="Default select example">
										<c:forEach items="${categories}" var="o">
											<option value="${o.categoryID}">${o.categoryName}</option>
										</c:forEach>
									</select>
								</div>

							</div>
							<div class="modal-footer">
								<input type="button" class="btn btn-default"
									data-dismiss="modal" value="Hủy"> <input type="submit"
									class="btn btn-success" value="Thêm">
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- Delete Modal HTML -->
			<div id="deleteEmployeeModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<form>
							<div class="modal-header">
								<h4 class="modal-title">Xóa người dùng</h4>
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
							</div>
							<div class="modal-body">
								<p>Bạn có chắc chắn muốn xóa các người dùng này này không?</p>
								<p class="text-warning">
									<small>Hành động này không thể được hoàn tác.</small>
								</p>
							</div>
							<div class="modal-footer">
								<input type="button" class="btn btn-default"
									data-dismiss="modal" value="Hủy"> <input type="submit"
									class="btn btn-danger" value="Xóa">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>


		<div id="videos">

			<c:forEach items="${listVideos}" var="o" varStatus="status">
				<div class="video anim" style="-delay: .4s">
					<a href="watch?video=${o.videoID}&cid=${o.category.categoryID}">
						<div class="video-time">${o.time}</div>
						<div class="video-wrapper">
							<img class="image"
								style="position: absolute; width: 100%; height: 100%;" alt=""
								src="${o.images }">
							<video muted="${o.links}">
								<source src="${o.links}" type="video/mp4">
							</video>
							<div class="author-img__wrapper video-author">
								<svg viewBox="0 0 24 24" fill="none" stroke="currentColor"
									stroke-width="3" stroke-linecap="round" stroke-linejoin="round"
									class="feather feather-check">
                                        <path d="M20 6L9 17l-5-5" />
                                    </svg>
								<img class="author-img" src="${o.account.avartar}" />
							</div>
						</div>
						<div class="video-by">${o.account.fullname }</div>
						<div class="video-name">${o.nameVideo}</div>
						<div class="video-view">${o.views}
							lượt xem <span class="seperate video-seperate"></span>
							<!-- Thời gian đăng video -->
							${o.dateCreated}
						</div>

					</a>
				</div>
			</c:forEach>

		</div>

		<script type="text/javascript" src="js/manager.js"></script>
</body>
</html>