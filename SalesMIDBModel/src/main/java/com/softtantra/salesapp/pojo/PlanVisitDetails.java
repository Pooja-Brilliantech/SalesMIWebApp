package com.softtantra.salesapp.pojo;

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
@Table(name = "plan_visit_details")
public class PlanVisitDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int plan_visit_id;
	private int company_id;
	private int customer_id;
	private int user_id;
	private Date app_created_date;
	private Date plan_date;
	private String plan_details;
	private String address;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private double deviation;
	private String coTravellers;
	@Transient
	private String user_name;
	@Transient
	private String cust_name;

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPlan_visit_id() {
		return plan_visit_id;
	}

	public void setPlan_visit_id(int plan_visit_id) {
		this.plan_visit_id = plan_visit_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public Date getApp_created_date() {
		return app_created_date;
	}

	public void setApp_created_date(Date app_created_date) {
		this.app_created_date = app_created_date;
	}

	public Date getPlan_date() {
		return plan_date;
	}

	public void setPlan_date(Date plan_date) {
		this.plan_date = plan_date;
	}

	public String getPlan_details() {
		return plan_details;
	}

	public void setPlan_details(String plan_details) {
		this.plan_details = plan_details;
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

	public double getDeviation() {
		return deviation;
	}

	public void setDeviation(double deviation) {
		this.deviation = deviation;
	}

	public String getCoTravellers() {
		return coTravellers;
	}

	public void setCoTravellers(String coTravellers) {
		this.coTravellers = coTravellers;
	}
}
