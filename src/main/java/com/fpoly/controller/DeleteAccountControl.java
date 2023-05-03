package com.fpoly.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.dao.DAO_Account;

@WebServlet("/delete")
public class DeleteAccountControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO_Account dao_Account;

	@Override
	public void init() throws ServletException {
		dao_Account = new DAO_Account();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String authorID = request.getParameter("id");
		try {
			dao_Account.delete(Integer.parseInt(authorID));
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("managerAccount");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
