package com.softtantra.salesapp.pojo;

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
@Table(name = "challen_master")
public class ChallenMaster implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int challen_id;

	private int company_id;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private int status;
	private int customer_id;
	private int salesmaster_id;
	private double total_amount;

	private String despatch_document_number;
	private String despatched_through;
	private String contact_no;
	private String terms_of_delivery;
	private String other_references;
	private String destination;
	@Transient
	private String c_date;

	public String getC_date() {
		return c_date;
	}

	public void setC_date(String c_date) {
		this.c_date = c_date;
	}

	public int getChallen_id() {
		return challen_id;
	}

	public void setChallen_id(int challen_id) {
		this.challen_id = challen_id;
	}

	public String getDespatch_document_number() {
		return despatch_document_number;
	}

	public void setDespatch_document_number(String despatch_document_number) {
		this.despatch_document_number = despatch_document_number;
	}

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getSalesmaster_id() {
		return salesmaster_id;
	}

	public void setSalesmaster_id(int salesmaster_id) {
		this.salesmaster_id = salesmaster_id;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public String getDespatched_through() {
		return despatched_through;
	}

	public void setDespatched_through(String despatched_through) {
		this.despatched_through = despatched_through;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getTerms_of_delivery() {
		return terms_of_delivery;
	}

	public void setTerms_of_delivery(String terms_of_delivery) {
		this.terms_of_delivery = terms_of_delivery;
	}

	public String getOther_references() {
		return other_references;
	}

	public void setOther_references(String other_references) {
		this.other_references = other_references;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

}
