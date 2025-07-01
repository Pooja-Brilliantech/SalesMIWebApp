package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "sm_user_details")
public class SM_User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	private int company_id;
	private String first_name;
	private String last_name;
	private String email;
	private int role_id;
	private String time_zone;
	private String mobile_no;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private int state;
	private int city;
	private int reporting_role_id;
	private int reporting_manager_id;
	private int distributor_id;
	private String field1;
	private String field2;
	private String profile_img;
	private String signature;

	private Double travaling_rate;
	private int cost;
	private int country_id;

	private String institute_code;

	// added by omkar
	private String lat;
	private String longi;
	private Date in_time;
	private Date out_time;
	private int inout_type;
	private double km_travelled;
	private double current_battery_level;
	private int gps_status;
	private double speed;
	private String location;

	private String user_code;

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getInstitute_code() {
		return institute_code;
	}

	public void setInstitute_code(String institute_code) {
		this.institute_code = institute_code;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	@Transient
	private String state_name;
	@Transient
	private String city_name;
	@Transient
	private String routes;
	@Transient
	private String password;

	@Transient
	private int stateId;

	@Transient
	private int cityId;

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	@Transient
	private String country_name;

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	@Transient
	private String role_name;
	@Transient
	private Date last_login;

	@Transient
	private String created_date1;

	public Double getTravaling_rate() {
		return travaling_rate;
	}

	public void setTravaling_rate(Double travaling_rate) {
		this.travaling_rate = travaling_rate;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getCreated_date1() {
		return created_date1;
	}

	public void setCreated_date1(String created_date1) {
		this.created_date1 = created_date1;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getTime_zone() {
		return time_zone;
	}

	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getReporting_role_id() {
		return reporting_role_id;
	}

	public void setReporting_role_id(int reporting_role_id) {
		this.reporting_role_id = reporting_role_id;
	}

	public int getReporting_manager_id() {
		return reporting_manager_id;
	}

	public void setReporting_manager_id(int reporting_manager_id) {
		this.reporting_manager_id = reporting_manager_id;
	}

	public int getDistributor_id() {
		return distributor_id;
	}

	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
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

	public String getRoutes() {
		return routes;
	}

	public void setRoutes(String routes) {
		this.routes = routes;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "total_leaves", columnDefinition = "int default '0'")
	private int total_leaves;
	@Column(name = "balance_leaves", columnDefinition = "int default '0'")
	private int balance_leaves;

	public int getTotal_leaves() {
		return total_leaves;
	}

	public void setTotal_leaves(int total_leaves) {
		this.total_leaves = total_leaves;
	}

	public int getBalance_leaves() {
		return balance_leaves;
	}

	public void setBalance_leaves(int balance_leaves) {
		this.balance_leaves = balance_leaves;
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

	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}

	public Date getOut_time() {
		return out_time;
	}

	public void setOut_time(Date out_time) {
		this.out_time = out_time;
	}

	public int getInout_type() {
		return inout_type;
	}

	public void setInout_type(int inout_type) {
		this.inout_type = inout_type;
	}

	public double getKm_travelled() {
		return km_travelled;
	}

	public void setKm_travelled(double km_travelled) {
		this.km_travelled = km_travelled;
	}

	public double getCurrent_battery_level() {
		return current_battery_level;
	}

	public void setCurrent_battery_level(double current_battery_level) {
		this.current_battery_level = current_battery_level;
	}

	public int getGps_status() {
		return gps_status;
	}

	public void setGps_status(int gps_status) {
		this.gps_status = gps_status;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
