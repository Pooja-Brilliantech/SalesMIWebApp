package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "sm_resolution_name")
public class SM_Resolution_Name implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sm_resolution_name_id;
	private int resolution_name_code;
	private String resolution_code;
	private String resolution_name;
	private int company_id;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;

	public int getResolution_name_code() {
		return resolution_name_code;
	}

	public void setResolution_name_code(int resolution_name_code) {
		this.resolution_name_code = resolution_name_code;
	}

	public int getSm_resolution_name_id() {
		return sm_resolution_name_id;
	}

	public void setSm_resolution_name_id(int sm_resolution_name_id) {
		this.sm_resolution_name_id = sm_resolution_name_id;
	}

	public String getResolution_code() {
		return resolution_code;
	}

	public void setResolution_code(String resolution_code) {
		this.resolution_code = resolution_code;
	}

	public String getResolution_name() {
		return resolution_name;
	}

	public void setResolution_name(String resolution_name) {
		this.resolution_name = resolution_name;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
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

}
