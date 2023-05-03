package com.fpoly.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FavoriteVideo")
public class FavoriteVideo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "favoriteVideoID")
	private int favoriteVideoID;

	@ManyToOne
	@JoinColumn(name = "videoID")
	private Video video;

	@ManyToOne
	@JoinColumn(name = "authorID")
	private Account account;

	@Column(name = "likeDate")
	private Date likeDate;

	public FavoriteVideo() {
	}

	public FavoriteVideo(int favoriteVideoID, Video video, Account account, Date likeDate) {
		super();
		this.favoriteVideoID = favoriteVideoID;
		this.video = video;
		this.account = account;
		this.likeDate = likeDate;
	}

	public int getFavoriteVideoID() {
		return favoriteVideoID;
	}

	public Video getVideo() {
		return video;
	}

	public Account getAccount() {
		return account;
	}

	public Date getLikeDate() {
		return likeDate;
	}

	public void setFavoriteVideoID(int favoriteVideoID) {
		this.favoriteVideoID = favoriteVideoID;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

	@Override
	public String toString() {
		return "FavoriteVideo [favoriteVideoID=" + favoriteVideoID + ", video=" + video + ", account=" + account
				+ ", likeDate=" + likeDate + "]";
	}

}