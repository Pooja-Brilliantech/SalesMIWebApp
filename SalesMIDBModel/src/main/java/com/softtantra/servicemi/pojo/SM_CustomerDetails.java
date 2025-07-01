package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "sm_customer_details")
public class SM_CustomerDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_id;
	private int company_id;
	private String customer_name;
	private String code;
	private String email;
	private String mobile;
	private String phone;
	private String fax;
	private int country_id;
	private int city;
	private int state;
	private String image_link;
	private String firm_type;
	private String bsegment;
	private String locations;
	private double credit_limit;
	private int crerdit_duration;
	private int location_no;

	// for kcm
	private String service_contact;
	private int kcmUploadStatus;

	public int getKcmUploadStatus() {
		return kcmUploadStatus;
	}

	public void setKcmUploadStatus(int kcmUploadStatus) {
		this.kcmUploadStatus = kcmUploadStatus;
	}

	@Transient
	private String country_name;

	public String getService_contact() {
		return service_contact;
	}

	public void setService_contact(String service_contact) {
		this.service_contact = service_contact;
	}

	public String getFirm_type() {
		return firm_type;
	}

	public void setFirm_type(String firm_type) {
		this.firm_type = firm_type;
	}

	public String getBsegment() {
		return bsegment;
	}

	public void setBsegment(String bsegment) {
		this.bsegment = bsegment;
	}

	public String getLocations() {
		return locations;
	}

	public void setLocations(String locations) {
		this.locations = locations;
	}

	public int getLocation_no() {
		return location_no;
	}

	public void setLocation_no(int location_no) {
		this.location_no = location_no;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	@Transient
	private int contacts;
	@Transient
	private String ConcatedContactInfo;
	@Transient
	private String cityId;
	@Transient
	private String stateId;
	@Transient
	private String distributor_name;

	private int storeType;

	private int classType;

	private String area;

	@Transient
	private String manage_store_type_name;

	@Transient
	private String manage_class_name;

	public String getManage_store_type_name() {
		return manage_store_type_name;
	}

	public void setManage_store_type_name(String manage_store_type_name) {
		this.manage_store_type_name = manage_store_type_name;
	}

	public String getManage_class_name() {
		return manage_class_name;
	}

	public void setManage_class_name(String manage_class_name) {
		this.manage_class_name = manage_class_name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	private String gst_no;

	public int getStoreType() {
		return storeType;
	}

	public void setStoreType(int storeType) {
		this.storeType = storeType;
	}

	public int getClassType() {
		return classType;
	}

	public void setClassType(int classType) {
		this.classType = classType;
	}

	public String getGst_no() {
		return gst_no;
	}

	public void setGst_no(String gst_no) {
		this.gst_no = gst_no;
	}

	private String route_name;
	private String pincode;
	private int distributor_id;
	private int route_id;
	private String billing_address;
	private String address;
	private String lat;
	private String longi;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private int new_city;
	private int new_state;
	private int new_country;

	public int getNew_country() {
		return new_country;
	}

	public void setNew_country(int new_country) {
		this.new_country = new_country;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
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

	public int getDistributor_id() {
		return distributor_id;
	}

	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}

	public int getRoute_id() {
		return route_id;
	}

	public void setRoute_id(int route_id) {
		this.route_id = route_id;
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

	public String getImage_link() {
		return image_link;
	}

	public void setImage_link(String image_link) {
		this.image_link = image_link;
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

	public String getConcatedContactInfo() {
		return ConcatedContactInfo;
	}

	public void setConcatedContactInfo(String concatedContactInfo) {
		ConcatedContactInfo = concatedContactInfo;
	}

	public int getContacts() {
		return contacts;
	}

	public void setContacts(int contacts) {
		this.contacts = contacts;
	}

	public int getNew_city() {
		return new_city;
	}

	public void setNew_city(int new_city) {
		this.new_city = new_city;
	}

	public int getNew_state() {
		return new_state;
	}

	public void setNew_state(int new_state) {
		this.new_state = new_state;
	}

	@Transient
	private BigDecimal amount;

	public double getCredit_limit() {
		return credit_limit;
	}

	public void setCredit_limit(double credit_limit) {
		this.credit_limit = credit_limit;
	}

	public int getCrerdit_duration() {
		return crerdit_duration;
	}

	public void setCrerdit_duration(int crerdit_duration) {
		this.crerdit_duration = crerdit_duration;
	}

	@Override
	public String toString() {
		return "SM_CustomerDetails [customer_id=" + customer_id + ", company_id=" + company_id + ", customer_name="
				+ customer_name + ", code=" + code + ", email=" + email + ", mobile=" + mobile + ", phone=" + phone
				+ ", fax=" + fax + ", country_id=" + country_id + ", city=" + city + ", state=" + state
				+ ", image_link=" + image_link + ", firm_type=" + firm_type + ", bsegment=" + bsegment + ", locations="
				+ locations + ", credit_limit=" + credit_limit + ", crerdit_duration=" + crerdit_duration
				+ ", location_no=" + location_no + ", country_name=" + country_name + ", contacts=" + contacts
				+ ", ConcatedContactInfo=" + ConcatedContactInfo + ", cityId=" + cityId + ", stateId=" + stateId
				+ ", distributor_name=" + distributor_name + ", storeType=" + storeType + ", classType=" + classType
				+ ", area=" + area + ", manage_store_type_name=" + manage_store_type_name + ", manage_class_name="
				+ manage_class_name + ", gst_no=" + gst_no + ", route_name=" + route_name + ", pincode=" + pincode
				+ ", distributor_id=" + distributor_id + ", route_id=" + route_id + ", billing_address="
				+ billing_address + ", address=" + address + ", lat=" + lat + ", longi=" + longi + ", status=" + status
				+ ", created_by=" + created_by + ", created_date=" + created_date + ", updated_by=" + updated_by
				+ ", updated_date=" + updated_date + ", new_city=" + new_city + ", new_state=" + new_state
				+ ", new_country=" + new_country + ", amount=" + amount + "]";
	}

}
