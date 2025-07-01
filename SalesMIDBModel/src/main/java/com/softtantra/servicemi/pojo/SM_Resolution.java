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
@Table(name = "sm_resolution")
public class SM_Resolution implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sm_resolution_id;
	private int fault_area_id;
	private int fault_code_id;
	private int symptom_id;
	private String resolution_code;
	private String resolution_name;
	private int company_id;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;

	@Transient
	private String faultname;

	@Transient
	private String faultcode;

	@Transient
	private String symptomcode;

	@Transient
	private String createdDate;

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getSymptomcode() {
		return symptomcode;
	}

	public void setSymptomcode(String symptomcode) {
		this.symptomcode = symptomcode;
	}

	public String getFaultcode() {
		return faultcode;
	}

	public void setFaultcode(String faultcode) {
		this.faultcode = faultcode;
	}

	public String getFaultname() {
		return faultname;
	}

	public void setFaultname(String faultname) {
		this.faultname = faultname;
	}

	public int getSm_resolution_id() {
		return sm_resolution_id;
	}

	public void setSm_resolution_id(int sm_resolution_id) {
		this.sm_resolution_id = sm_resolution_id;
	}

	public int getFault_area_id() {
		return fault_area_id;
	}

	public void setFault_area_id(int fault_area_id) {
		this.fault_area_id = fault_area_id;
	}

	public int getFault_code_id() {
		return fault_code_id;
	}

	public void setFault_code_id(int fault_code_id) {
		this.fault_code_id = fault_code_id;
	}

	public int getSymptom_id() {
		return symptom_id;
	}

	public void setSymptom_id(int symptom_id) {
		this.symptom_id = symptom_id;
	}

	public String getResolution_code() {
		return resolution_code;
	}

	public void setResolution_code(String resolution_code) {
		this.resolution_code = resolution_code;
	}

	public String getResolution_name() {
		return resolution_name;
	}

	public void setResolution_name(String resolution_name) {
		this.resolution_name = resolution_name;
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
