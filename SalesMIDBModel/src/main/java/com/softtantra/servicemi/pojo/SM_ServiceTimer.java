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
@Table(name = "sm_service_timer")
public class SM_ServiceTimer implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int timer_id;
	private int timer_type; // 1 for TrivelerTime && 2 for JobTime
	private int time_type; // 1 for start time && 2 for end time
	private int company_id;
	private int service_id;
	private int customer_id;
	private Date timer_date;
	private String location;
	private String lat;
	private String longi;
	private int created_by;
	private int updated_by;
	@CreationTimestamp
	private Date created_date;;
	@UpdateTimestamp
	private Date updated_date;
	@Transient
	private String timer_type_name;
	@Transient
	private String startDate;
	@Transient
	private String endDate;
	@Transient
	private String duration;

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTimer_type_name() {
		return timer_type_name;
	}

	public void setTimer_type_name(String timer_type_name) {
		this.timer_type_name = timer_type_name;
	}

	public int getTimer_id() {
		return timer_id;
	}

	public void setTimer_id(int timer_id) {
		this.timer_id = timer_id;
	}

	public int getTimer_type() {
		return timer_type;
	}

	public void setTimer_type(int timer_type) {
		this.timer_type = timer_type;
	}

	public int getTime_type() {
		return time_type;
	}

	public void setTime_type(int time_type) {
		this.time_type = time_type;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public Date getTimer_date() {
		return timer_date;
	}

	public void setTimer_date(Date timer_date) {
		this.timer_date = timer_date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

}
