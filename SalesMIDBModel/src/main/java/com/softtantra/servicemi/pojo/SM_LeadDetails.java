package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.softtantra.salesapp.pojo.LeadHasProducts;


@Entity
@Table(name = "sm_lead_details")
public class SM_LeadDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int lead_id;
	
	@ColumnDefault(value="0")
	private int company_id;
	
	@Column(nullable=true)
	private String first_name;
	
	@Column(nullable=true)
	private String last_name;
	
	@Column(nullable=true)
	private String company_name;
	
	@Column(nullable=true)
	private String company_address;
	
	@Column(nullable=true)
	private String contact_no;
	
	@Column(nullable=true)
	private String email;
	
	@Column(nullable=true)
	private String lead_details;
	
	@Column(nullable=true)
	private String lead_type;
	
	@ColumnDefault(value="0")
	private int assign_user_id;
	
	@ColumnDefault(value="0")
	private int created_by;
	
	@CreationTimestamp
	private Date created_date;
	
	@ColumnDefault(value="0")
	private int updated_by;
	
	@UpdateTimestamp
	private Date updated_date;
	
	@ColumnDefault(value="0")
	private int city;
	
	@ColumnDefault(value="0")
	private int state;
	
	@ColumnDefault(value="0")
	private int country_id;
	
	@Column(nullable=true)
	private String id_range;
	
	@Column(nullable=true)
	private String pincode;
	
	@ColumnDefault(value="0")
	private int source_from;
	
	@ColumnDefault(value="0")
	private int customer_id;
	
	@ColumnDefault(value="0")
	private int opportunity_id;
	
	@Column(nullable=true)
	private String opportunity_IdRange;
	
	public String getOpportunity_IdRange() {
		return opportunity_IdRange;
	}

	public void setOpportunity_IdRange(String opportunity_IdRange) {
		this.opportunity_IdRange = opportunity_IdRange;
	}

	@Transient
	private String created_date_time;
	
	public String getCreated_date_time() {
		return created_date_time;
	}

	public void setCreated_date_time(String created_date_time) {
		this.created_date_time = created_date_time;
	}

	public int getOpportunity_id() {
		return opportunity_id;
	}

	public void setOpportunity_id(int opportunity_id) {
		this.opportunity_id = opportunity_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getSource_from() {
		return source_from;
	}

	public void setSource_from(int source_from) {
		this.source_from = source_from;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Transient
	private ArrayList<String> list = new ArrayList<>();
	

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	public String getAssign_user_name() {
		return assign_user_name;
	}

	public void setAssign_user_name(String assign_user_name) {
		this.assign_user_name = assign_user_name;
	}

	@Transient
	private String user_name;
	@Transient
	private String assign_user_name;

	@Transient
	private String country_name;
	@Transient
	private String state_name;
	@Transient
	private String city_name;

	@Transient
	private String created_name;

	public String getCreated_name() {
		return created_name;
	}

	public void setCreated_name(String created_name) {
		this.created_name = created_name;
	}

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

	@Transient
	private int user;

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	private int status;
	private int close_status;// 0-not closed 1 -closed
	private String close_leadstatus;// Closed Won, Closed Lost
	private String close_comment;
	private String created_location;
	
	private String lead_source;
	private String lead_industry;
	private String website;
	private String designation;
	private String salvation;
	private String address;
	private String lat;
	private String longi;
	private String district;
	private String image_url;
	private String taluka;
	@Transient
	private byte image[];
	
	

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTaluka() {
		return taluka;
	}

	public void setTaluka(String taluka) {
		this.taluka = taluka;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
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

	public int getLead_id() {
		return lead_id;
	}

	public void setLead_id(int lead_id) {
		this.lead_id = lead_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
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

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_address() {
		return company_address;
	}

	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLead_details() {
		return lead_details;
	}

	public void setLead_details(String lead_details) {
		this.lead_details = lead_details;
	}

	public String getLead_type() {
		return lead_type;
	}

	public void setLead_type(String lead_type) {
		this.lead_type = lead_type;
	}

	public int getAssign_user_id() {
		return assign_user_id;
	}

	public void setAssign_user_id(int assign_user_id) {
		this.assign_user_id = assign_user_id;
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

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getClose_status() {
		return close_status;
	}

	public void setClose_status(int close_status) {
		this.close_status = close_status;
	}

	public String getClose_leadstatus() {
		return close_leadstatus;
	}

	public void setClose_leadstatus(String close_leadstatus) {
		this.close_leadstatus = close_leadstatus;
	}

	public String getClose_comment() {
		return close_comment;
	}

	public void setClose_comment(String close_comment) {
		this.close_comment = close_comment;
	}

	public String getCreated_location() {
		return created_location;
	}

	public void setCreated_location(String created_location) {
		this.created_location = created_location;
	}

	private Date close_date;

	public Date getClose_date() {
		return close_date;
	}

	public void setClose_date(Date close_date) {
		this.close_date = close_date;
	}

	@Transient
	private String sclose_date;

	public String getSclose_date() {
		return sclose_date;
	}

	public void setSclose_date(String sclose_date) {
		this.sclose_date = sclose_date;
	}

	private String product_id;

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getId_range() {
		return id_range;
	}

	public void setId_range(String id_range) {
		this.id_range = id_range;
	}

	private String product_name;
	
	@Transient
	List<LeadHasProducts> wsLeadProducts = new  ArrayList<LeadHasProducts>();

	public List<LeadHasProducts> getWsLeadProducts() {
		return wsLeadProducts;
	}

	public void setWsLeadProducts(List<LeadHasProducts> wsLeadProducts) {
		this.wsLeadProducts = wsLeadProducts;
	}

	@Transient
	 private String quantity;

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	private String date;
	
	public String getDate() {
		// TODO Auto-generated method stub
		return date;
	}
	public void setDate(String date) {
		this.date= date;
	}
	

}
