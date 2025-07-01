package com.softtantra.servicemi.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sm_distributor_route_import_records")
public class SM_DistributorRouteImportRecords implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int user_id;
	private int status;
	private String comment;
	private int import_id;
	private int company_id;

	private String distributor_name;
	private String route_name;

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

}
