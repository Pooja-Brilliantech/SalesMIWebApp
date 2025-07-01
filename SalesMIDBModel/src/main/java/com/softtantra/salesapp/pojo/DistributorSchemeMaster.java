package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "distributor_scheme_master")
public class DistributorSchemeMaster implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int distributor_scheme_master_id;
	private int company_id;
	private String scheme_name;
	private String description;
	private Date start_date;
	private Date end_date;
	@Column(name = "terms_conditions", columnDefinition = "TEXT")
	private String terms_conditions;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;

	@Transient
	private String formated_start_date;
	@Transient
	private String formated_end_date;

	public int getDistributor_scheme_master_id() {
		return distributor_scheme_master_id;
	}

	public void setDistributor_scheme_master_id(int distributor_scheme_master_id) {
		this.distributor_scheme_master_id = distributor_scheme_master_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getScheme_name() {
		return scheme_name;
	}

	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getTerms_conditions() {
		return terms_conditions;
	}

	public void setTerms_conditions(String terms_conditions) {
		this.terms_conditions = terms_conditions;
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

	public String getFormated_start_date() {
		return formated_start_date;
	}

	public void setFormated_start_date(String formated_start_date) {
		this.formated_start_date = formated_start_date;
	}

	public String getFormated_end_date() {
		return formated_end_date;
	}

	public void setFormated_end_date(String formated_end_date) {
		this.formated_end_date = formated_end_date;
	}
}
