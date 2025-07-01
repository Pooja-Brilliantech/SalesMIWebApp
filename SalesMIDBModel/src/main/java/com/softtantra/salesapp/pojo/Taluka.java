package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "taluka")
public class Taluka implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int taluka_id;
	
	@Column(nullable = true)
	private String talukaCode;
	
	private String talukaDescription;
	
	@ColumnDefault(value = "0")
	private int company_id;
	
	private int created_by;
	private int updated_by;
	
	@CreationTimestamp
	private Date created_date;
	
	@UpdateTimestamp
	private Date updated_date;
	
	private int status;

	private int district_id;


	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTaluka_id() {
		return taluka_id;
	}

	public void setTaluka_id(int taluka_id) {
		this.taluka_id = taluka_id;
	}

	public String getTalukaCode() {
		return talukaCode;
	}

	public void setTalukaCode(String talukaCode) {
		this.talukaCode = talukaCode;
	}

	public String getTalukaDescription() {
		return talukaDescription;
	}

	public void setTalukaDescription(String talukaDescription) {
		this.talukaDescription = talukaDescription;
	}

	public int getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}
	
	

}
