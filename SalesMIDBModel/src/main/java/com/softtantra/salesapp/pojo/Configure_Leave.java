package com.softtantra.salesapp.pojo;

import javax.persistence.Transient;

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
@Table(name = "configure_leave")
public class Configure_Leave implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int configure_leave_id;
	private int company_id;
	private int no_of_leaves;
	private int role_id;
	private int leave_type_id;
	private int status;
	@CreationTimestamp
	private Date created_date;;
	public String getLeave_type_name() {
		return leave_type_name;
	}

	public void setLeave_type_name(String leave_type_name) {
		this.leave_type_name = leave_type_name;
	}

	private int created_by;
	@UpdateTimestamp
	private Date updated_date;
	private int updated_by;
	
	@Transient
	private String leave_type_name;
	public int getConfigure_leave_id() {
		return configure_leave_id;
	}

	public void setConfigure_leave_id(int configure_leave_id) {
		this.configure_leave_id = configure_leave_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getNo_of_leaves() {
		return no_of_leaves;
	}

	public void setNo_of_leaves(int no_of_leaves) {
		this.no_of_leaves = no_of_leaves;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public int getLeave_type_id() {
		return leave_type_id;
	}

	public void setLeave_type_id(int leave_type_id) {
		this.leave_type_id = leave_type_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}
}
