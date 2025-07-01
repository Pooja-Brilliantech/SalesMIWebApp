package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "eod_special_distributor")
public class EOD_Special_Distributor implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eod_special_distributor_id;
	private int special_report_id;
	// private String new_distributor_ss_searching;
	private Date distributor_date;
	// private String distributor_area;
	// private int no_of_ss_visited;
	private String distributor_name;
	private String distributor_work;
	private String distributor_remark;

	@Transient
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getEod_special_distributor_id() {
		return eod_special_distributor_id;
	}

	public void setEod_special_distributor_id(int eod_special_distributor_id) {
		this.eod_special_distributor_id = eod_special_distributor_id;
	}

	public int getSpecial_report_id() {
		return special_report_id;
	}

	public void setSpecial_report_id(int special_report_id) {
		this.special_report_id = special_report_id;
	}

	/*
	 * public String getNew_distributor_ss_searching() { return
	 * new_distributor_ss_searching; } public void
	 * setNew_distributor_ss_searching(String new_distributor_ss_searching) {
	 * this.new_distributor_ss_searching = new_distributor_ss_searching; }
	 */
	public Date getDistributor_date() {
		return distributor_date;
	}

	public void setDistributor_date(Date distributor_date) {
		this.distributor_date = distributor_date;
	}

	/*
	 * public String getDistributor_area() { return distributor_area; } public void
	 * setDistributor_area(String distributor_area) { this.distributor_area =
	 * distributor_area; }
	 */
	/*
	 * public int getNo_of_ss_visited() { return no_of_ss_visited; } public void
	 * setNo_of_ss_visited(int no_of_ss_visited) { this.no_of_ss_visited =
	 * no_of_ss_visited; }
	 */
	public String getDistributor_name() {
		return distributor_name;
	}

	public void setDistributor_name(String distributor_name) {
		this.distributor_name = distributor_name;
	}

	public String getDistributor_work() {
		return distributor_work;
	}

	public void setDistributor_work(String distributor_work) {
		this.distributor_work = distributor_work;
	}

	public String getDistributor_remark() {
		return distributor_remark;
	}

	public void setDistributor_remark(String distributor_remark) {
		this.distributor_remark = distributor_remark;
	}

}
