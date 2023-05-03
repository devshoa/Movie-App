package com.fpoly.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.dao.DAO_Account;
import com.fpoly.dao.DAO_Category;
import com.fpoly.entity.Account;
import com.fpoly.entity.Category;

@WebServlet("/loadAccount")
public class LoadAccountControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Account account;
	private DAO_Account dao_Account;
	// List Category
	private List<Category> categories;
	private DAO_Category category;
	
	@Override
	public void init() throws ServletException {
		account = new Account();
		dao_Account = new DAO_Account();
		categories = new ArrayList<>();
		category = new DAO_Category();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// get thể loại	
		getAllCategory(request, response);
		
		// load account by id
		String id = request.getParameter("id");
		int authorID = Integer.parseInt(id);
		account = dao_Account.findByID(authorID);
		
		request.setAttribute("account", account);
		
		request.getRequestDispatcher("views/EditAccount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		

	}
	
	private void getAllCategory(HttpServletRequest request, HttpServletResponse response) {
		categories = category.findAll();
		System.out.println(categories);
		request.setAttribute("categorys", categories);
	}

}
