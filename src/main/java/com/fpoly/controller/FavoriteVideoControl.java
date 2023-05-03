package com.fpoly.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpoly.dao.DAO_Favorite;
import com.fpoly.entity.Account;
import com.fpoly.entity.FavoriteVideo;
import com.fpoly.utils.SessionUtils;

@WebServlet("/FavoriteVideoControl")
public class FavoriteVideoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Account account;
	private List<FavoriteVideo> favoriteVideos;
	private DAO_Favorite dao_Favorite;

	@Override
	public void init() throws ServletException {
		account = new Account();
		favoriteVideos = new ArrayList<>();
		dao_Favorite = new DAO_Favorite();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Kiểm tra đăng nhập trong sessition
		account = (Account) SessionUtils.get(request, "acc");

		if (account == null) {
			request.setAttribute("a", "Vui lòng đăng nhập trước khi xem video yêu thích yêu thích");
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
		} else {
			// lấy video yeu thích theo id author
			int authorID = account.getAuthorID();
			favoriteVideos = dao_Favorite.getAllVideo(authorID);
			System.out.println(favoriteVideos + "võ thái hòa");
			if(favoriteVideos == null || favoriteVideos.isEmpty()) {
				request.setAttribute("mss", "Bạn không có video yêu thích");
			} else {
				request.setAttribute("listVideos", favoriteVideos);
			}
		}
		
		request.getRequestDispatcher("views/yeu-thich.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
