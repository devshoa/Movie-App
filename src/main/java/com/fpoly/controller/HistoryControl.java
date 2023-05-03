package com.fpoly.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fpoly.dao.DAO_History;
import com.fpoly.entity.Account;
import com.fpoly.entity.History;
import com.fpoly.utils.SessionUtils;

@WebServlet("/history")
public class HistoryControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO_History dao_History;
	private List<History> histories;
	
	@Override
	public void init() throws ServletException {
		dao_History = new DAO_History();
		histories = new ArrayList<>();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Kiểm tra đăng nhập trong sessition
		Account account = (Account) SessionUtils.get(request, "acc");

		if (account == null) {
			request.setAttribute("a", "Vui lòng đăng nhập trước khi xem lịch sử");
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
		} else {
			histories = dao_History.getAllHistoryByAuthorID(account.getAuthorID());
			System.out.println("History: " + histories);
			if(histories == null) {
				request.setAttribute("mss", "Không có video nào đã xem!");
			}
			request.setAttribute("historys", histories);
			request.getRequestDispatcher("views/history.jsp").forward(request, response);
			System.out.println(histories);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
