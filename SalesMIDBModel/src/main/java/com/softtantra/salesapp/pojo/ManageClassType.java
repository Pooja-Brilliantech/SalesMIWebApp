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
@Table(name = "manage_class_type")
public class ManageClassType implements Serializable{
	@Id
	@Column(name = "manage_class_type_id")
	@GeneratedValue
	private int manage_class_type_id;
	private String manage_class_name;
	private int sequence;
	private int company_id;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	@Column(name = "class_frequency", columnDefinition = "int default '0'")
	private int class_frequency;

	public int getClass_frequency() {
		return class_frequency;
	}

	public void setClass_frequency(int class_frequency) {
		this.class_frequency = class_frequency;
	}

	public int getManage_class_type_id() {
		return manage_class_type_id;
	}

	public void setManage_class_type_id(int manage_class_type_id) {
		this.manage_class_type_id = manage_class_type_id;
	}

	public String getManage_class_name() {
		return manage_class_name;
	}

	public void setManage_class_name(String manage_class_name) {
		this.manage_class_name = manage_class_name;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
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
