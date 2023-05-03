package com.fpoly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class History {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "historyID")
	private int historyID;

	@ManyToOne
	@JoinColumn(name = "authorID")
	private Account author;

	@ManyToOne
	@JoinColumn(name = "videoID")
	private Video video;

	@Column(name = "viewedDate")
	private Date viewedDate;

	@Column(name = "isLiked")
	private boolean isLiked;

	@Column(name = "likedDate")
	private Date likeDate;

	public History() {
	}

	public History(int historyID, Account author, Video video, Date viewedDate, boolean isLiked, Date likeDate) {
		super();
		this.historyID = historyID;
		this.author = author;
		this.video = video;
		this.viewedDate = viewedDate;
		this.isLiked = isLiked;
		this.likeDate = likeDate;
	}

	public int getHistoryID() {
		return historyID;
	}

	public Account getAuthor() {
		return author;
	}

	public Video getVideo() {
		return video;
	}

	public Date getViewedDate() {
		return viewedDate;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public Date getLikeDate() {
		return likeDate;
	}

	public void setHistoryID(int historyID) {
		this.historyID = historyID;
	}

	public void setAuthor(Account author) {
		this.author = author;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public void setViewedDate(Date viewedDate) {
		this.viewedDate = viewedDate;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

	@Override
	public String toString() {
		return "History [historyID=" + historyID + ", author=" + author + ", video=" + video + ", viewedDate="
				+ viewedDate + ", isLiked=" + isLiked + ", likeDate=" + likeDate + "]";
	}

}
