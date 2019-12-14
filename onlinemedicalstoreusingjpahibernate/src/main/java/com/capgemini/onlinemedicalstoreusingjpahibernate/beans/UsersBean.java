package com.capgemini.onlinemedicalstoreusingjpahibernate.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users_info")
public class UsersBean {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	@Column(name = "user_name")
	private String userName;
	@Column
	private String email;
	@Column(name = "mobile_number")
	private long mobileNumber;
	@Column
	private String password;
	@Column(name = "user_type")
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
