package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "visit_details")
public class VisitDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int visit_id;
	private int company_id;
	@Column(name = "customer_id", columnDefinition = "int default '0'", nullable = true)
	private int customer_id;
	private int user_id;
	private String description;
	private Date app_created_date;
	private Date nex_visit_date;
	private String completion_info;
	private String remarks;
	private String img_path;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private String address;
	private String lat;
	private String longi;
	private String coTravellers;
	@Column(name = "beatId", columnDefinition = "int default '0'", nullable = true)
	private int beatId=0;
	private String visitor_type;

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLongi() {
		return longi;
	}

	public void setLongi(String longi) {
		this.longi = longi;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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

	public String getCoTravellers() {
		return coTravellers;
	}

	public void setCoTravellers(String coTravellers) {
		this.coTravellers = coTravellers;
	}

	public int getBeatId() {
		return beatId;
	}

	public void setBeatId(int beatId) {
		this.beatId = beatId;
	}

	public String getVisitor_type() {
		return visitor_type;
	}

	public void setVisitor_type(String visitor_type) {
		this.visitor_type = visitor_type;
	}

}
