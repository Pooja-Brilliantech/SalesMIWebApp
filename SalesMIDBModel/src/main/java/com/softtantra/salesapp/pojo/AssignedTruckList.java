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
@Table(name = "assigned_truck_list")
public class AssignedTruckList implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int assigned_truck_id;

	private int company_id;
	private int state_id;
	private int country_id;
	private int city_id;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private double total_capacity;
	private double available_capacity;
	private double occupied_capacity;
	private int vehicle_master_id;
	private int margin;
	
	private String status_of_truck = "Available";

	public String getStatus_of_truck() {
		return status_of_truck;
	}

	public void setStatus_of_truck(String status_of_truck) {
		this.status_of_truck = status_of_truck;
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

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public int getAssigned_truck_id() {
		return assigned_truck_id;
	}

	public void setAssigned_truck_id(int assigned_truck_id) {
		this.assigned_truck_id = assigned_truck_id;
	}

	public double getTotal_capacity() {
		return total_capacity;
	}

	public void setTotal_capacity(double total_capacity) {
		this.total_capacity = total_capacity;
	}

	public double getAvailable_capacity() {
		return available_capacity;
	}

	public void setAvailable_capacity(double available_capacity) {
		this.available_capacity = available_capacity;
	}

	public double getOccupied_capacity() {
		return occupied_capacity;
	}

	public void setOccupied_capacity(double occupied_capacity) {
		this.occupied_capacity = occupied_capacity;
	}

	public int getVehicle_master_id() {
		return vehicle_master_id;
	}

	public void setVehicle_master_id(int vehicle_master_id) {
		this.vehicle_master_id = vehicle_master_id;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

}
