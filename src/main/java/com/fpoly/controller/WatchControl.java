package com.fpoly.controller;

import java.io.IOException;
import java.text.NumberFormat.Style;
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
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

import com.fpoly.dao.DAO_Favorite;
import com.fpoly.dao.DAO_History;
import com.fpoly.dao.DAO_Video;
import com.fpoly.entity.Account;
import com.fpoly.entity.FavoriteVideo;
import com.fpoly.entity.History;
import com.fpoly.entity.Video;
import com.fpoly.utils.SessionUtils;

@WebServlet("/watch")
public class WatchControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Video được lấy theo id
	private Video video;
	private DAO_Video dao_Video;

	// List video by categoryID
	private List<Video> videos;

	// Dao history
	private DAO_History dao_History;
	// Đối tượng History;
	private History history;
	// Danh sách history
	
	private Account account;

	@Override
	public void init() throws ServletException {
		video = new Video();
		dao_Video = new DAO_Video();
		videos = new ArrayList();
		dao_History = new DAO_History();
		history = new History();
		account = new Account();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getVideoByID(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private void getVideoByID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Kiểm tra đăng nhập trong sessition
		account = (Account) SessionUtils.get(request, "acc");

		if (account == null) {
			request.setAttribute("a", "Vui lòng đăng nhập trước khi xem video");
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
		} else {
			// lấy id của video
			String id = request.getParameter("video");
			// Get video by id
			int videoID = Integer.valueOf(id);
			video = dao_Video.getByID(videoID);
			request.setAttribute("video", video);
			
			// check yêu thích
			DAO_Favorite dao_Favorite = new DAO_Favorite();
			FavoriteVideo favoriteVideo = new FavoriteVideo();
			favoriteVideo = dao_Favorite.getByVIDAndAuID(videoID, video.getAccount().getAuthorID());
			if(favoriteVideo == null) {
				request.setAttribute("style", null);
			} else {
				request.setAttribute("style", "red");
			}
			

			// gọi phương thức getvideo theo categoryID
			getVideoByCategoryID(request, response);

			// lưu video người dùng đã xem vào history
			addVideoInHistory(request, response);

			// goiPhuongw thức update view
			updateTheView(request, response);
			request.getRequestDispatcher("views/watch.jsp").forward(request, response);
		}
	}

	/**
	 * Phương thức lấy ra những video theo category người dùng chọn
	 * 
	 * @param request
	 * @param response
	 */
	private void getVideoByCategoryID(HttpServletRequest request, HttpServletResponse response) {
		// Lấy id của categoryid
		String cid = request.getParameter("cid");
		// Get video by categoryID
		int categoryID = Integer.parseInt(cid);
		videos = dao_Video.getVideoByCategoryID(categoryID);
		request.setAttribute("videoCategory", videos);
	}

	private void updateTheView(HttpServletRequest request, HttpServletResponse response) {
		// lấy ra số views hiện tại
		int viewNow = video.getViews();
		int id = video.getVideoID();

		video.setViews(viewNow += 1);
		System.out.println(video);

		dao_Video.updateViewsByID(video);
	}

	private void addVideoInHistory(HttpServletRequest request, HttpServletResponse response) {
		// Lấy AuthorID trong session
		Account account = (Account) SessionUtils.get(request, "acc");

		// Trước khi thêm vào history thì kiểm tra người dùng đã xem video chưa
		// nếu như người dùng đã xem video rồi thì tiến hành cập nhật thời gian
		// còn không thì thêm vào db
		history = dao_History.getHistoryByAuthorIDAndVideoID(account.getAuthorID(), video.getVideoID());
		System.out.println("History: " + history);

		if (history != null) {
			// tiến hành update thời gian xem video
			// Lấy ra thời gian xem hiện tại
			Date now = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String formattedDate = formatter.format(now);

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date;
			try {
				date = dateFormat.parse(formattedDate);
				history.setViewedDate(date);
				dao_History.update(history);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			history = new History();
			// thực hiện thêm vào history

			// đầu tiên lấy ra id của video và id của người dùng đã lick vào video
			int videoID = video.getVideoID();

			// Lấy ra thời gian xem hiện tại
			Date now = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String formattedDate = formatter.format(now);

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			try {
				Date date = dateFormat.parse(formattedDate);
				history.setAuthor(account);
				history.setVideo(video);
				history.setViewedDate(date);
				history.setLiked(false);
				history.setLikeDate(date);
				dao_History.create(history);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
