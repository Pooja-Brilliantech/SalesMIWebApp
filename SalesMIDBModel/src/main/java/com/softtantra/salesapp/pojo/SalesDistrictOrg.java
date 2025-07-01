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
@Table(name = "sales_district_org")
public class SalesDistrictOrg implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sales_dist_id;
	private String district_code;
	private String sales_district_code;
	private String sales_group_code;
	private int company_id;
	private int created_by;
	private int updated_by;
	@CreationTimestamp
	private Date created_date;;
	@UpdateTimestamp
	private Date updated_date;
	private int status;
	private int state_id;

	
	public int getSales_dist_id() {
		return sales_dist_id;
	}

	public void setSales_dist_id(int sales_dist_id) {
		this.sales_dist_id = sales_dist_id;
	}

	public String getSales_district_code() {
		return sales_district_code;
	}

	public void setSales_district_code(String sales_district_code) {
		this.sales_district_code = sales_district_code;
	}

	public String getSales_group_code() {
		return sales_group_code;
	}

	public void setSales_group_code(String sales_group_code) {
		this.sales_group_code = sales_group_code;
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

	public String getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}

	public int getState_id() {
		return state_id;
	}

	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	
}
