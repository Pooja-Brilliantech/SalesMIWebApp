package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "travel_details")
public class TravelDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int travel_details_id;
	private int travel_master_id;
	private Date plan_date;
	private String from_place;
	private String to_place;
	private String mode_of_travel;
	private Date preferred_time;
	private Date meeting_time;
	private String other_details;
	private String remarks;

	public int getTravel_details_id() {
		return travel_details_id;
	}

	public void setTravel_details_id(int travel_details_id) {
		this.travel_details_id = travel_details_id;
	}

	public int getTravel_master_id() {
		return travel_master_id;
	}

	public void setTravel_master_id(int travel_master_id) {
		this.travel_master_id = travel_master_id;
	}

	public Date getPlan_date() {
		return plan_date;
	}

	public void setPlan_date(Date plan_date) {
		this.plan_date = plan_date;
	}

	public String getFrom_place() {
		return from_place;
	}

	public void setFrom_place(String from_place) {
		this.from_place = from_place;
	}

	public String getTo_place() {
		return to_place;
	}

	public void setTo_place(String to_place) {
		this.to_place = to_place;
	}

	public String getMode_of_travel() {
		return mode_of_travel;
	}

	public void setMode_of_travel(String mode_of_travel) {
		this.mode_of_travel = mode_of_travel;
	}

	public Date getPreferred_time() {
		return preferred_time;
	}

	public void setPreferred_time(Date preferred_time) {
		this.preferred_time = preferred_time;
	}

	public Date getMeeting_time() {
		return meeting_time;
	}

	public void setMeeting_time(Date meeting_time) {
		this.meeting_time = meeting_time;
	}

	public String getOther_details() {
		return other_details;
	}

	public void setOther_details(String other_details) {
		this.other_details = other_details;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
