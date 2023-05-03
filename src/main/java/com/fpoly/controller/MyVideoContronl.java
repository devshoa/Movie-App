package com.fpoly.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fpoly.dao.DAO_Video;
import com.fpoly.entity.Account;
import com.fpoly.entity.Video;
import com.fpoly.utils.SessionUtils;

@WebServlet("/myVideo")
public class MyVideoContronl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO_Video dao_Video;
	private List<Video> videos;
	private Account account;

	@Override
	public void init() throws ServletException {
		videos = new ArrayList();
		dao_Video = new DAO_Video();
		account = new Account();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getVideoByAuthorID(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	private void getVideoByAuthorID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		account = (Account) SessionUtils.get(request, "acc");
		if (account == null) {
			String mss = "Vui lòng đăng nhập trước khi sử dụng chức năng này";
			request.setAttribute("a", mss);
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
			return;
		} else {

			// nếu đăng nhập rồi tiến hành lấy tất cả video người dùng đã đăng
			int id = account.getAuthorID();
			System.out.println("Mã người dùng: " + id);
			videos = dao_Video.getVideoByAuthorID(id);
			System.out.println(videos);

			if (videos == null || videos.isEmpty()) {
				request.setAttribute("mss", "Bạn chưa tải lên bất kỳ một video nào");
				request.getRequestDispatcher("views/my-video.jsp").forward(request, response);
			} else {
				request.setAttribute("listVideos", videos);

				request.getRequestDispatcher("views/my-video.jsp").forward(request, response);
			}

		}
	}

}
