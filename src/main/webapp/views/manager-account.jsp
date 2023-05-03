<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý người dùng</title>
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
									Quản lý <b>người dùng</b>
								</h2>
							</div>
							<div class="col-sm-6">
								<a href="#addEmployeeModal" class="btn btn-success"
									data-toggle="modal"><i class="material-icons">&#xE147;</i>
									<span>Thêm người dùng mới</span></a> <a href="#deleteEmployeeModal"
									class="btn btn-danger" data-toggle="modal"><i
									class="material-icons">&#xE15C;</i> <span>Xóa</span></a>
							</div>
						</div>
					</div>
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Tên đăng nhập</th>
								<th>Mật khẩu</th>
								<th>Họ và tên</th>
								<th>Hình ảnh</th>
								<th>Email</th>
								<th>Số điện thoại</th>
								<th>Ngày tạo</th>
								<th>Ngày cập nhật</th>
								<th>Phân quyền</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${accounts}" var="o">
								<tr style="border: none;">
									<td>${o.userName}</td>
									<td>${o.password}</td>
									<td>${o.fullname}</td>
									<td><img src="${o.avartar}"></td>
									<td>${o.email}</td>
									<td>${o.phoneNumber}</td>
									<td>${o.createdAt}</td>
									<td>${o.updateAt}</td>
									<th>${o.role ? 'Admin':'User'}</th>
									<td><a href="loadAccount?id=${o.authorID}" class="edit"
										data-toggle="modal"><i class="material-icons"
											data-toggle="tooltip" title="Edit">&#xE254;</i></a> <a
										href="delete?id=${o.authorID}" class="delete"
										data-toggle="modal"><i class="material-icons"
											data-toggle="tooltip" title="Delete">&#xE872;</i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="clearfix">
						<div class="hint-text">
							Showing <b>5</b> out of <b>25</b> entries
						</div>
						<ul class="pagination">
							<li class="page-item disabled"><a href="#">Previous</a></li>
							<li class="page-item"><a href="#" class="page-link">1</a></li>
							<li class="page-item"><a href="#" class="page-link">2</a></li>
							<li class="page-item active"><a href="#" class="page-link">3</a></li>
							<li class="page-item"><a href="#" class="page-link">4</a></li>
							<li class="page-item"><a href="#" class="page-link">5</a></li>
							<li class="page-item"><a href="#" class="page-link">Next</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- Edit Modal HTML -->
			<div id="addEmployeeModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<form action="addAccount" method="post">
							<div class="modal-header">
								<h4 class="modal-title">Thêm người dùng</h4>
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label>Tên đăng nhập</label> <input name="username" type="text"
										class="form-control" required>
								</div>
								<div class="form-group">
									<label>Mật khẩu</label> <input name="password" type="password"
										class="form-control" required>
								</div>
								<div class="form-group">
									<label>Họ và tên</label> <input name="fullname" type="text"
										class="form-control" required>
								</div>
								<div class="form-group">
									<label>Hình ảnh</label> <input name="image" type="text"
										class="form-control" required>
								</div>
								<div class="form-group">
									<label>Email</label> <input name="email" type="text"
										class="form-control" required>
								</div>
								<div class="form-group">
									<label>Số điện thoại</label> <input name="phonenumber" type="text"
										class="form-control" required>
								</div>

								<div class="input-field">
									<label>Phân quyền</label> <select required name="role">
										<option disabled>Chọn phân quyền</option>
										<option value="true">Admin</option>
										<option value="false">User</option>
									</select>
								</div>

							</div>
							<div class="modal-footer">
								<input type="button" class="btn btn-default"
									data-dismiss="modal" value="Hủy"> <input
									type="submit" class="btn btn-success" value="Thêm">
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

		<script type="text/javascript" src="js/manager.js"></script>
</body>
</html>