
package com.freshcart.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Component
@Table(name="feedbacks")
@Entity
public class Feedback {

	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	@Column(name="user_id")
	private int userId;

	@Column(name = "full_name")
	private String fullName;
	
	@Column(name="mobile_no")
	private String mobileNo;
	
	private String email;

	private String message;
	
	@Column(name = "contact_date")
	private Date contactDate;
	
	

//---------------------------To String -------------------------------------------
	
	@Override
	public String toString() {
		return "Feedback [id=" + id + ", userId=" + userId + ", fullname=" + fullName + ", mobileNo=" + mobileNo
				+ ", email=" + email + ", message=" + message + ", contact_date=" + contactDate + "]";
	}
	

//--------------------   Getters And Setters ---------------------------------------
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullname) {
		this.fullName = fullname;
	}
	
	public String getMobileNo() {
		return mobileNo;
	}
	
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Date getContactDate() {
		return contactDate;
	}
	
	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}
	
	
	
}
