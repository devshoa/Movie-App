package com.fpoly.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.dao.DAO_Category;
import com.fpoly.dao.DAO_Video;
import com.fpoly.entity.Category;
import com.fpoly.entity.Video;

@WebServlet("/loadVideo")
public class LoadVideoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO_Video dao_Video;
	private Video video;
	private DAO_Category category;
	private List<Category> categories;
	
	@Override
	public void init() throws ServletException {
		video = new Video();
		dao_Video = new DAO_Video();
		category = new DAO_Category();
		categories = new ArrayList<>();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get Category
		categories = category.findAll();
		
		String id = request.getParameter("id");
		
		// lấy video theo id
		video = dao_Video.getByID(Integer.parseInt(id));
			
		request.setAttribute("video", video);
		request.setAttribute("category", categories);
		request.getRequestDispatcher("views/EditVideo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
