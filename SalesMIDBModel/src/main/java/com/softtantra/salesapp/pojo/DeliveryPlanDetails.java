package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "delivery_plan_details")
public class DeliveryPlanDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int delivery_plan_id;
	private int company_id;
	private int customer_id;
	private int user_id;
	private int distributor_id;
	private int route_id;
	private int target;
	private Date delivery_plan_date;
	private int status;
	private int created_by;
	private int empty;
	private double cash;
	private int updated_by;
	@CreationTimestamp
	private Date created_date;;
	@UpdateTimestamp
	private Date updated_date;
	private int achived_target;
	private String location;
	private Date delivery_updated_date;

	private String trip_id;

	private String challen_details;

	public String getChallen_details() {
		return challen_details;
	}

	public void setChallen_details(String challen_details) {
		this.challen_details = challen_details;
	}

	public String getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	@Transient
	private String user_name;

	@Transient
	private int empty_jar;

	@Transient
	private String vehicle_details;

	public String getVehicle_details() {
		return vehicle_details;
	}

	public void setVehicle_details(String vehicle_details) {
		this.vehicle_details = vehicle_details;
	}

	public int getEmpty_jar() {
		return empty_jar;
	}

	public void setEmpty_jar(int empty_jar) {
		this.empty_jar = empty_jar;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getDelivery_updated_date() {
		return delivery_updated_date;
	}

	public void setDelivery_updated_date(Date delivery_updated_date) {
		this.delivery_updated_date = delivery_updated_date;
	}

	public int getDelivery_plan_id() {
		return delivery_plan_id;
	}

	public void setDelivery_plan_id(int delivery_plan_id) {
		this.delivery_plan_id = delivery_plan_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getDistributor_id() {
		return distributor_id;
	}

	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}

	public int getRoute_id() {
		return route_id;
	}

	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}

	public Date getDelivery_plan_date() {
		return delivery_plan_date;
	}

	public void setDelivery_plan_date(Date delivery_plan_date) {
		this.delivery_plan_date = delivery_plan_date;
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

	public int getEmpty() {
		return empty;
	}

	public void setEmpty(int empty) {
		this.empty = empty;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
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

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getAchived_target() {
		return achived_target;
	}

	public void setAchived_target(int achived_target) {
		this.achived_target = achived_target;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	@Transient
	private String b_date;

	public String getB_date() {
		return b_date;
	}

	public void setB_date(String b_date) {
		this.b_date = b_date;
	}

	@Transient
	private String distributor_name;

	@Transient
	private String route_name;

	@Transient
	private String customer_name;

	public String getDistributor_name() {
		return distributor_name;
	}

	public void setDistributor_name(String distributor_name) {
		this.distributor_name = distributor_name;
	}

	public String getRoute_name() {
		return route_name;
	}

	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

}