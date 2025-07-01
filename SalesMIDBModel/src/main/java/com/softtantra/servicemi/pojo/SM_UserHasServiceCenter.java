package com.softtantra.servicemi.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sm_user_has_service_center")
public class SM_UserHasServiceCenter implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sm_user_has_service_center_id;
	private int company_id;
	private int user_id;
	private int service_center_id;

	@Transient
	private String service_center_name;

	public String getService_center_name() {
		return service_center_name;
	}

	public void setService_center_name(String service_center_name) {
		this.service_center_name = service_center_name;
	}

	public int getSm_user_has_service_center_id() {
		return sm_user_has_service_center_id;
	}

	public void setSm_user_has_service_center_id(int sm_user_has_service_center_id) {
		this.sm_user_has_service_center_id = sm_user_has_service_center_id;
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

	public int getService_center_id() {
		return service_center_id;
	}

	public void setService_center_id(int service_center_id) {
		this.service_center_id = service_center_id;
	}

}
