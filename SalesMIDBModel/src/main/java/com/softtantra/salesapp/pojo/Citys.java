package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "cities")
public class Citys implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int city_id;
	private String city_name;
	private String city_code;
	private int state_id;
	private String city_type;
	
	@CreationTimestamp
	private Date created_date;
	
	@UpdateTimestamp
	private Date updated_date;
	
	@ColumnDefault(value = "0")
	private int taluka_id = 0;
	
	@ColumnDefault(value = "0")
	private int district_id = 0;
	
	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public int getState_id() {
		return state_id;
	}

	public void setState_id(int state_id) {
		this.state_id = state_id;
	}

	public String getCity_type() {
		return city_type;
	}

	public void setCity_type(String city_type) {
		this.city_type = city_type;
	}

	public int getTaluka_id() {
		return taluka_id;
	}

	public void setTaluka_id(int taluka_id) {
		this.taluka_id = taluka_id;
	}

	public int getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
}
