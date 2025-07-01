package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "beat_plan_details")
public class BeatPlanDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int beat_plan_id;
	private int company_id;
	private int customer_id;
	private int user_id;
	private int distributor_id;
	private int route_id;
	private double target;
	private Date beat_plan_date;
	private int status;
	private int created_by;
	private String reason;
	@CreationTimestamp
	private Date created_date;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	@ColumnDefault("0")
	private int beatPlanMasterId=0;
	public int getBeatPlanMasterId() {
		return beatPlanMasterId;
	}

	public void setBeatPlanMasterId(int beatPlanMasterId) {
		this.beatPlanMasterId = beatPlanMasterId;
	}

	private Date reason_date;
	private double achived_target;

	private int beat_plan_reason_id;

	private String reasondetails;

	private String unit;
	private double collection_target;
	private int sequence;
	private String sales_target_type;
	
	@Transient
	private boolean isUpdated = false;

	public String getSales_target_type() {
		return sales_target_type;
	}

	public void setSales_target_type(String sales_target_type) {
		this.sales_target_type = sales_target_type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getCollection_target() {
		return collection_target;
	}

	public void setCollection_target(double collection_target) {
		this.collection_target = collection_target;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getReasondetails() {
		return reasondetails;
	}

	public void setReasondetails(String reasondetails) {
		this.reasondetails = reasondetails;
	}

	public int getBeat_plan_reason_id() {
		return beat_plan_reason_id;
	}

	public void setBeat_plan_reason_id(int beat_plan_reason_id) {
		this.beat_plan_reason_id = beat_plan_reason_id;
	}

	public double getAchived_target() {
		return achived_target;
	}

	public void setAchived_target(double achived_target) {
		this.achived_target = achived_target;
	}

	public Date getReason_date() {
		return reason_date;
	}

	public void setReason_date(Date reason_date) {
		this.reason_date = reason_date;
	}

	@Transient
	private String user_name;
	@Transient
	private String cust_name;

	public int getBeat_plan_id() {
		return beat_plan_id;
	}

	public void setBeat_plan_id(int beat_plan_id) {
		this.beat_plan_id = beat_plan_id;
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

	public double getTarget() {
		return target;
	}

	public void setTarget(double target) {
		this.target = target;
	}

	public Date getBeat_plan_date() {
		return beat_plan_date;
	}

	public void setBeat_plan_date(Date beat_plan_date) {
		this.beat_plan_date = beat_plan_date;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
	@Transient
	private String beat_plan_reason;

	public String getBeat_plan_reason() {
		return beat_plan_reason;
	}

	public void setBeat_plan_reason(String beat_plan_reason) {
		this.beat_plan_reason = beat_plan_reason;
	}

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

	// swapnil
	@Transient
	private String date;

	private double collection_achived_target;

	public double getCollection_achived_target() {
		return collection_achived_target;
	}

	public void setCollection_achived_target(double collection_achived_target) {
		this.collection_achived_target = collection_achived_target;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isUpdated() {
		return isUpdated;
	}

	public void setUpdated(boolean isUpdated) {
		this.isUpdated = isUpdated;
	}

}
