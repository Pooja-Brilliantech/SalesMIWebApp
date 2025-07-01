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
@Table(name = "iit_vehicle")
public class IIT_Vehicle implements Serializable{
	@Id
	@Column(name = "IIT_vehicle_id")
	@GeneratedValue
	private int IIT_vehicle_id;
	private String location;
	private String type_of_vehicle;
	private String type_of_fule;
	private String emission_norms;
	private String make_model_year;
	private String engine_capacity;
	private String mileage;
	private String referenceNumber;
	private String company_id;
	private String name;
	private String survery_no;
	private String village;
	private String contact;
	private String axcel;
	private String remark;
	private String Speed;
	private String odometer_reading;
	private String daily_distance_travel;
	private String fuel_consumption;
	private String maintenance;
	private String vehicle_lifetime;
	private String vehicle_no;
	private String vehicle_company;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private String status;
	private String image_path;
	private String answer_to_question;
	private String state;
	private String city;
	private String lat;
	private String longi;

	@Transient
	private String username;

	private String country;
	private String participant_measurements;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getParticipant_measurements() {
		return participant_measurements;
	}

	public void setParticipant_measurements(String participant_measurements) {
		this.participant_measurements = participant_measurements;
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

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getSurvery_no() {
		return survery_no;
	}

	public void setSurvery_no(String survery_no) {
		this.survery_no = survery_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAxcel() {
		return axcel;
	}

	public void setAxcel(String axcel) {
		this.axcel = axcel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCompny_id() {
		return company_id;
	}

	public void setCompny_id(String compny_id) {
		this.company_id = compny_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public int getIIT_vehicle_id() {
		return IIT_vehicle_id;
	}

	public void setIIT_vehicle_id(int iIT_vehicle_id) {
		IIT_vehicle_id = iIT_vehicle_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType_of_vehicle() {
		return type_of_vehicle;
	}

	public void setType_of_vehicle(String type_of_vehicle) {
		this.type_of_vehicle = type_of_vehicle;
	}

	public String getType_of_fule() {
		return type_of_fule;
	}

	public void setType_of_fule(String type_of_fule) {
		this.type_of_fule = type_of_fule;
	}

	public String getEmission_norms() {
		return emission_norms;
	}

	public void setEmission_norms(String emission_norms) {
		this.emission_norms = emission_norms;
	}

	public String getMake_model_year() {
		return make_model_year;
	}

	public void setMake_model_year(String make_model_year) {
		this.make_model_year = make_model_year;
	}

	public String getEngine_capacity() {
		return engine_capacity;
	}

	public void setEngine_capacity(String engine_capacity) {
		this.engine_capacity = engine_capacity;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getSpeed() {
		return Speed;
	}

	public void setSpeed(String speed) {
		Speed = speed;
	}

	public String getOdometer_reading() {
		return odometer_reading;
	}

	public void setOdometer_reading(String odometer_reading) {
		this.odometer_reading = odometer_reading;
	}

	public String getDaily_distance_travel() {
		return daily_distance_travel;
	}

	public void setDaily_distance_travel(String daily_distance_travel) {
		this.daily_distance_travel = daily_distance_travel;
	}

	public String getFuel_consumption() {
		return fuel_consumption;
	}

	public void setFuel_consumption(String fuel_consumption) {
		this.fuel_consumption = fuel_consumption;
	}

	public String getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}

	public String getVehicle_lifetime() {
		return vehicle_lifetime;
	}

	public void setVehicle_lifetime(String vehicle_lifetime) {
		this.vehicle_lifetime = vehicle_lifetime;
	}

	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}

	public String getVehicle_company() {
		return vehicle_company;
	}

	public void setVehicle_company(String vehicle_company) {
		this.vehicle_company = vehicle_company;
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

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getAnswer_to_question() {
		return answer_to_question;
	}

	public void setAnswer_to_question(String answer_to_question) {
		this.answer_to_question = answer_to_question;
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
}
