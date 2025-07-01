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
@Table(name = "sm_servicecenter")
public class SM_ServiceCenter implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sid_id;
	private String serviceCenter_id;
	private String serviceCenter_name;
	private int country_id;
	private int state_id;
	private int city_id;
	private int company_id;// companyid

	@CreationTimestamp
	private Date created_date;;
	private int created_by;
	@UpdateTimestamp
	private Date updated_date;
	private int updated_by;
	private int status;

	@Transient
	private String country_name;

	@Transient
	private String state_name;

	@Transient
	private String city_name;

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getSid_id() {
		return sid_id;
	}

	public void setSid_id(int sid_id) {
		this.sid_id = sid_id;
	}

	public String getServiceCenter_id() {
		return serviceCenter_id;
	}

	public void setServiceCenter_id(String serviceCenter_id) {
		this.serviceCenter_id = serviceCenter_id;
	}

	public String getServiceCenter_name() {
		return serviceCenter_name;
	}

	public void setServiceCenter_name(String serviceCenter_name) {
		this.serviceCenter_name = serviceCenter_name;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public int getState_id() {
		return state_id;
	}

	public void setState_id(int state_id) {
		this.state_id = state_id;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

}
