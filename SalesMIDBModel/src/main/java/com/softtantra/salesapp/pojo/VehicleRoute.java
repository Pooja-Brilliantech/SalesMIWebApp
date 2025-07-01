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
@Table(name = "vehicle_route")
public class VehicleRoute implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vehicleRouteId;
	private String route;
	private String shippingType;
	private String shippingTypeDesc;
	private String description;
	private String stageNumber;
	private String departurePoint;
	private String destinationPoint;
	private int company_id;
	private int created_by;
	private int updated_by;
	@CreationTimestamp
	private Date created_date;;
	@UpdateTimestamp
	private Date updated_date;
	private int status;
	private String country;
	private String routeId;

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getVehicleRouteId() {
		return vehicleRouteId;
	}

	public void setVehicleRouteId(int vehicleRouteId) {
		this.vehicleRouteId = vehicleRouteId;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public String getShippingTypeDesc() {
		return shippingTypeDesc;
	}

	public void setShippingTypeDesc(String shippingTypeDesc) {
		this.shippingTypeDesc = shippingTypeDesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStageNumber() {
		return stageNumber;
	}

	public void setStageNumber(String stageNumber) {
		this.stageNumber = stageNumber;
	}

	public String getDeparturePoint() {
		return departurePoint;
	}

	public void setDeparturePoint(String departurePoint) {
		this.departurePoint = departurePoint;
	}

	public String getDestinationPoint() {
		return destinationPoint;
	}

	public void setDestinationPoint(String destinationPoint) {
		this.destinationPoint = destinationPoint;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
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

	@Override
	public String toString() {
		return "VehicleRoute [vehicleRouteId=" + vehicleRouteId + ", route=" + route + ", shippingType=" + shippingType
				+ ", shippingTypeDesc=" + shippingTypeDesc + ", description=" + description + ", stageNumber="
				+ stageNumber + ", departurePoint=" + departurePoint + ", destinationPoint=" + destinationPoint
				+ ", company_id=" + company_id + ", created_by=" + created_by + ", updated_by=" + updated_by
				+ ", created_date=" + created_date + ", updated_date=" + updated_date + ", status=" + status
				+ ", country=" + country + ", routeId=" + routeId + "]";
	}

}
