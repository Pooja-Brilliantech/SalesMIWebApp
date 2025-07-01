package com.softtantra.servicemi.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sm_gem_customer_profile_contact_details")
public class SM_GemsCustomerProfileContactDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int details_id;
	private int company_id;
	private int customer_profile_id;
	private String contact_name;
	private String contact_no;
	private String contact_email;
	private int status;
	private String designation;

	public int getDetails_id() {
		return details_id;
	}

	public void setDetails_id(int details_id) {
		this.details_id = details_id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getCustomer_profile_id() {
		return customer_profile_id;
	}

	public void setCustomer_profile_id(int customer_profile_id) {
		this.customer_profile_id = customer_profile_id;
	}

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getContact_email() {
		return contact_email;
	}

	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
