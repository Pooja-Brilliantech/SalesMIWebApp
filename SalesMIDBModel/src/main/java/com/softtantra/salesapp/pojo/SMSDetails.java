package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "sms_details")
public class SMSDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sms_id;
	private String message;
	private Date sms_date;
	@CreationTimestamp
	private Date created_date;;
	private int created_by;
	private String sms_from; // webapp or from sales person to customer name
	private String sms_type; // sales order ,
	private int company_id;
	private String mobile;
	private int status;

	public int getSms_id() {
		return sms_id;
	}

	public void setSms_id(int sms_id) {
		this.sms_id = sms_id;
	}

	public Date getSms_date() {
		return sms_date;
	}

	public void setSms_date(Date sms_date) {
		this.sms_date = sms_date;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public String getSms_from() {
		return sms_from;
	}

	public void setSms_from(String sms_from) {
		this.sms_from = sms_from;
	}

	public String getSms_type() {
		return sms_type;
	}

	public void setSms_type(String sms_type) {
		this.sms_type = sms_type;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
