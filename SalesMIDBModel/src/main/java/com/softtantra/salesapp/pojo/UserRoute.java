package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_route")
public class UserRoute implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_route_id;
	private int company_id;
	private int user_id;
	private int route_id;

	public int getUser_route_id() {
		return user_route_id;
	}

	public void setUser_route_id(int user_route_id) {
		this.user_route_id = user_route_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRoute_id() {
		return route_id;
	}

	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}
}
