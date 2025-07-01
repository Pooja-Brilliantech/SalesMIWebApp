package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "attendance_register")
public class AttendanceRegister implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long attendance_id;
	
	@ColumnDefault(value="0")
	private int company_id;
	
	@ColumnDefault(value="0")
	private int user_id;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	@CreationTimestamp
	private Date created_date;
	
	@ColumnDefault(value="0")
	private int retry_attempt = 0;
	
	@ColumnDefault(value="1")
	private int status;
	
	@ColumnDefault(value="0")
	private int created_by;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	@Column(nullable = true)
	private Date in_time;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Kolkata")
	@Column(nullable = true)
	private Date out_time;
	
	@Column(nullable = true)
	private String in_location_name;
	
	@Column(nullable = true)
	private String out_location_name;
	
	@Column(nullable = true)
	private String in_lat;
	
	@Column(nullable = true)
	private String in_longi;
	
	@Column(nullable = true)
	private String out_lat;
	
	@Column(nullable = true)
	private String out_longi;
	
	@ColumnDefault(value="0")
	private double in_accuracy;
	
	@ColumnDefault(value="0")
	private double out_accuracy;
	
	@Column(nullable = true)
	private String in_city;
	
	@Column(nullable = true)
	private String in_state;
	
	@Column(nullable = true)
	private String out_state;
	
	@Column(nullable = true)
	private String out_city;
	
	@ColumnDefault(value="0")
	private double km_travelled;
	
	@ColumnDefault(value="0")
	private int in_nearest_company_location;
	
	@ColumnDefault(value="0")
	private int out_nearest_company_location;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	@UpdateTimestamp
	private Date updated_date;
	
	@ColumnDefault(value="0")
	private int device_details_id;
	
	@ColumnDefault(value="0")
	private int flag;
	
	@Column(nullable = true)
	private String in_selfieImage;
	
	@Column(nullable = true)
	private String out_selfieImage;
	
	@Column(nullable = true)
	private String entryTiming;
	
	@Column(nullable = true)
	private String exitTiming;
	
	@Column(nullable = false)
	private String userid_intime;
	
	public String getEntryTiming() {
		return entryTiming;
	}

	public void setEntryTiming(String entryTiming) {
		this.entryTiming = entryTiming;
	}

	public String getExitTiming() {
		return exitTiming;
	}

	public void setExitTiming(String exitTiming) {
		this.exitTiming = exitTiming;
	}

	@Transient
	private String dayStatus;
	
	@Transient
	private String user_name;
	
	@Transient
	private byte[] in_image;
	
	@Transient
	private byte[] out_image;
	
	@Transient
	private String in_nearest_company_location_name;
	
	@Transient
	private String out_nearest_company_location_name;
	
	@Transient
	private String role_name;
	
	@Transient
	private String divisionName;
	
	@Transient
	private String mobileNumber;
	
	@Transient
	private String countryName;
	
	@Transient
	private String stateName;
	
	@Transient
	private String cityName;
	
	@Transient
	private String employeeCode;
	
	@Transient
	private String zoneName;
	
	@Transient
	private String email;
	
	
	public long getAttendance_id() {
		return attendance_id;
	}

	public void setAttendance_id(long attendance_id) {
		this.attendance_id = attendance_id;
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

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public int getRetry_attempt() {
		return retry_attempt;
	}

	public void setRetry_attempt(int retry_attempt) {
		this.retry_attempt = retry_attempt;
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

	public String getIn_location_name() {
		return in_location_name;
	}

	public void setIn_location_name(String in_location_name) {
		this.in_location_name = in_location_name;
	}

	public String getOut_location_name() {
		return out_location_name;
	}

	public void setOut_location_name(String out_location_name) {
		this.out_location_name = out_location_name;
	}

	public String getIn_lat() {
		return in_lat;
	}

	public void setIn_lat(String in_lat) {
		this.in_lat = in_lat;
	}

	public String getIn_longi() {
		return in_longi;
	}

	public void setIn_longi(String in_longi) {
		this.in_longi = in_longi;
	}

	public String getOut_lat() {
		return out_lat;
	}

	public void setOut_lat(String out_lat) {
		this.out_lat = out_lat;
	}

	public String getOut_longi() {
		return out_longi;
	}

	public void setOut_longi(String out_longi) {
		this.out_longi = out_longi;
	}


	public String getIn_city() {
		return in_city;
	}

	public void setIn_city(String in_city) {
		this.in_city = in_city;
	}

	public String getIn_state() {
		return in_state;
	}

	public void setIn_state(String in_state) {
		this.in_state = in_state;
	}

	public String getOut_state() {
		return out_state;
	}

	public void setOut_state(String out_state) {
		this.out_state = out_state;
	}

	public String getOut_city() {
		return out_city;
	}

	public void setOut_city(String out_city) {
		this.out_city = out_city;
	}

	public double getKm_travelled() {
		return km_travelled;
	}

	public void setKm_travelled(double km_travelled) {
		this.km_travelled = km_travelled;
	}

	public String getDayStatus() {
		return dayStatus;
	}

	public void setDayStatus(String dayStatus) {
		this.dayStatus = dayStatus;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public double getIn_accuracy() {
		return in_accuracy;
	}

	public void setIn_accuracy(double in_accuracy) {
		this.in_accuracy = in_accuracy;
	}

	public double getOut_accuracy() {
		return out_accuracy;
	}

	public void setOut_accuracy(double out_accuracy) {
		this.out_accuracy = out_accuracy;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public int getDevice_details_id() {
		return device_details_id;
	}

	public void setDevice_details_id(int device_details_id) {
		this.device_details_id = device_details_id;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getIn_selfieImage() {
		return in_selfieImage;
	}

	public void setIn_selfieImage(String in_selfieImage) {
		this.in_selfieImage = in_selfieImage;
	}

	public String getOut_selfieImage() {
		return out_selfieImage;
	}

	public void setOut_selfieImage(String out_selfieImage) {
		this.out_selfieImage = out_selfieImage;
	}

	public byte[] getIn_image() {
		return in_image;
	}

	public void setIn_image(byte[] in_image) {
		this.in_image = in_image;
	}

	public byte[] getOut_image() {
		return out_image;
	}

	public void setOut_image(byte[] out_image) {
		this.out_image = out_image;
	}

	public int getIn_nearest_company_location() {
		return in_nearest_company_location;
	}

	public void setIn_nearest_company_location(int in_nearest_company_location) {
		this.in_nearest_company_location = in_nearest_company_location;
	}

	public int getOut_nearest_company_location() {
		return out_nearest_company_location;
	}

	public void setOut_nearest_company_location(int out_nearest_company_location) {
		this.out_nearest_company_location = out_nearest_company_location;
	}

	public String getIn_nearest_company_location_name() {
		return in_nearest_company_location_name;
	}

	public void setIn_nearest_company_location_name(String in_nearest_company_location_name) {
		this.in_nearest_company_location_name = in_nearest_company_location_name;
	}

	public String getOut_nearest_company_location_name() {
		return out_nearest_company_location_name;
	}

	public void setOut_nearest_company_location_name(String out_nearest_company_location_name) {
		this.out_nearest_company_location_name = out_nearest_company_location_name;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getUserid_intime() {
		return userid_intime;
	}

	public void setUserid_intime(String userid_intime) {
		this.userid_intime = userid_intime;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
