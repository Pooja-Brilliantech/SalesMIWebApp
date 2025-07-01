package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class IIT_AgriDisplay implements Serializable{

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

	private String primary_crops;

	private String area_of_land;

	private String avg_crop_production;

	private String days_after_samplae_collected;

	private String harvesting_procedure;

	private String crop_weighed;

	private String mass_of_residue;

	private String weight_of_sample;

	private String uses_of_crop_residue;

	private String reasons;

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

	private String primary_months;

	private String r_diff_crops;

	private String k_days_after_samplae_collected;

	private String k_season;

	private String k_primary_crops;

	private String k_area_of_land;

	private String k_avg_crop_production;

	private String k_harvesting_procedure;

	private String k_crop_weighed;

	private String k_mass_of_residue;

	private String k_weight_of_sample;

	private String k_uses_of_crop_residue;

	private String k_reasons;

	private String k_approx_days;

	private String k_primary_months;

	private String k_diff_crops;

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

	public String getR_diff_crops() {
		return r_diff_crops;
	}

	public void setR_diff_crops(String r_diff_crops) {
		this.r_diff_crops = r_diff_crops;
	}

	public String getK_days_after_samplae_collected() {
		return k_days_after_samplae_collected;
	}

	public void setK_days_after_samplae_collected(String k_days_after_samplae_collected) {
		this.k_days_after_samplae_collected = k_days_after_samplae_collected;
	}

	public String getK_season() {
		return k_season;
	}

	public void setK_season(String k_season) {
		this.k_season = k_season;
	}

	public String getK_primary_crops() {
		return k_primary_crops;
	}

	public void setK_primary_crops(String k_primary_crops) {
		this.k_primary_crops = k_primary_crops;
	}

	public String getK_area_of_land() {
		return k_area_of_land;
	}

	public void setK_area_of_land(String k_area_of_land) {
		this.k_area_of_land = k_area_of_land;
	}

	public String getK_avg_crop_production() {
		return k_avg_crop_production;
	}

	public void setK_avg_crop_production(String k_avg_crop_production) {
		this.k_avg_crop_production = k_avg_crop_production;
	}

	public String getK_harvesting_procedure() {
		return k_harvesting_procedure;
	}

	public void setK_harvesting_procedure(String k_harvesting_procedure) {
		this.k_harvesting_procedure = k_harvesting_procedure;
	}

	public String getK_crop_weighed() {
		return k_crop_weighed;
	}

	public void setK_crop_weighed(String k_crop_weighed) {
		this.k_crop_weighed = k_crop_weighed;
	}

	public String getK_mass_of_residue() {
		return k_mass_of_residue;
	}

	public void setK_mass_of_residue(String k_mass_of_residue) {
		this.k_mass_of_residue = k_mass_of_residue;
	}

	public String getK_weight_of_sample() {
		return k_weight_of_sample;
	}

	public void setK_weight_of_sample(String k_weight_of_sample) {
		this.k_weight_of_sample = k_weight_of_sample;
	}

	public String getK_uses_of_crop_residue() {
		return k_uses_of_crop_residue;
	}

	public void setK_uses_of_crop_residue(String k_uses_of_crop_residue) {
		this.k_uses_of_crop_residue = k_uses_of_crop_residue;
	}

	public String getK_reasons() {
		return k_reasons;
	}

	public void setK_reasons(String k_reasons) {
		this.k_reasons = k_reasons;
	}

	public String getK_approx_days() {
		return k_approx_days;
	}

	public void setK_approx_days(String k_approx_days) {
		this.k_approx_days = k_approx_days;
	}

	public String getK_primary_months() {
		return k_primary_months;
	}

	public void setK_primary_months(String k_primary_months) {
		this.k_primary_months = k_primary_months;
	}

	public String getK_diff_crops() {
		return k_diff_crops;
	}

	public void setK_diff_crops(String k_diff_crops) {
		this.k_diff_crops = k_diff_crops;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
