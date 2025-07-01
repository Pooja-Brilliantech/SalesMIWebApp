package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mode_of_travel")
public class ModeOfTravel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int modeOfTravelId;
	private int company_id;
	private int user_id;
	private String modeOfTravel;
	private double modeOfTravelRate=0.0;
	private Date created_date;
	private Date updated_date;
	private int status;
	private int roleId;
	public int getModeOfTravelId() {
		return modeOfTravelId;
	}
	public void setModeOfTravelId(int modeOfTravelId) {
		this.modeOfTravelId = modeOfTravelId;
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
	public String getModeOfTravel() {
		return modeOfTravel;
	}
	public void setModeOfTravel(String modeOfTravel) {
		this.modeOfTravel = modeOfTravel;
	}
	public double getModeOfTravelRate() {
		return modeOfTravelRate;
	}
	public void setModeOfTravelRate(double modeOfTravelRate) {
		this.modeOfTravelRate = modeOfTravelRate;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
	
}
