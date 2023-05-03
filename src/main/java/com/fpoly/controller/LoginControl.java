package com.fpoly.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fpoly.dao.DAO_Account;
import com.fpoly.entity.Account;
import com.fpoly.utils.SessionUtils;

import antlr.CharQueue;

@WebServlet("/login")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String errUser;
	private String errPass;
	// Entity Account
	private Account account;
	// DAO
	private DAO_Account dao_Account;

	@Override
	public void init() throws ServletException {
		errUser = "";
		errPass = "";
		account = new Account();
		dao_Account = new DAO_Account();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("views/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username == "") {
			errUser = "Vui lòng nhập vào tên đăng nhập";
			request.setAttribute("errUser", errUser);
		}
		if (password == "") {
			errPass = "Vui lòng nhập vào mật khẩu";
			request.setAttribute("errPass", errPass);
		}
		if (username == "" || password == "") {
			doGet(request, response);
			return;
		}

		// tiến hành get Data trong db
		account = dao_Account.login(username, password);

		if (account == null) {
			request.setAttribute("a", "Tên đăng nhập và mật khẩu không chính xác");
			doGet(request, response);
		} else {
			// Tiến hành lưu account vào session
			SessionUtils.add(request, "acc", account);
			// set thời gian tồn tại của session
//			session.setMaxInactiveInterval(10);

			response.sendRedirect("home");
		}

		System.out.println(account);

		System.out.println(username + " " + password);
	}

}
