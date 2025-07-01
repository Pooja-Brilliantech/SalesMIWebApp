package com.softtantra.servicemi.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sm_service_sub_status")
public class SM_Service_Sub_Status implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sub_status_id;
	private int company_id;
	private String status_name;
	private String sub_status_name;
	private int status;
	private String pendingReasonCode;
	private String pendingSubReasonCode;
	private String pendingSubReason;

	public int getSub_status_id() {
		return sub_status_id;
	}

	public void setSub_status_id(int sub_status_id) {
		this.sub_status_id = sub_status_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	public String getSub_status_name() {
		return sub_status_name;
	}

	public void setSub_status_name(String sub_status_name) {
		this.sub_status_name = sub_status_name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPendingReasonCode() {
		return pendingReasonCode;
	}

	public void setPendingReasonCode(String pendingReasonCode) {
		this.pendingReasonCode = pendingReasonCode;
	}

	public String getPendingSubReasonCode() {
		return pendingSubReasonCode;
	}

	public void setPendingSubReasonCode(String pendingSubReasonCode) {
		this.pendingSubReasonCode = pendingSubReasonCode;
	}

	public String getPendingSubReason() {
		return pendingSubReason;
	}

	public void setPendingSubReason(String pendingSubReason) {
		this.pendingSubReason = pendingSubReason;
	}

}
