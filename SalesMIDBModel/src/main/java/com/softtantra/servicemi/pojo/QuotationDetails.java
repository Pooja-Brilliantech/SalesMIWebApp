package com.softtantra.servicemi.pojo;

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
@Table(name = "quotation_details")
public class QuotationDetails implements Serializable{

	@Id
	@Column(name = "quote_id")
	@GeneratedValue
	private int quote_id;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private String subject;
	private String billing_address;
	private String description;
	private String shipping_address;
	private String t_and_c;
	private int company_id;
	private String opportunity_name;
	private String valid_until;
	
	public String getOpportunity_name() {
		return opportunity_name;
	}
	public void setOpportunity_name(String opportunity_name) {
		this.opportunity_name = opportunity_name;
	}
	public String getValid_until() {
		return valid_until;
	}
	public void setValid_until(String valid_until) {
		this.valid_until = valid_until;
	}
	public int getQuote_id() {
		return quote_id;
	}
	public void setQuote_id(int quote_id) {
		this.quote_id = quote_id;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBilling_address() {
		return billing_address;
	}
	public void setBilling_address(String billing_address) {
		this.billing_address = billing_address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getShipping_address() {
		return shipping_address;
	}
	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}
	public String getT_and_c() {
		return t_and_c;
	}
	public void setT_and_c(String t_and_c) {
		this.t_and_c = t_and_c;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	
	
}
