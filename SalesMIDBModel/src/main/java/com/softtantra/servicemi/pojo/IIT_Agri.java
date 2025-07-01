package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "iit_agri_with_rabbi")
public class IIT_Agri implements Serializable{
	@Id
	@Column(name = "IIT_agri_id")
	@GeneratedValue
	private int IIT_agri_id;

	private String state;

	private String district;

	private String city;

	private String country;

	private String participate_measurements;

	private String answer_to_question;

	private byte[] image;

	private String remark;

	private String village;

	private String lat;

	private String longi;

	private String farm_id;

	private String location;

	private String name;

	private String phone;

	private String survery_no;

	private String company_id;

	private String annual_income;

	private String season;

	@Column(length = 65536)
	private String primary_crops;

	@Column(length = 65536)
	private String area_of_land;

	@Column(length = 65536)
	private String avg_crop_production;

	@Column(length = 65536)
	private String days_after_samplae_collected;

	@Column(length = 65536)
	private String harvesting_procedure;

	@Column(length = 65536)
	private String crop_weighed;

	@Column(length = 65536)
	private String mass_of_residue;

	@Column(length = 65536)
	private String weight_of_sample;

	@Column(length = 65536)
	private String uses_of_crop_residue;

	@Column(length = 65536)
	private String reasons;

	@Column(length = 65536)
	private String approx_days;

	private long created_by;

	@CreationTimestamp
	private Date created_date;;

	private long updated_by;

	@UpdateTimestamp
	private Date updated_date;

	private String status;

	private String image_path;

	private String referenceNumber;

	@Column(length = 65536)
	private String primary_months;

	@Column(length = 65536)
	private String r_diff_crops;

	@Transient
	private String username;

	public int getIIT_agri_id() {
		return IIT_agri_id;
	}

	public void setIIT_agri_id(int iIT_agri_id) {
		IIT_agri_id = iIT_agri_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getParticipate_measurements() {
		return participate_measurements;
	}

	public void setParticipate_measurements(String participate_measurements) {
		this.participate_measurements = participate_measurements;
	}

	public String getAnswer_to_question() {
		return answer_to_question;
	}

	public void setAnswer_to_question(String answer_to_question) {
		this.answer_to_question = answer_to_question;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
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

	public String getFarm_id() {
		return farm_id;
	}

	public void setFarm_id(String farm_id) {
		this.farm_id = farm_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSurvery_no() {
		return survery_no;
	}

	public void setSurvery_no(String survery_no) {
		this.survery_no = survery_no;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getAnnual_income() {
		return annual_income;
	}

	public void setAnnual_income(String annual_income) {
		this.annual_income = annual_income;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getPrimary_crops() {
		return primary_crops;
	}

	public void setPrimary_crops(String primary_crops) {
		this.primary_crops = primary_crops;
	}

	public String getArea_of_land() {
		return area_of_land;
	}

	public void setArea_of_land(String area_of_land) {
		this.area_of_land = area_of_land;
	}

	public String getAvg_crop_production() {
		return avg_crop_production;
	}

	public void setAvg_crop_production(String avg_crop_production) {
		this.avg_crop_production = avg_crop_production;
	}

	public String getDays_after_samplae_collected() {
		return days_after_samplae_collected;
	}

	public void setDays_after_samplae_collected(String days_after_samplae_collected) {
		this.days_after_samplae_collected = days_after_samplae_collected;
	}

	public String getHarvesting_procedure() {
		return harvesting_procedure;
	}

	public void setHarvesting_procedure(String harvesting_procedure) {
		this.harvesting_procedure = harvesting_procedure;
	}

	public String getCrop_weighed() {
		return crop_weighed;
	}

	public void setCrop_weighed(String crop_weighed) {
		this.crop_weighed = crop_weighed;
	}

	public String getMass_of_residue() {
		return mass_of_residue;
	}

	public void setMass_of_residue(String mass_of_residue) {
		this.mass_of_residue = mass_of_residue;
	}

	public String getWeight_of_sample() {
		return weight_of_sample;
	}

	public void setWeight_of_sample(String weight_of_sample) {
		this.weight_of_sample = weight_of_sample;
	}

	public String getUses_of_crop_residue() {
		return uses_of_crop_residue;
	}

	public void setUses_of_crop_residue(String uses_of_crop_residue) {
		this.uses_of_crop_residue = uses_of_crop_residue;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public String getApprox_days() {
		return approx_days;
	}

	public void setApprox_days(String approx_days) {
		this.approx_days = approx_days;
	}

	public long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(long created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(long updated_by) {
		this.updated_by = updated_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getPrimary_months() {
		return primary_months;
	}

	public void setPrimary_months(String primary_months) {
		this.primary_months = primary_months;
	}

	public String getUsername() {
		return username;
	}

	public String getR_diff_crops() {
		return r_diff_crops;
	}

	public void setR_diff_crops(String r_diff_crops) {
		this.r_diff_crops = r_diff_crops;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
