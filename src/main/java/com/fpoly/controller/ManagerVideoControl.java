package com.fpoly.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.dao.DAO_Category;
import com.fpoly.dao.DAO_Video;
import com.fpoly.entity.Account;
import com.fpoly.entity.Category;
import com.fpoly.entity.Video;
import com.fpoly.utils.SessionUtils;

@WebServlet("/managerVideo")
public class ManagerVideoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Account account;
	private List<Video> list;	
	private DAO_Video dao_Video;
	private List<Category> categories;
	private DAO_Category dao_Category;
	
	@Override
	public void init() throws ServletException {
		account = new Account();
		list = new ArrayList<>();
		dao_Video = new DAO_Video();
		categories = new ArrayList<>();
		dao_Category = new DAO_Category();
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
			
			// get category
			categories = dao_Category.findAll();
			
			// Bước 1: get số lượng Video
			int count = dao_Video.countAccounts();
			System.out.println("số lượng: " + count);
			
			
			// Trang cuối cùng nếu mỗi trang 3 bài thì chia 3
			int endPage = count / 3;
			
			
			// kiểm tra đk xem endPage có là số lẽ không
			if (count % 3 != 0) {
				endPage += 1;
			}
			
			String i = request.getParameter("index");
			if (i == null) {
				i = "0";
			}

			int index = Integer.parseInt(i);
			
			list = dao_Video.pagingAccount(index);

			
			request.setAttribute("categories", categories);
			request.setAttribute("endP", endPage);
			request.setAttribute("tag", index);
			request.setAttribute("listVD", list);
			request.getRequestDispatcher("views/manager-video.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
