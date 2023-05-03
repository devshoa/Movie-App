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

import com.fpoly.dao.DAO_Account;
import com.fpoly.entity.Account;
import com.fpoly.utils.DateUtils;

@WebServlet("/addAccount")
public class AddAccountControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Account account;
	private DAO_Account dao_Account;
	private SimpleDateFormat df;

	@Override
	public void init() throws ServletException {
		account = new Account();
		dao_Account = new DAO_Account();
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// Lấy ra id của account
		String accID = request.getParameter("id");

		// Get form
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullname");
		String image = request.getParameter("image");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phonenumber");
		boolean role = Boolean.parseBoolean(request.getParameter("role"));
		
		// ngày tạo
		String c = DateUtils.getDateNow();
		Date createAt = new Date();
		try {
			createAt = df.parse(c);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		account = new Account(0, userName, password, fullName, image, email, phoneNumber, createAt, null, role);
		
		dao_Account.create(account);
		response.sendRedirect("managerAccount");
	}

}
