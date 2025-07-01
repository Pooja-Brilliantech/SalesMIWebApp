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
@Table(name = "sm_gem_customer_profile")
public class SM_Gem_CustomerProfile implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_gem_profile_id;
	private String company_name;
	private String firm_type;
	private String branch;
	private int branch_no;
	private int software_tally;
	private String license;
	private String sales_invoices_use;
	private String gems_standards;
	private String gems_add_on;
	private String apex_remark;
	private int rating;
	private int software_other;
	private String other_software_names;
	private int status;
	private int company_id;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;

	public int getCustomer_gem_profile_id() {
		return customer_gem_profile_id;
	}

	public void setCustomer_gem_profile_id(int customer_gem_profile_id) {
		this.customer_gem_profile_id = customer_gem_profile_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getFirm_type() {
		return firm_type;
	}

	public void setFirm_type(String firm_type) {
		this.firm_type = firm_type;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getBranch_no() {
		return branch_no;
	}

	public void setBranch_no(int branch_no) {
		this.branch_no = branch_no;
	}

	public int getSoftware_tally() {
		return software_tally;
	}

	public void setSoftware_tally(int software_tally) {
		this.software_tally = software_tally;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getSales_invoices_use() {
		return sales_invoices_use;
	}

	public void setSales_invoices_use(String sales_invoices_use) {
		this.sales_invoices_use = sales_invoices_use;
	}

	public String getGems_standards() {
		return gems_standards;
	}

	public void setGems_standards(String gems_standards) {
		this.gems_standards = gems_standards;
	}

	public String getGems_add_on() {
		return gems_add_on;
	}

	public void setGems_add_on(String gems_add_on) {
		this.gems_add_on = gems_add_on;
	}

	public String getApex_remark() {
		return apex_remark;
	}

	public void setApex_remark(String apex_remark) {
		this.apex_remark = apex_remark;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getSoftware_other() {
		return software_other;
	}

	public void setSoftware_other(int software_other) {
		this.software_other = software_other;
	}

	public String getOther_software_names() {
		return other_software_names;
	}

	public void setOther_software_names(String other_software_names) {
		this.other_software_names = other_software_names;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	@Transient
	private String user_name;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}
