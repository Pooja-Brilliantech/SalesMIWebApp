package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_details")
public class EventDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int event_id;
	private int company_id;

	private int user_id;
	private String event_type;
	private String event_details;
	private Date event_date_time;

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
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

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public String getEvent_details() {
		return event_details;
	}

	public void setEvent_details(String event_details) {
		this.event_details = event_details;
	}

	public Date getEvent_date_time() {
		return event_date_time;
	}

	public void setEvent_date_time(Date event_date_time) {
		this.event_date_time = event_date_time;
	}

}