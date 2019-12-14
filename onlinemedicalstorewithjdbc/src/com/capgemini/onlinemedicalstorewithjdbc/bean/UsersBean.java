package com.capgemini.onlinemedicalstorewithjdbc.bean;

public class UsersBean {
	private int userId;
	private String userName;
	private String email;
	private long mobileNumber;
	private String password;
	private String userType;
	
	//Getters and Setters
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "UsersBean [userId=" + userId + ", userName=" + userName + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", password=" + password + ", userType=" + userType + "]";
	}
	
}//End of Class
