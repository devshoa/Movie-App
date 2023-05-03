package com.fpoly.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "share")
public class Share {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shareID")
	private int shareID;

	@ManyToOne
	@JoinColumn(name = "authorID")
	private Account account;

	@ManyToOne
	@JoinColumn(name = "videoID")
	private Video video;

	@ManyToOne
	@JoinColumn(name = "categoryID")
	private Category category;

	@Column(name = "shareDate")
	private Date shareDate;

	public Share() {
	}

	public Share(int shareID, Account account, Video video, Category category, Date shareDate) {
		super();
		this.shareID = shareID;
		this.account = account;
		this.video = video;
		this.category = category;
		this.shareDate = shareDate;
	}

	public int getShareID() {
		return shareID;
	}

	public void setShareID(int shareID) {
		this.shareID = shareID;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getShareDate() {
		return shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}

	@Override
	public String toString() {
		return "Share [shareID=" + shareID + ", account=" + account + ", video=" + video + ", category=" + category
				+ ", shareDate=" + shareDate + "]";
	}

}