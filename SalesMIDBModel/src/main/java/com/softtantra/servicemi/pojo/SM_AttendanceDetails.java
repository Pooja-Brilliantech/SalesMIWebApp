package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "sm_attendance_details")
public class SM_AttendanceDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long attendance_id;

	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private int device_details_id;
	private double km_travelled;
	private int company_id;
	private Long user_id;
	private int inout_type;
	private Date inout_time;
	private String inout_location_name;
	private String inout_lat;
	private String inout_longi;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;

	private int flag;
	private int retry_attempt;
	private Date location_updated_time;

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getRetry_attempt() {
		return retry_attempt;
	}

	public void setRetry_attempt(int retry_attempt) {
		this.retry_attempt = retry_attempt;
	}

	public Date getLocation_updated_time() {
		return location_updated_time;
	}

	public void setLocation_updated_time(Date location_updated_time) {
		this.location_updated_time = location_updated_time;
	}

	@Transient
	private String user_name;
	@Transient
	private String out_location;
	@Transient
	private String duration;

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getOut_location() {
		return out_location;
	}

	public void setOut_location(String out_location) {
		this.out_location = out_location;
	}

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

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public int getInout_type() {
		return inout_type;
	}

	public void setInout_type(int inout_type) {
		this.inout_type = inout_type;
	}

	public Date getInout_time() {
		return inout_time;
	}

	public void setInout_time(Date inout_time) {
		this.inout_time = inout_time;
	}

	public String getInout_location_name() {
		return inout_location_name;
	}

	public void setInout_location_name(String inout_location_name) {
		this.inout_location_name = inout_location_name;
	}

	public String getInout_lat() {
		return inout_lat;
	}

	public void setInout_lat(String inout_lat) {
		this.inout_lat = inout_lat;
	}

	public String getInout_longi() {
		return inout_longi;
	}

	public void setInout_longi(String inout_longi) {
		this.inout_longi = inout_longi;
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

	public int getDevice_details_id() {
		return device_details_id;
	}

	public void setDevice_details_id(int device_details_id) {
		this.device_details_id = device_details_id;
	}

	public double getKm_travelled() {
		return km_travelled;
	}

	public void setKm_travelled(double km_travelled) {
		this.km_travelled = km_travelled;
	}
}
