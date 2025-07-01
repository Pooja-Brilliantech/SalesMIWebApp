package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company_has_mobilepermission")
public class Companymobilepermission implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int company_id;
	private int mobile_permission_id;

	public int getMobile_permission_id() {
		return mobile_permission_id;
	}

	public void setMobile_permission_id(int mobile_permission_id) {
		this.mobile_permission_id = mobile_permission_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

}
