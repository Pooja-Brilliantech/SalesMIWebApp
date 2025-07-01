package com.softtantra.servicemi.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sm_resolutionimportrecords")
public class SM_ResolutionImportRecords implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int user_id;
	private int status;
	private String comment;
	private int import_id;
	private int company_id;

	private String resolution_code;
	private String resolution_name;
	private String fault_code_name;
	private String fault_area_name;
	private String symptom_code;

	public int getRecords_id() {
		return records_id;
	}

	public void setRecords_id(int records_id) {
		this.records_id = records_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getImport_id() {
		return import_id;
	}

	public void setImport_id(int import_id) {
		this.import_id = import_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
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

	public String getFault_code_name() {
		return fault_code_name;
	}

	public void setFault_code_name(String fault_code_name) {
		this.fault_code_name = fault_code_name;
	}

	public String getFault_area_name() {
		return fault_area_name;
	}

	public void setFault_area_name(String fault_area_name) {
		this.fault_area_name = fault_area_name;
	}

	public String getSymptom_code() {
		return symptom_code;
	}

	public void setSymptom_code(String symptom_code) {
		this.symptom_code = symptom_code;
	}

}
