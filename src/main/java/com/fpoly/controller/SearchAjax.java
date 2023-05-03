package com.fpoly.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.dao.DAO_Video;
import com.fpoly.entity.Video;

@WebServlet("/searchAjax")
public class SearchAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO_Video dao_Video;
	private List<Video> videos;

	@Override
	public void init() throws ServletException {
		dao_Video = new DAO_Video();
		videos = new ArrayList<>();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String txtSearch = request.getParameter("txt");
		System.out.println(txtSearch);
		videos = dao_Video.searchByName(txtSearch);

		PrintWriter out = response.getWriter();
		for (Video video : videos) {
			out.print("<div class=\"video anim\" style=\"-delay: .4s\">\r\n"
					+ "							<a href=\"watch?video=" + video.getVideoID() + "&cid="
					+ video.getCategory().getCategoryID()
					+ "\">\r\n"
					+ "								<div class=\"video-time\">" + video.getTime() + "</div>\r\n"
					+ "								<div class=\"video-wrapper\">\r\n"
					+ "									<img class=\"image\"\r\n"
					+ "										style=\"position: absolute; width: 100%; height: 100%;\" alt=\"\"\r\n"
					+ "										src=\"" + video.getImages() + "\">\r\n"
					+ "									<video muted=\"" + video.getLinks() + "\">\r\n"
					+ "										<source src=\"" + video.getLinks()
					+ "\" type=\"video/mp4\">\r\n" + "									</video>\r\n"
					+ "									<div class=\"author-img__wrapper video-author\">\r\n"
					+ "										<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\"\r\n"
					+ "											stroke-width=\"3\" stroke-linecap=\"round\"\r\n"
					+ "											stroke-linejoin=\"round\" class=\"feather feather-check\">\r\n"
					+ "                                        <path d=\"M20 6L9 17l-5-5\" />\r\n"
					+ "                                    </svg>\r\n"
					+ "										<img class=\"author-img\" src=\""
					+ video.getAccount().getAvartar() + "\" />\r\n" + "									</div>\r\n"
					+ "								</div>\r\n"
					+ "								<div class=\"video-by\">" + video.getAccount().getFullname()
					+ "</div>\r\n" + "								<div class=\"video-name\">" + video.getNameVideo()
					+ "</div>\r\n" + "								<div class=\"video-view\">" + video.getViews()
					+ "\r\n"
					+ "									lượt xem <span class=\"seperate video-seperate\"></span>\r\n"
					+ "									<!-- Thời gian đăng video -->\r\n"
					+ "									" + video.getDateCreated() + "\r\n"
					+ "								</div>\r\n" + "\r\n" + "							</a>\r\n"
					+ "						</div>");
		}

		System.out.println(videos);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
