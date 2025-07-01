package com.softtantra.servicemi.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sm_customer_import_records")
public class SM_CustomerImportRecords implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int user_id;
	private int status;
	private String comment;
	private int import_id;
	private int company_id;

	private String customer_name;
	private String email;
	private String mobile;
	private String phone;
	private String fax;
	private String city;
	private String state;

	private String distributor_name;
	private String route_name;
	private String pincode;

	private String billing_address;
	private String address;
	private String lat;
	private String longi;
	private String code;

	private String country;
	private String storeType;
	private String classType;

	private String gst_no;
	private String area;

	public String getGst_no() {
		return gst_no;
	}

	public void setGst_no(String gst_no) {
		this.gst_no = gst_no;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistributor_name() {
		return distributor_name;
	}

	public void setDistributor_name(String distributor_name) {
		this.distributor_name = distributor_name;
	}

	public String getRoute_name() {
		return route_name;
	}

	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getBilling_address() {
		return billing_address;
	}

	public void setBilling_address(String billing_address) {
		this.billing_address = billing_address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

}
