package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class RenewalMaster implements Serializable{

	// renewal request details
	private int renewal_request_id;
	private int requestor_user_id;
	private String description;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;

	// packagedetails
	private int package_id;
	private String package_name;

	// companydetails
	private int company_id;
	private String company_name;

	// renevaldetails
	private int renewal_id;
	private Date start_date;
	private Date end_date;
	// private int status;

	@Transient
	private int no_of_months;

	public int getNo_of_months() {
		return no_of_months;
	}

	public void setNo_of_months(int no_of_months) {
		this.no_of_months = no_of_months;
	}

	public int getRenewal_request_id() {
		return renewal_request_id;
	}

	public int getRenewal_id() {
		return renewal_id;
	}

	public void setRenewal_id(int renewal_id) {
		this.renewal_id = renewal_id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public void setRenewal_request_id(int renewal_request_id) {
		this.renewal_request_id = renewal_request_id;
	}

	public int getRequestor_user_id() {
		return requestor_user_id;
	}

	public void setRequestor_user_id(int requestor_user_id) {
		this.requestor_user_id = requestor_user_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getPackage_id() {
		return package_id;
	}

	public void setPackage_id(int package_id) {
		this.package_id = package_id;
	}

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

}
