package com.example.demo.pojo;

public class User {


	private int id;//int(11) NOT NULL用户编号
	private String userName;//varchar(50) NULL用户昵称
	private String userPwd;//varchar(50) NULL用户密码
	private String userPhoto;//varchar(50) NULL用户头像
	
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userPwd=" + userPwd + ", userPhoto=" + userPhoto + "]";
	}
	public User() {
		super();
	}
	public User(int id, String userName, String userPwd, String userPhoto) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userPhoto = userPhoto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	
	
	
	
}
