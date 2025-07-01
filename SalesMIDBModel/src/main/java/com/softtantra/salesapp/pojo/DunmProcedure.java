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
@Table(name = "dunn_procedure")
public class DunmProcedure implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dunmProcedureId;
	private String language;
	private String dunnProcedure;
	private String description;
	private int company_id;
	private int created_by;
	private int updated_by;
	@CreationTimestamp
	private Date created_date;;
	@UpdateTimestamp
	private Date updated_date;
	private int status;

	public int getDunmProcedureId() {
		return dunmProcedureId;
	}

	public void setDunmProcedureId(int dunmProcedureId) {
		this.dunmProcedureId = dunmProcedureId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDunnProcedure() {
		return dunnProcedure;
	}

	public void setDunnProcedure(String dunnProcedure) {
		this.dunnProcedure = dunnProcedure;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}
