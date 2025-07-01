package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "customer_has_documents")
public class CustomerHasDocuments implements Serializable{

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;
	private String document_name;
	private int company_id;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private String customer_type_name;
	
	@Column(nullable=true)
	private String customer_sub_type;
	
	
	public String getCustomer_sub_type() {
		return customer_sub_type;
	}
	public void setCustomer_sub_type(String customer_sub_type) {
		this.customer_sub_type = customer_sub_type;
	}
	@Column(name = "is_mandatory", columnDefinition = "boolean default true", nullable = false)
	private Boolean is_mandatory = true;
	
	public Boolean getIs_mandatory() {
		return is_mandatory;
	}
	public void setIs_mandatory(Boolean is_mandatory) {
		this.is_mandatory = is_mandatory;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDocument_name() {
		return document_name;
	}
	public void setDocument_name(String document_name) {
		this.document_name = document_name;
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
	public String getCustomer_type_name() {
		return customer_type_name;
	}
	public void setCustomer_type_name(String customer_type_name) {
		this.customer_type_name = customer_type_name;
	}
	
	
	
}
