package com.softtantra.servicemi.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sm_fault_code_import_records")
public class SM_FaultCodeImportRecord implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int user_id;
	private int status;
	private String comment;
	private int import_id;
	private int company_id;
	private String fault_code;
	private String fault_name;
	private String fault_area_name;
	private String symptom_name;
	private String symptom_code;

	public String getSymptom_code() {
		return symptom_code;
	}

	public void setSymptom_code(String symptom_code) {
		this.symptom_code = symptom_code;
	}

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

	public String getFault_code() {
		return fault_code;
	}

	public void setFault_code(String fault_code) {
		this.fault_code = fault_code;
	}

	public String getFault_name() {
		return fault_name;
	}

	public void setFault_name(String fault_name) {
		this.fault_name = fault_name;
	}

	public String getFault_area_name() {
		return fault_area_name;
	}

	public void setFault_area_name(String fault_area_name) {
		this.fault_area_name = fault_area_name;
	}

	public String getSymptom_name() {
		return symptom_name;
	}

	public void setSymptom_name(String symptom_name) {
		this.symptom_name = symptom_name;
	}

}
