package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "checkincheckoutdetails")
public class CkeckInCheckOutDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long checkIncheckOut_id;

	private String customer_id;

	private Date checkin_time;
	private String checkin_location;
	private String checkin_lat;
	private String checkin_longi;
	private String checkin_deviation;

	private Date checkout_time;
	private String checkout_location;
	private String checkout_lat;
	private String checkout_longi;
	private String checkout_deviation;

	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	@UpdateTimestamp
	private Date updated_date;

	public long getCheckIncheckOut_id() {
		return checkIncheckOut_id;
	}

	public void setCheckIncheckOut_id(long checkIncheckOut_id) {
		this.checkIncheckOut_id = checkIncheckOut_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public Date getCheckin_time() {
		return checkin_time;
	}

	public void setCheckin_time(Date checkin_time) {
		this.checkin_time = checkin_time;
	}

	public String getCheckin_location() {
		return checkin_location;
	}

	public void setCheckin_location(String checkin_location) {
		this.checkin_location = checkin_location;
	}

	public String getCheckin_lat() {
		return checkin_lat;
	}

	public void setCheckin_lat(String checkin_lat) {
		this.checkin_lat = checkin_lat;
	}

	public String getCheckin_longi() {
		return checkin_longi;
	}

	public void setCheckin_longi(String checkin_longi) {
		this.checkin_longi = checkin_longi;
	}

	public String getCheckin_deviation() {
		return checkin_deviation;
	}

	public void setCheckin_deviation(String checkin_deviation) {
		this.checkin_deviation = checkin_deviation;
	}

	public Date getCheckout_time() {
		return checkout_time;
	}

	public void setCheckout_time(Date checkout_time) {
		this.checkout_time = checkout_time;
	}

	public String getCheckout_location() {
		return checkout_location;
	}

	public void setCheckout_location(String checkout_location) {
		this.checkout_location = checkout_location;
	}

	public String getCheckout_lat() {
		return checkout_lat;
	}

	public void setCheckout_lat(String checkout_lat) {
		this.checkout_lat = checkout_lat;
	}

	public String getCheckout_longi() {
		return checkout_longi;
	}

	public void setCheckout_longi(String checkout_longi) {
		this.checkout_longi = checkout_longi;
	}

	public String getCheckout_deviation() {
		return checkout_deviation;
	}

	public void setCheckout_deviation(String checkout_deviation) {
		this.checkout_deviation = checkout_deviation;
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

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
}