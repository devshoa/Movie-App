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

@WebServlet("/updateAccount")
public class UpdateAccountControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Account account;
	private DAO_Account dao_Account;
	private SimpleDateFormat simpleDateFormat;
	
	@Override
	public void init() throws ServletException {
		account = new Account();
		dao_Account = new DAO_Account();
		simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
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
		account = dao_Account.findByID(Integer.parseInt(accID));
		
		// Get form
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullname");
		String image = request.getParameter("image");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phonenumber");
		String create =   request.getParameter("createdat");
		Date dateCreate = new Date();
		try {
			dateCreate = simpleDateFormat.parse(create);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String updateAt = request.getParameter("updateAt");
		Date update = new Date();
		try {
			update = simpleDateFormat.parse(updateAt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Boolean role = Boolean.parseBoolean(request.getParameter("role"));
		
		account = new Account(Integer.parseInt(accID), userName, password, fullName, image, email, phoneNumber, dateCreate, update, role);
		
		dao_Account.update(account);
		response.sendRedirect("managerAccount");
		
	}

}
