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
import com.fpoly.entity.Video;

@WebServlet("/search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO_Video dao_Video;
	private List<Video> list;

	@Override
	public void init() throws ServletException {
		dao_Video = new DAO_Video();
		list = new ArrayList<>();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String search = request.getParameter("txt");
		System.out.println(search);
		list = dao_Video.searchByName(search);
		System.out.println(list);

		if (list == null || list.isEmpty()) {
			request.setAttribute("search", "Không tìm thấy kết quả nào: " + search);

			request.setAttribute("txtSearch", search);
			request.setAttribute("listVideos", list);
			request.getRequestDispatcher("views/index.jsp").forward(request, response);
		} else {
			request.setAttribute("search", "Kết quả tìm kiếm");

			request.setAttribute("txtSearch", search);
			request.setAttribute("listVideos", list);
			request.getRequestDispatcher("views/index.jsp").forward(request, response);
		}

	}

}
