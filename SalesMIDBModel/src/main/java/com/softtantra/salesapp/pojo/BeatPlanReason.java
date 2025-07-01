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
@Table(name = "beat_plan_reason")
public class BeatPlanReason implements Serializable{
	@Id
	@Column(name = "beat_plan_reason_id")
	@GeneratedValue
	private int beat_plan_reason_id;
	private String beat_plan_reason;
	private int isdetailsrequired;
	private int company_id;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;

	public int getBeat_plan_reason_id() {
		return beat_plan_reason_id;
	}

	public void setBeat_plan_reason_id(int beat_plan_reason_id) {
		this.beat_plan_reason_id = beat_plan_reason_id;
	}

	public String getBeat_plan_reason() {
		return beat_plan_reason;
	}

	public void setBeat_plan_reason(String beat_plan_reason) {
		this.beat_plan_reason = beat_plan_reason;
	}

	public int getIsdetailsrequired() {
		return isdetailsrequired;
	}

	public void setIsdetailsrequired(int isdetailsrequired) {
		this.isdetailsrequired = isdetailsrequired;
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
