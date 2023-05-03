package com.fpoly.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;

import com.fpoly.dao.DAO_Category;
import com.fpoly.dao.DAO_Favorite;
import com.fpoly.dao.DAO_Video;
import com.fpoly.entity.Account;
import com.fpoly.entity.Category;
import com.fpoly.entity.FavoriteVideo;
import com.fpoly.entity.Video;
import com.fpoly.utils.DateUtils;
import com.fpoly.utils.SessionUtils;

@WebServlet("/like")
public class LikeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FavoriteVideo favoriteVideo;
	private DAO_Favorite dao_Favorite;
	private Account account;
	private Video video;
	private DAO_Video dao_Video;
	private Category category;
	private DAO_Category dao_Category;
	private SimpleDateFormat df = new SimpleDateFormat();
	private List<Video> videos;

	@Override
	public void init() throws ServletException {
		favoriteVideo = new FavoriteVideo();
		dao_Favorite = new DAO_Favorite();
		account = new Account();
		video = new Video();
		dao_Video = new DAO_Video();
		category = new Category();
		dao_Category = new DAO_Category();
		videos = new ArrayList<>();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String vid = request.getParameter("vid");
		account = (Account) SessionUtils.get(request, "acc");
		video = dao_Video.getByID(Integer.parseInt(vid));

		String date = DateUtils.getDateNow();
		Date dateNow = new Date();
		try {
			dateNow = df.parse(date);
		} catch (ParseException e) {
		}

		favoriteVideo = dao_Favorite.getByVIDAndAuID(Integer.parseInt(vid), account.getAuthorID());
		System.out.println(favoriteVideo);
		if (favoriteVideo == null) {
			// thêm vào db
			favoriteVideo = new FavoriteVideo(0, video, account, dateNow);
			dao_Favorite.create(favoriteVideo);
			request.setAttribute("style", "red");
		} else if(favoriteVideo != null) {
			try {
				System.out.println("ID yêu thích" + favoriteVideo.getFavoriteVideoID());
				dao_Favorite.delete(favoriteVideo.getFavoriteVideoID());
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("style", null);
		}

		// Lấy id của categoryid

		videos = dao_Video.getVideoByCategoryID(video.getCategory().getCategoryID());
		request.setAttribute("videoCategory", videos);

		Video videoid = dao_Video.getByID(video.getVideoID());
		request.setAttribute("video", videoid);

		request.getRequestDispatcher("views/watch.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
