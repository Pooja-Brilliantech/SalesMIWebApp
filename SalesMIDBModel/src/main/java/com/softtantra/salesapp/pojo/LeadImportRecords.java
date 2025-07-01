package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lead_import_records")
public class LeadImportRecords implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int user_id;
	private int status;
	private String comment;
	private String first_name;
	private String last_name;
	private String email;
	private String contact_no;
	private String country;
	private String state;
	private String city;
	private int import_id;
	private int company_id;
	private String assign_to;
	private String address;
	private String lead_source;
	private String lead_industry;
	private String website;
	private String designation;
	private String salvation;
	private String lat;
	private String longi;
	private String product_name;
	private String lead_type;
	private String quantity;
	private String company_address;
	private String customer_name;
	private String pincode;
	private String date;
	
	
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getLead_source() {
		return lead_source;
	}
	public void setLead_source(String lead_source) {
		this.lead_source = lead_source;
	}
	public String getLead_industry() {
		return lead_industry;
	}
	public void setLead_industry(String lead_industry) {
		this.lead_industry = lead_industry;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getSalvation() {
		return salvation;
	}
	public void setSalvation(String salvation) {
		this.salvation = salvation;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLongi() {
		return longi;
	}
	public void setLongi(String longi) {
		this.longi = longi;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getLead_type() {
		return lead_type;
	}
	public void setLead_type(String lead_type) {
		this.lead_type = lead_type;
	}
	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
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
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getAssign_to() {
		return assign_to;
	}
	public void setAssign_to(String assign_to) {
		this.assign_to = assign_to;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDate() {
		// TODO Auto-generated method stub
		return date;
	}
	
	public void setDate (String date) {
		this.date = date;
	}
	
	

}
