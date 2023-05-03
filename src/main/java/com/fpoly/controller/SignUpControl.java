package com.fpoly.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.ClosureUtils;

import com.fpoly.dao.DAO_Account;
import com.fpoly.entity.Account;

@WebServlet("/signUp")
public class SignUpControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String errUser;
	private String errPass;
	private String errPass1;
	private Account account;
	private DAO_Account dao_Account;

	@Override
	public void init() throws ServletException {
		errUser = "";
		errPass = "";
		errPass1 = "";
		account = new Account();
		dao_Account = new DAO_Account();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("views/sign-up.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String cofrimPassword = request.getParameter("password1");

		System.out.println("Mật khẩu 1: " + password + "Mật khẩu 2: " + cofrimPassword);

		if (userName == "") {
			errUser = "Vui lòng nhập vào tên đăng nhập";
			request.setAttribute("errUser", errUser);
		}

		if (password == "") {
			errPass = "Vui lòng nhập vào mật khẩu";
			request.setAttribute("errPass", errPass);
		}

		if (cofrimPassword == "") {
			errPass1 = "Vui lòng nhập vào xác nhận mật khẩu";
			request.setAttribute("errPass1", errPass1);
		}

		if (userName == "" || password == "" || cofrimPassword == "") {
			request.getRequestDispatcher("views/sign-up.jsp").forward(request, response);
			return;
		}

		if (!password.equals(cofrimPassword)) {
			errPass1 = "Xác nhận mật khẩu không chính xác";
			request.setAttribute("errPass1", errPass1);
			request.getRequestDispatcher("views/sign-up.jsp").forward(request, response);
			return;
		}

		// tiến hành kiểm tra tài khoản đã tồn tại trong hệ thống hay chưa

		
		
		account = dao_Account.getByUser(userName);
		
		

		System.out.println(account);

		if (account != null) {
			request.setAttribute("mess", "Tên đăng nhập đã được sử dụng!");
			request.getRequestDispatcher("views/sign-up.jsp").forward(request, response);
		} else {
			// tiến hành thêm tài khoản vào db
			// lấy ra ngày h hiện tại
			Date now = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String formattedDate = formatter.format(now);

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			try {
				Date date = dateFormat.parse(formattedDate);
				account = new Account(userName, password, date, date, false);
				System.out.println(account);
				dao_Account.create(account);
				
				request.setAttribute("username", userName);
				request.setAttribute("password", password);
				request.getRequestDispatcher("views/login.jsp").forward(request, response);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

	}

}
