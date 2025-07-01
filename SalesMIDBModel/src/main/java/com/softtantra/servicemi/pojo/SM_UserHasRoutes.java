package com.softtantra.servicemi.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sm_user_has_routes")
public class SM_UserHasRoutes implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int user_has_routes_id;
	private int user_id;
	private int company_id;
	private int distributor_id;
	private int route_id;
	@Transient
	private String route_name;
	@Transient
	private String distributor_name;

	public String getRoute_name() {
		return route_name;
	}

	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}

	public String getDistributor_name() {
		return distributor_name;
	}

	public void setDistributor_name(String distributor_name) {
		this.distributor_name = distributor_name;
	}

	public int getUser_has_routes_id() {
		return user_has_routes_id;
	}

	public void setUser_has_routes_id(int user_has_routes_id) {
		this.user_has_routes_id = user_has_routes_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
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

}
