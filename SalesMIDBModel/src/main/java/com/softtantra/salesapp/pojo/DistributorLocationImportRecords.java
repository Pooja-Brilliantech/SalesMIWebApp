package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "distributorlocation_import_records")
public class DistributorLocationImportRecords implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int user_id;
	private String distributorLocationName;
	private String distributorLocationCode;
	private String lat;
	private String longi;
	private String distributorLocationAddress;
	private int company_id;
	private int status;
	private String comment;
	private int import_id;
	private String officeLocation;
	private int distributor_id;
	
	@Transient
	private String distributor_name;
	
	public String getDistributor_name() {
		return distributor_name;
	}
	public void setDistributor_name(String distributor_name) {
		this.distributor_name = distributor_name;
	}
	public int getRecords_id() {
		return records_id;
	}
	public void setRecords_id(int records_id) {
		this.records_id = records_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getDistributorLocationName() {
		return distributorLocationName;
	}
	public void setDistributorLocationName(String distributorLocationName) {
		this.distributorLocationName = distributorLocationName;
	}
	public String getDistributorLocationCode() {
		return distributorLocationCode;
	}
	public void setDistributorLocationCode(String distributorLocationCode) {
		this.distributorLocationCode = distributorLocationCode;
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
	public String getDistributorLocationAddress() {
		return distributorLocationAddress;
	}
	public void setDistributorLocationAddress(String distributorLocationAddress) {
		this.distributorLocationAddress = distributorLocationAddress;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getImport_id() {
		return import_id;
	}
	public void setImport_id(int import_id) {
		this.import_id = import_id;
	}
	public String getOfficeLocation() {
		return officeLocation;
	}
	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}
	public int getDistributor_id() {
		return distributor_id;
	}
	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}
	
	
}
