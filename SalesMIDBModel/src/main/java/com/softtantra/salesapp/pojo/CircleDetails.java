package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "circle_details")
public class CircleDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cDId;
	private int cId=0;
	private int countryId=0;
	private int stateId=0;
	private int cityId=0;
	public int getcDId() {
		return cDId;
	}
	public void setcDId(int cDId) {
		this.cDId = cDId;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
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
	
	
}
