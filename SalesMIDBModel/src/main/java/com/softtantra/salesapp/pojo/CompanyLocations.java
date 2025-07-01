package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company_locations")
public class CompanyLocations implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyLocationId;
	private String companyLocationName;
	private String companyLocationCode;
	private String lat;
	private String longi;
	private String companyLocationAddress;
	private String officeLocation;
	private int company_id;
	public int getCompanyLocationId() {
		return companyLocationId;
	}
	public void setCompanyLocationId(int companyLocationId) {
		this.companyLocationId = companyLocationId;
	}
	public String getCompanyLocationName() {
		return companyLocationName;
	}
	public void setCompanyLocationName(String companyLocationName) {
		this.companyLocationName = companyLocationName;
	}
	public String getCompanyLocationCode() {
		return companyLocationCode;
	}
	public void setCompanyLocationCode(String companyLocationCode) {
		this.companyLocationCode = companyLocationCode;
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
	public String getCompanyLocationAddress() {
		return companyLocationAddress;
	}
	public void setCompanyLocationAddress(String companyLocationAddress) {
		this.companyLocationAddress = companyLocationAddress;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getOfficeLocation() {
		return officeLocation;
	}
	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}
	
	
}
