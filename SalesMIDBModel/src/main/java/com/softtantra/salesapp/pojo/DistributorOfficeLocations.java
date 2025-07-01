package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "distributor_office_locations")
public class DistributorOfficeLocations implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int distributorLocationId;
	@Column(nullable = true)
	private String distributorLocationName;
	@Column(nullable = true)
	private String distributorLocationCode;
	@Column(nullable = true)
	private String lat;
	@Column(nullable = true)
	private String longi;
	@Column(nullable = true)
	private String distributorLocationAddress;
	@Column(nullable = true)
	private String distributorOfficeLocation;
	private int company_id;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private int distributor_id=0;
	@Column(nullable = true)
	private String distributor_name;
	public int getDistributorLocationId() {
		return distributorLocationId;
	}
	public void setDistributorLocationId(int distributorLocationId) {
		this.distributorLocationId = distributorLocationId;
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
	public String getDistributorOfficeLocation() {
		return distributorOfficeLocation;
	}
	public void setDistributorOfficeLocation(String distributorOfficeLocation) {
		this.distributorOfficeLocation = distributorOfficeLocation;
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
	public int getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public int getDistributor_id() {
		return distributor_id;
	}
	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}
	public String getDistributor_name() {
		return distributor_name;
	}
	public void setDistributor_name(String distributor_name) {
		this.distributor_name = distributor_name;
	}
	
	
}
