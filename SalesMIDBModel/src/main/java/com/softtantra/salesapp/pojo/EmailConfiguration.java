package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email_configuration")
public class EmailConfiguration implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int company_id;
	private Date created_date;
	private int created_by;
	private int status;
	private String from_email;
	private String from_password;
	private String salesorder_email;
	private String pricing_email;
	private int port_no;
	private String host_name;
	private Date updated_date;
	private int isSSL=0;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getFrom_email() {
		return from_email;
	}
	public void setFrom_email(String from_email) {
		this.from_email = from_email;
	}
	public String getFrom_password() {
		return from_password;
	}
	public void setFrom_password(String from_password) {
		this.from_password = from_password;
	}
	public String getSalesorder_email() {
		return salesorder_email;
	}
	public void setSalesorder_email(String salesorder_email) {
		this.salesorder_email = salesorder_email;
	}
	public String getPricing_email() {
		return pricing_email;
	}
	public void setPricing_email(String pricing_email) {
		this.pricing_email = pricing_email;
	}
	public int getPort_no() {
		return port_no;
	}
	public void setPort_no(int port_no) {
		this.port_no = port_no;
	}
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public int getIsSSL() {
		return isSSL;
	}
	public void setIsSSL(int isSSL) {
		this.isSSL = isSSL;
	}

}
