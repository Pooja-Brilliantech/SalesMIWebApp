package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "sm_fault_area_code")
public class SM_FaultAreaCode implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fault_area_code_id;
	private String fault_code;
	private String fault_name;
	private int company_id;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;

	@Transient
	private String createdDate;

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getFault_area_code_id() {
		return fault_area_code_id;
	}

	public void setFault_area_code_id(int fault_area_code_id) {
		this.fault_area_code_id = fault_area_code_id;
	}

	public String getFault_code() {
		return fault_code;
	}

	public void setFault_code(String fault_code) {
		this.fault_code = fault_code;
	}

	public String getFault_name() {
		return fault_name;
	}

	public void setFault_name(String fault_name) {
		this.fault_name = fault_name;
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
