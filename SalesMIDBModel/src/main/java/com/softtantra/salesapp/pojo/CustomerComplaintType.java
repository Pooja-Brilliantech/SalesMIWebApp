package com.softtantra.salesapp.pojo;

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
@Table(name="customer_complaint_type")
public class CustomerComplaintType implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int complaintTypeId;
	private String complaintName;
	private int company_id;
	private int status;
	private int updated_by;
	private int created_by;
	@CreationTimestamp
	private Date created_date;
	@UpdateTimestamp
	private Date updated_date;
	
	public int getComplaintTypeId() {
		return complaintTypeId;
	}
	public void setComplaintTypeId(int complaintTypeId) {
		this.complaintTypeId = complaintTypeId;
	}
	public String getComplaintName() {
		return complaintName;
	}
	public void setComplaintName(String complaintName) {
		this.complaintName = complaintName;
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
	public int getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	
	
	
	
	
	
	

}
