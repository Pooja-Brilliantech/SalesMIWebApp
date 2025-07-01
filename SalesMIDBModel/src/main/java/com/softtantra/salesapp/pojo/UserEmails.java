package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_emails")
public class UserEmails implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userMailId;
	private int company_id;
	private String userEmails;
	private int status;
	private int created_by;
	public int getUserMailId() {
		return userMailId;
	}
	public void setUserMailId(int userMailId) {
		this.userMailId = userMailId;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getUserEmails() {
		return userEmails;
	}
	public void setUserEmails(String userEmails) {
		this.userEmails = userEmails;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	
	

}
