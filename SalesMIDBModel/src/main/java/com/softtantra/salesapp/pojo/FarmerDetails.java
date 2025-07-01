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

@Entity
@Table(name = "farmer_details")
public class FarmerDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int farmer_id;
	private int company_id;
	private int user_id;
	private String name;
	private String email_id;
	private String mobile;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private Date farmeradd_datetime;
	private String image_url;

	private String address_taluka;
	private String address_district;
	private int pincode;
	private String contact_number;
	private String land_holding;
	private String source_of_irrigation_water;
	private String major_crop;
	private String seasonal_cropping_patern; // 4 options
	private String perennial_cropping_patern;// 4 options
	private String major_issue_in_crop;
	private String products_recommend;
	private String action_plan_of_field_staff;
	private String farmers_feedback;
	private int farmers_reference_relation;// 1.Relative, 2. Friends 3.cothers.
	private String farmers_reference_name;
	private String farmers_reference_contact;
	private String farmers_reference_number;
	private String farmers_address;

	public String getFarmers_address() {
		return farmers_address;
	}

	public void setFarmers_address(String farmers_address) {
		this.farmers_address = farmers_address;
	}

	@Transient
	private String farmers_date;

	@Transient
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFarmers_date() {
		return farmers_date;
	}

	public void setFarmers_date(String farmers_date) {
		this.farmers_date = farmers_date;
	}

	public String getAddress_taluka() {
		return address_taluka;
	}

	public void setAddress_taluka(String address_taluka) {
		this.address_taluka = address_taluka;
	}

	public String getAddress_district() {
		return address_district;
	}

	public void setAddress_district(String address_district) {
		this.address_district = address_district;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public String getLand_holding() {
		return land_holding;
	}

	public void setLand_holding(String land_holding) {
		this.land_holding = land_holding;
	}

	public String getSource_of_irrigation_water() {
		return source_of_irrigation_water;
	}

	public void setSource_of_irrigation_water(String source_of_irrigation_water) {
		this.source_of_irrigation_water = source_of_irrigation_water;
	}

	public String getMajor_crop() {
		return major_crop;
	}

	public void setMajor_crop(String major_crop) {
		this.major_crop = major_crop;
	}

	public String getSeasonal_cropping_patern() {
		return seasonal_cropping_patern;
	}

	public void setSeasonal_cropping_patern(String seasonal_cropping_patern) {
		this.seasonal_cropping_patern = seasonal_cropping_patern;
	}

	public String getPerennial_cropping_patern() {
		return perennial_cropping_patern;
	}

	public void setPerennial_cropping_patern(String perennial_cropping_patern) {
		this.perennial_cropping_patern = perennial_cropping_patern;
	}

	public String getMajor_issue_in_crop() {
		return major_issue_in_crop;
	}

	public void setMajor_issue_in_crop(String major_issue_in_crop) {
		this.major_issue_in_crop = major_issue_in_crop;
	}

	public String getProducts_recommend() {
		return products_recommend;
	}

	public void setProducts_recommend(String products_recommend) {
		this.products_recommend = products_recommend;
	}

	public String getAction_plan_of_field_staff() {
		return action_plan_of_field_staff;
	}

	public void setAction_plan_of_field_staff(String action_plan_of_field_staff) {
		this.action_plan_of_field_staff = action_plan_of_field_staff;
	}

	public String getFarmers_feedback() {
		return farmers_feedback;
	}

	public void setFarmers_feedback(String farmers_feedback) {
		this.farmers_feedback = farmers_feedback;
	}

	public int getFarmers_reference_relation() {
		return farmers_reference_relation;
	}

	public void setFarmers_reference_relation(int farmers_reference_relation) {
		this.farmers_reference_relation = farmers_reference_relation;
	}

	public String getFarmers_reference_name() {
		return farmers_reference_name;
	}

	public void setFarmers_reference_name(String farmers_reference_name) {
		this.farmers_reference_name = farmers_reference_name;
	}

	public String getFarmers_reference_contact() {
		return farmers_reference_contact;
	}

	public void setFarmers_reference_contact(String farmers_reference_contact) {
		this.farmers_reference_contact = farmers_reference_contact;
	}

	public String getFarmers_reference_number() {
		return farmers_reference_number;
	}

	public void setFarmers_reference_number(String farmers_reference_number) {
		this.farmers_reference_number = farmers_reference_number;
	}

	public int getFarmer_id() {
		return farmer_id;
	}

	public void setFarmer_id(int farmer_id) {
		this.farmer_id = farmer_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public Date getFarmeradd_datetime() {
		return farmeradd_datetime;
	}

	public void setFarmeradd_datetime(Date farmeradd_datetime) {
		this.farmeradd_datetime = farmeradd_datetime;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
}
