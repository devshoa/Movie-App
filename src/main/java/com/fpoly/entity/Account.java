package com.fpoly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "authorID")
	private int authorID;

	@Column(name = "userName")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "fullName")
	private String fullname;

	@Column(name = "avartar")
	private String avartar;

	@Column(name = "email")
	private String email;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "createdAt")
	private Date createdAt;

	@Column(name = "updateAt")
	private Date updateAt;

	@Column(name = "role")
	private boolean role;

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(int authorID, String userName, String password, String fullname, String avartar, String email,
			String phoneNumber, Date createdAt, Date updateAt, boolean role) {
		super();
		this.authorID = authorID;
		this.userName = userName;
		this.password = password;
		this.fullname = fullname;
		this.avartar = avartar;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
		this.role = role;
	}

	
	
	public Account(String userName, String password, Date createdAt, Date updateAt, boolean role) {
		super();
		this.userName = userName;
		this.password = password;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
		this.role = role;
	}

	public Account(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	

	public int getAuthorID() {
		return authorID;
	}

	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAvartar() {
		return avartar;
	}

	public void setAvartar(String avartar) {
		this.avartar = avartar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public boolean isRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Account [authorID=" + authorID + ", userName=" + userName + ", password=" + password + ", fullname="
				+ fullname + ", avartar=" + avartar + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", createdAt=" + createdAt + ", updateAt=" + updateAt + ", role=" + role + "]";
	}

}
