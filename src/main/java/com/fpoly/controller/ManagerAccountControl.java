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

import com.fpoly.dao.DAO_Account;
import com.fpoly.entity.Account;
import com.fpoly.utils.SessionUtils;

@WebServlet("/managerAccount")
public class ManagerAccountControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Account> accounts;
	private DAO_Account dao_Account;
	private Account account;
	
	@Override
	public void init() throws ServletException {
		accounts = new ArrayList<>();
		dao_Account = new DAO_Account();
		account = new Account();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// kiểm tra login
		account = (Account) SessionUtils.get(request, "acc");
		
		if(account == null) {
			request.setAttribute("a", "Vui lòng đăng nhập để sử dụng chức năng này");
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
			return;
		}
		 
		
		// kiểm tra xem người dùng có phải là admin hay không
		if(account.isRole() == false) {
			System.out.println("Đã vào đây");
			request.setAttribute("mss", "Không thể truy cập");
			request.getRequestDispatcher("views/page-err.jsp").forward(request, response);
		} else {
			accounts = dao_Account.findAll();
			request.setAttribute("accounts", accounts);
			request.getRequestDispatcher("views/manager-account.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
