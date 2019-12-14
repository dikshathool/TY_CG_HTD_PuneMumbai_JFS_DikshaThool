package com.capgemini.onlinemedicalstorewithjdbc.bean;

public class AdminBean {
	private int adminId;
	private String adminName;
	private String email;
	private long mobileNumber;
	private String password;
	
	//Getters and Setters
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
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
	@Override
	public String toString() {
		return "adminId=" + adminId + ", adminName=" + adminName + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", password=" + password;
	}//End of toString()
	
}//End of class
