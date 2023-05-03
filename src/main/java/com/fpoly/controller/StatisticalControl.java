package com.fpoly.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.dao.DAO_Video;
import com.fpoly.entity.Account;
import com.fpoly.entity.Video;
import com.fpoly.utils.SessionUtils;

@WebServlet("/statistical")
public class StatisticalControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Video> list;
	private DAO_Video dao_Video;
	private Account account;
	
	

	@Override
	public void init() throws ServletException {
		list = new ArrayList<>();
		dao_Video = new DAO_Video();
		account = new Account();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// kiểm tra login
		account = (Account) SessionUtils.get(request, "acc");

		if (account == null) {
			request.setAttribute("a", "Vui lòng đăng nhập để sử dụng chức năng này");
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
			return;
		} 

		// kiểm tra xem người dùng có phải là admin hay không
		if (account.isRole() == false) {
			System.out.println("Đã vào đây");
			request.setAttribute("mss", "Không thể truy cập");
			request.getRequestDispatcher("views/page-err.jsp").forward(request, response);
		} else {
			list = dao_Video.getAllOderViews();
			request.setAttribute("listVideos", list);
			request.getRequestDispatcher("views/ThongKe.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
