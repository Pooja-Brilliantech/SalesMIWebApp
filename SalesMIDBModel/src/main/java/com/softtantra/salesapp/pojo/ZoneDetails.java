package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zone_details")
public class ZoneDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int zDid;
	private int zId;
	private int countryId=0;
	private int stateId=0;
	private int cityId=0;
	public int getzDid() {
		return zDid;
	}
	public void setzDid(int zDid) {
		this.zDid = zDid;
	}
	public int getzId() {
		return zId;
	}
	public void setzId(int zId) {
		this.zId = zId;
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
