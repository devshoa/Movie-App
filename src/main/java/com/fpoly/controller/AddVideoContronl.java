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

import com.fpoly.dao.DAO_Category;
import com.fpoly.dao.DAO_Video;
import com.fpoly.entity.Account;
import com.fpoly.entity.Category;
import com.fpoly.entity.Video;
import com.fpoly.utils.DateUtils;
import com.fpoly.utils.SessionUtils;

@WebServlet("/addVideo")
public class AddVideoContronl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Video video;
	private DAO_Video dao_Video;
	private DAO_Category dao_Category;
	private SimpleDateFormat df;

	@Override
	public void init() throws ServletException {
		video = new Video();
		dao_Video = new DAO_Video();
		dao_Category = new DAO_Category();
		df = new SimpleDateFormat();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");


		// Get form
		String nameVideo = request.getParameter("videoname");
		String description = request.getParameter("description");
		String performer = request.getParameter("performer");
		String image = request.getParameter("image");
		String video1 = request.getParameter("video");
		String time = request.getParameter("time");
		String categor = request.getParameter("category");
		Account account = (Account) SessionUtils.get(request, "acc");

		Category category = new Category();
		category = dao_Category.findByID(Integer.parseInt(categor));

		String c = DateUtils.getDateNow();
		Date createDate = new Date();
		try {
			createDate = df.parse(c);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		video = new Video(0, nameVideo, description, performer, createDate, image, video1, time,
				0, 0, 0, category, account);

		dao_Video.create(video);
		response.sendRedirect("managerVideo");
		
	}

}
