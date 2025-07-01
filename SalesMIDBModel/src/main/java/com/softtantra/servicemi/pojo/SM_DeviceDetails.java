package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "sm_device_details")
public class SM_DeviceDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long device_details_id;

	private String device_type;
	private String imei_no;

	private String apk_version;

	@CreationTimestamp
	private Date created_date;;

	private long user_id;

	private Date modified_date;

	private String device_id;

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public long getDevice_details_id() {
		return device_details_id;
	}

	public void setDevice_details_id(long device_details_id) {
		this.device_details_id = device_details_id;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getImei_no() {
		return imei_no;
	}

	public void setImei_no(String imei_no) {
		this.imei_no = imei_no;
	}

	public String getApk_version() {
		return apk_version;
	}

	public void setApk_version(String apk_version) {
		this.apk_version = apk_version;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Imei_Details [id=" + device_details_id + ", imei_no=" + imei_no + ", apk_version=" + apk_version
				+ ", created_date=" + created_date + ", user_id=" + user_id + ", modified_date=" + modified_date + "]";
	}

}
