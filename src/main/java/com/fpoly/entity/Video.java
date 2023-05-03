package com.fpoly.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "videos")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "videoID")
	private int videoID;

	@Column(name = "nameVideo")
	private String nameVideo;

	@Column(name = "description")
	private String description;

	@Column(name = "performer")
	private String performer;

	@Column(name = "dateCreated")
	private Date dateCreated;

	@Column(name = "images")
	private String images;

	@Column(name = "links")
	private String links;

	@Column(name = "time")
	private String time;

	@Column(name = "views")
	private int views;

	@Column(name = "likes")
	private int likes;

	@Column(name = "shares")
	private int shares;

	@ManyToOne
	@JoinColumn(name = "categoryID")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "authorID")
	private Account account;

	public Video() {
	}

	public Video(int videoID, String nameVideo, String description, String performer, Date dateCreated, String images,
			String links, String time, int views, int likes, int shares, Category category, Account account) {
		super();
		this.videoID = videoID;
		this.nameVideo = nameVideo;
		this.description = description;
		this.performer = performer;
		this.dateCreated = dateCreated;
		this.images = images;
		this.links = links;
		this.time = time;
		this.views = views;
		this.likes = likes;
		this.shares = shares;
		this.category = category;
		this.account = account;
	}

	public int getVideoID() {
		return videoID;
	}

	public String getNameVideo() {
		return nameVideo;
	}

	public String getDescription() {
		return description;
	}

	public String getPerformer() {
		return performer;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public String getImages() {
		return images;
	}

	public String getLinks() {
		return links;
	}

	public String getTime() {
		return time;
	}

	public int getViews() {
		return views;
	}

	public int getLikes() {
		return likes;
	}

	public int getShares() {
		return shares;
	}

	public Category getCategory() {
		return category;
	}

	public Account getAccount() {
		return account;
	}

	public void setVideoID(int videoID) {
		this.videoID = videoID;
	}

	public void setNameVideo(String nameVideo) {
		this.nameVideo = nameVideo;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public void setLinks(String links) {
		this.links = links;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Video [videoID=" + videoID + ", nameVideo=" + nameVideo + ", description=" + description
				+ ", performer=" + performer + ", dateCreated=" + dateCreated + ", images=" + images + ", links="
				+ links + ", time=" + time + ", views=" + views + ", likes=" + likes + ", shares=" + shares
				+ ", category=" + category + ", account=" + account + "]";
	}

}
