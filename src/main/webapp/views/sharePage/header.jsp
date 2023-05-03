<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="header"
	style="position: fixed; right: 0; left: 0; top: 0; background: #1f1d2b; z-index: 99">
	<div class="search-bar">

		<form action="search" method="post" style="width: 100%">
			<input type="text" value="${txtSearch}" name="txt"
				placeholder="Search">
			<button type="submit"
				style="padding: 10px 20px; display: flex; align-items: center;
	/* background: #149da9; */ justify-content: center; cursor: pointer; border-radius: 5px; position: relative; right: -400px; display: inle; top: -96%; border: none; background: none;">
				<i style="color: black;" class='bx bx-search-alt-2'></i>
			</button>
		</form>
	</div>
	<div class="user-settings">


		<c:if test="${sessionScope.acc == null}">
			<a href="login"
				style="padding: 10px 20px; cursor: pointer; background: var(- -button-bg); color: white; border: none; border-radius: 7px;">Đăng
				nhập</a>
			<a href="signUp"
				style="padding: 10px 20px; margin-left: 10px; cursor: pointer; background: #32a7e2; color: white; border: none; border-radius: 7px;">Đăng
				ký</a>
		</c:if>

		<c:if test="${sessionScope.acc != null}">
			<a href="logout"
				style="padding: 10px 20px; margin-left: 10px; cursor: pointer; background: #ff7551; color: white; border: none; border-radius: 7px;">Đăng
				xuất</a>
		</c:if>
	</div>
</div>