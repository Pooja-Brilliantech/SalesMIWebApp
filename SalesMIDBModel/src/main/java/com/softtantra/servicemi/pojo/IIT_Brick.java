package com.softtantra.servicemi.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "iit_brick")
public class IIT_Brick implements Serializable{

	@Id
	@Column(name = "IIT_brick_id")
	@GeneratedValue
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
