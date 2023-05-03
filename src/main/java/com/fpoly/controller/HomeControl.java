package com.fpoly.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.fpoly.utils.DateUtils;

@WebServlet("/home")
public class HomeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// List này chứa tất cả các video
	private List<Video> allVideos;
	// List này chứa 5 video mới nhất
	private List<Video> top5Video;
	// Dao
	private DAO_Video dao_Video;

	// Danh sách ngày
	private List<String> getDay;

	private Date dateVideo;


	@Override
	public void init() throws ServletException {
		allVideos = new ArrayList<>();
		top5Video = new ArrayList<>();
		dao_Video = new DAO_Video();
		dateVideo = null;
		getDay = new ArrayList();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		findAll(request, response);
		request.getRequestDispatcher("views/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	

	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			allVideos = dao_Video.findAll();
			System.out.println(allVideos);
			request.setAttribute("listVideos", allVideos);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
