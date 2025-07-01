package com.softtantra.servicemi.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sm_days_master")
public class SM_DaysMaster implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sm_days_master_id;
	private String days_name;
	private int status;

	public int getSm_days_master_id() {
		return sm_days_master_id;
	}

	public void setSm_days_master_id(int sm_days_master_id) {
		this.sm_days_master_id = sm_days_master_id;
	}

	public String getDays_name() {
		return days_name;
	}

	public void setDays_name(String days_name) {
		this.days_name = days_name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
