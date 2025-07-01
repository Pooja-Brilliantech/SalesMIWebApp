package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class IIT_Brick_Display implements Serializable{

	private int IIT_brick_id;
	private String state;
	private String district;
	private String lat;
	private String longi;
	private String city;
	private String kiln_set_up;
	private String cycles_running;
	private String kiln_having_chimney;
	private String kiln_having_fan;
	private String type_of_fan;
	private String different_size_of_bricks;
	private String answer_to_question;
	private String referenceNumber;
	private String kiln_id;
	private String location;
	private String survey_no;
	private String village;
	private String country;
	private String name;
	private String phone;
	private String company_id;
	private String plot_covered;
	private String technology_type;
	private String chimney_type;
	private String air_circulation;
	private String capital_cost;
	private String other_kilns;
	private String months_kiln;
	private String days_complete_one_cycle;
	private String activities;
	private String material_used;
	private String bricks_per_stack;

	private String stacks_in_month;
	private String bricks_produced;
	private String bricks_considered;
	private String fuels_used;
	private String amount;
	private String source;
	private String brick_dimensions;
	private String other_dimensions;
	private String brick_used_activities;
	private String other_states;
	private String is_bricks_supplied;
	private String machines_use;
	private String amount_of_diesel;
	private long created_by;
	@CreationTimestamp
	private Date created_date;;
	private long updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private String status;
	private String image_path;
	private String remark;
	private String participate_measurements;
	private String probe_for_measurement;
	private String survery_no;
	private String agri_residue;
	private String bricks_sizes;

	@Transient
	private String username;

	public int getIIT_brick_id() {
		return IIT_brick_id;
	}

	public void setIIT_brick_id(int iIT_brick_id) {
		IIT_brick_id = iIT_brick_id;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getKiln_set_up() {
		return kiln_set_up;
	}

	public void setKiln_set_up(String kiln_set_up) {
		this.kiln_set_up = kiln_set_up;
	}

	public String getCycles_running() {
		return cycles_running;
	}

	public void setCycles_running(String cycles_running) {
		this.cycles_running = cycles_running;
	}

	public String getKiln_having_chimney() {
		return kiln_having_chimney;
	}

	public void setKiln_having_chimney(String kiln_having_chimney) {
		this.kiln_having_chimney = kiln_having_chimney;
	}

	public String getKiln_having_fan() {
		return kiln_having_fan;
	}

	public void setKiln_having_fan(String kiln_having_fan) {
		this.kiln_having_fan = kiln_having_fan;
	}

	public String getType_of_fan() {
		return type_of_fan;
	}

	public void setType_of_fan(String type_of_fan) {
		this.type_of_fan = type_of_fan;
	}

	public String getDifferent_size_of_bricks() {
		return different_size_of_bricks;
	}

	public void setDifferent_size_of_bricks(String different_size_of_bricks) {
		this.different_size_of_bricks = different_size_of_bricks;
	}

	public String getAnswer_to_question() {
		return answer_to_question;
	}

	public void setAnswer_to_question(String answer_to_question) {
		this.answer_to_question = answer_to_question;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getKiln_id() {
		return kiln_id;
	}

	public void setKiln_id(String kiln_id) {
		this.kiln_id = kiln_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSurvey_no() {
		return survey_no;
	}

	public void setSurvey_no(String survey_no) {
		this.survey_no = survey_no;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getPlot_covered() {
		return plot_covered;
	}

	public void setPlot_covered(String plot_covered) {
		this.plot_covered = plot_covered;
	}

	public String getTechnology_type() {
		return technology_type;
	}

	public void setTechnology_type(String technology_type) {
		this.technology_type = technology_type;
	}

	public String getChimney_type() {
		return chimney_type;
	}

	public void setChimney_type(String chimney_type) {
		this.chimney_type = chimney_type;
	}

	public String getAir_circulation() {
		return air_circulation;
	}

	public void setAir_circulation(String air_circulation) {
		this.air_circulation = air_circulation;
	}

	public String getCapital_cost() {
		return capital_cost;
	}

	public void setCapital_cost(String capital_cost) {
		this.capital_cost = capital_cost;
	}

	public String getOther_kilns() {
		return other_kilns;
	}

	public void setOther_kilns(String other_kilns) {
		this.other_kilns = other_kilns;
	}

	public String getMonths_kiln() {
		return months_kiln;
	}

	public void setMonths_kiln(String months_kiln) {
		this.months_kiln = months_kiln;
	}

	public String getDays_complete_one_cycle() {
		return days_complete_one_cycle;
	}

	public void setDays_complete_one_cycle(String days_complete_one_cycle) {
		this.days_complete_one_cycle = days_complete_one_cycle;
	}

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
	}

	public String getMaterial_used() {
		return material_used;
	}

	public void setMaterial_used(String material_used) {
		this.material_used = material_used;
	}

	public String getBricks_per_stack() {
		return bricks_per_stack;
	}

	public void setBricks_per_stack(String bricks_per_stack) {
		this.bricks_per_stack = bricks_per_stack;
	}

	public String getStacks_in_month() {
		return stacks_in_month;
	}

	public void setStacks_in_month(String stacks_in_month) {
		this.stacks_in_month = stacks_in_month;
	}

	public String getBricks_produced() {
		return bricks_produced;
	}

	public void setBricks_produced(String bricks_produced) {
		this.bricks_produced = bricks_produced;
	}

	public String getBricks_considered() {
		return bricks_considered;
	}

	public void setBricks_considered(String bricks_considered) {
		this.bricks_considered = bricks_considered;
	}

	public String getFuels_used() {
		return fuels_used;
	}

	public void setFuels_used(String fuels_used) {
		this.fuels_used = fuels_used;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getBrick_dimensions() {
		return brick_dimensions;
	}

	public void setBrick_dimensions(String brick_dimensions) {
		this.brick_dimensions = brick_dimensions;
	}

	public String getOther_dimensions() {
		return other_dimensions;
	}

	public void setOther_dimensions(String other_dimensions) {
		this.other_dimensions = other_dimensions;
	}

	public String getBrick_used_activities() {
		return brick_used_activities;
	}

	public void setBrick_used_activities(String brick_used_activities) {
		this.brick_used_activities = brick_used_activities;
	}

	public String getOther_states() {
		return other_states;
	}

	public void setOther_states(String other_states) {
		this.other_states = other_states;
	}

	public String getIs_bricks_supplied() {
		return is_bricks_supplied;
	}

	public void setIs_bricks_supplied(String is_bricks_supplied) {
		this.is_bricks_supplied = is_bricks_supplied;
	}

	public String getMachines_use() {
		return machines_use;
	}

	public void setMachines_use(String machines_use) {
		this.machines_use = machines_use;
	}

	public String getAmount_of_diesel() {
		return amount_of_diesel;
	}

	public void setAmount_of_diesel(String amount_of_diesel) {
		this.amount_of_diesel = amount_of_diesel;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getParticipate_measurements() {
		return participate_measurements;
	}

	public void setParticipate_measurements(String participate_measurements) {
		this.participate_measurements = participate_measurements;
	}

	public String getProbe_for_measurement() {
		return probe_for_measurement;
	}

	public void setProbe_for_measurement(String probe_for_measurement) {
		this.probe_for_measurement = probe_for_measurement;
	}

	public String getSurvery_no() {
		return survery_no;
	}

	public void setSurvery_no(String survery_no) {
		this.survery_no = survery_no;
	}

	public String getAgri_residue() {
		return agri_residue;
	}

	public void setAgri_residue(String agri_residue) {
		this.agri_residue = agri_residue;
	}

	public String getBricks_sizes() {
		return bricks_sizes;
	}

	public void setBricks_sizes(String bricks_sizes) {
		this.bricks_sizes = bricks_sizes;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
