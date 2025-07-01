package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "manage_pack_unit")
public class ManagePackUnit implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pack_unit_id;
	
	private String short_form;
	private String long_form;
	private String default_value;
	private int company_id;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;

	public int getPack_unit_id() {
		return pack_unit_id;
	}
	
	public void setPack_unit_id(int pack_unit_id) {
		this.pack_unit_id = pack_unit_id;
	}

	public String getShort_form() {
		return short_form;
	}

	public void setShort_form(String short_form) {
		this.short_form = short_form;
	}

	public String getLong_form() {
		return long_form;
	}

	public void setLong_form(String long_form) {
		this.long_form = long_form;
	}

	public String getDefault_value() {
		return default_value;
	}

	public void setDefault_value(String default_value) {
		this.default_value=default_value;
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
}
