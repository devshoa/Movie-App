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

@WebServlet("/category")
public class CategoryControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// danh sách video
	private List<Video> videos;
	private DAO_Video dao_Video;
	
	@Override
	public void init() throws ServletException {
		videos = new ArrayList();
		dao_Video = new DAO_Video();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		getVideoByCategoryID(request, response);
		request.getRequestDispatcher("views/category.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
	private void getVideoByCategoryID(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		int categoryID = Integer.parseInt(id);
		videos = dao_Video.getVideoByCategoryID(categoryID);
		
		request.setAttribute("videos", videos);
		
	}

}
