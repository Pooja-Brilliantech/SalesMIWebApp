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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="manage_holiday")
public class HolidayDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int holidayId;
	private String holidayName;
	private String holidayDate;
	@Column(nullable = true)
	private int state_id;
	@Column(nullable = true)
	private int country_id;
	private int company_id;
	private String holidayYear;
	@CreationTimestamp
	private Date created_date;;
	@UpdateTimestamp
	private Date updated_date;
	private int created_by;
	private int updated_by;
	private String holidayImage;
	private int status;
	
	@Transient
	private String stateId;
	@Transient
	private String countryId;
	
	
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public int getHolidayId() {
		return holidayId;
	}
	public void setHolidayId(int holidayId) {
		this.holidayId = holidayId;
	}
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	public String getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getHolidayYear() {
		return holidayYear;
	}
	public void setHolidayYear(String holidayYear) {
		this.holidayYear = holidayYear;
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
	public String getHolidayImage() {
		return holidayImage;
	}
	public void setHolidayImage(String holidayImage) {
		this.holidayImage = holidayImage;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
