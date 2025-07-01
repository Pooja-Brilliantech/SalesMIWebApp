package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sm_user_location_details")
public class SM_UserLocationDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long location_id;
	private Long company_id;
	private Long user_id;
	private String lat;
	private String longi;
	private Date update_date_time;
	private Double accuracy;
	private double current_battery_level;

	private double speed;
	private int gps_status;

	@Transient
	private String location_no;

	public Double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Double accuracy) {
		this.accuracy = accuracy;
	}

	@Transient
	private BigInteger userId;
	@Transient
	private BigInteger locationId;
	@Transient
	private String userName;

	public Long getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
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

	public Date getUpdate_date_time() {
		return update_date_time;
	}

	public void setUpdate_date_time(Date update_date_time) {
		this.update_date_time = update_date_time;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String activity_name;
	private int status;
	private String address;

	public BigInteger getLocationId() {
		return locationId;
	}

	public void setLocationId(BigInteger locationId) {
		this.locationId = locationId;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocation_no() {
		return location_no;
	}

	public void setLocation_no(String location_no) {
		this.location_no = location_no;
	}

	public double getCurrent_battery_level() {
		return current_battery_level;
	}

	public void setCurrent_battery_level(double current_battery_level) {
		this.current_battery_level = current_battery_level;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getGps_status() {
		return gps_status;
	}

	public void setGps_status(int gps_status) {
		this.gps_status = gps_status;
	}

}
