package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beat_plan_import_records")
public class BeatPlanImportRecords implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int user_id;
	private int status;
	private String comment;
	private int import_id;
	private int company_id;

	private String user_name;
	private String beat_plan_date;
	private String distributor_name;
	private String route_name;
	private String customer_name;
	private String sales_target_type;
	private String sales_target;
	private String sales_target_unit;
	private String collection_target;
	private String sequence;

	private String beat_plan_id;

	public String getBeat_plan_id() {
		return beat_plan_id;
	}

	public void setBeat_plan_id(String beat_plan_id) {
		this.beat_plan_id = beat_plan_id;
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

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getBeat_plan_date() {
		return beat_plan_date;
	}

	public void setBeat_plan_date(String beat_plan_date) {
		this.beat_plan_date = beat_plan_date;
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

	public String getSales_target_type() {
		return sales_target_type;
	}

	public void setSales_target_type(String sales_target_type) {
		this.sales_target_type = sales_target_type;
	}

	public String getSales_target() {
		return sales_target;
	}

	public void setSales_target(String sales_target) {
		this.sales_target = sales_target;
	}

	public String getSales_target_unit() {
		return sales_target_unit;
	}

	public void setSales_target_unit(String sales_target_unit) {
		this.sales_target_unit = sales_target_unit;
	}

	public String getCollection_target() {
		return collection_target;
	}

	public void setCollection_target(String collection_target) {
		this.collection_target = collection_target;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

}
