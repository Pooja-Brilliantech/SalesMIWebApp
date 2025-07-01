package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_version_details")
public class AppVersionDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long app_version_id;
	private String app_version;
	private String supported_email;
	private String supported_contact_no;
	private String device_type;

	public Long getApp_version_id() {
		return app_version_id;
	}

	public void setApp_version_id(Long app_version_id) {
		this.app_version_id = app_version_id;
	}

	public String getApp_version() {
		return app_version;
	}

	public void setApp_version(String app_version) {
		this.app_version = app_version;
	}

	public String getSupported_email() {
		return supported_email;
	}

	public void setSupported_email(String supported_email) {
		this.supported_email = supported_email;
	}

	public String getSupported_contact_no() {
		return supported_contact_no;
	}

	public void setSupported_contact_no(String supported_contact_no) {
		this.supported_contact_no = supported_contact_no;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

}
