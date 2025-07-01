package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "customer_required_documents")
public class CustomerRequiredDocuments implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int companyId;
	private String requiredDocumentName;
	private Date created_date;
	private Date updated_date;
	private int created_by;
	private int status;
	private int updated_by;
	
	@Transient
	private String customer_type;
	
	@Transient
	private boolean is_mandatory;
	
	@Transient
	private String customer_sub_type;
	
	
	public String getCustomer_sub_type() {
		return customer_sub_type;
	}
	public void setCustomer_sub_type(String customer_sub_type) {
		this.customer_sub_type = customer_sub_type;
	}
	public boolean isIs_mandatory() {
		return is_mandatory;
	}
	public void setIs_mandatory(boolean is_mandatory) {
		this.is_mandatory = is_mandatory;
	}
	public String getCustomer_type() {
		return customer_type;
	}
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getRequiredDocumentName() {
		return requiredDocumentName;
	}
	public void setRequiredDocumentName(String requiredDocumentName) {
		this.requiredDocumentName = requiredDocumentName;
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
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
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
	
	
}
