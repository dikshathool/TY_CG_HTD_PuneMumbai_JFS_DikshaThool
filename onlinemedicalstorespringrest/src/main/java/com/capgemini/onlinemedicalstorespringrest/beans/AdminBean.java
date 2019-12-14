package com.capgemini.onlinemedicalstorespringrest.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin_info")
public class AdminBean {
	@Id
	@Column(name = "admin_id")
	private int adminId;
	
	@Column(name = "admin_name")
	private String adminName;
	
	@Column
	private String email;
	
	@Column(name = "mobile_number")
	private long mobileNumber;
	
	@Column
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
		return "AdminBean [adminId=" + adminId + ", adminName=" + adminName + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", password=" + password + "]";
	}//End of toString()
	
}//End of class
