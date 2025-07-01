package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class View_Visit_Master implements Serializable{

	// visit_details
	private int visit_id;
	private int company_id;
	private String description;
	private Date app_created_date;
	private Date nex_visit_date;
	private String completion_info;
	private String remarks;
	private String img_path;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private String visit_address;
	private String coTravellers;
	@Transient
	private String app_created_date1;
	@Transient
	private String nex_visit_date1;

	// customer_details
	private int customer_id;
	private String email;
	private String phone;
	private String address;

	// user_details
	private String first_name;
	private String last_name;
	
	
	private Date checkin_time;
	private String checkin_location;
	private Date checkout_time;
	private String checkout_location;
	private String duration;
	@Transient
	private String deviation;
	

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Date getCheckin_time() {
		return checkin_time;
	}

	public void setCheckin_time(Date checkin_time) {
		this.checkin_time = checkin_time;
	}
	
	public String getCheckin_location() {
		return checkin_location;
	}

	public void setCheckin_location(String checkin_location) {
		this.checkin_location = checkin_location;
	}

	public Date getCheckout_time() {
		return checkout_time;
	}

	public void setCheckout_time(Date checkout_time) {
		this.checkout_time = checkout_time;
	}
	
	public String getCheckout_location() {
		return checkout_location;
	}

	public void setCheckout_location(String checkout_location) {
		this.checkout_location = checkout_location;
	}

	public String getDeviation() {
		return deviation;
	}

	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getNex_visit_date1() {
		return nex_visit_date1;
	}

	public void setNex_visit_date1(String nex_visit_date1) {
		this.nex_visit_date1 = nex_visit_date1;
	}

	public String getApp_created_date1() {
		return app_created_date1;
	}

	public void setApp_created_date1(String app_created_date1) {
		this.app_created_date1 = app_created_date1;
	}

	public int getVisit_id() {
		return visit_id;
	}

	public void setVisit_id(int visit_id) {
		this.visit_id = visit_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getApp_created_date() {
		return app_created_date;
	}

	public void setApp_created_date(Date app_created_date) {
		this.app_created_date = app_created_date;
	}

	public Date getNex_visit_date() {
		return nex_visit_date;
	}

	public void setNex_visit_date(Date nex_visit_date) {
		this.nex_visit_date = nex_visit_date;
	}

	public String getCompletion_info() {
		return completion_info;
	}

	public void setCompletion_info(String completion_info) {
		this.completion_info = completion_info;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
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

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVisit_address() {
		return visit_address;
	}

	public void setVisit_address(String visit_address) {
		this.visit_address = visit_address;
	}

	public String getCoTravellers() {
		return coTravellers;
	}

	public void setCoTravellers(String coTravellers) {
		this.coTravellers = coTravellers;
	}

}
