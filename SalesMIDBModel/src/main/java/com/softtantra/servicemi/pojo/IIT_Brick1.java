package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "iit_brick1")
public class IIT_Brick1 implements Serializable{

	@Id
	@Column(name = "IIT_brick_id_1")
	@GeneratedValue
	private int IIT_brick_id_1;

	private int IIT_brick_id;

	private String stacks_in_month;
	private String bricks_produced;
	private String bricks_considered;
	private String fuels_used;
	private String amount;
	private String source;
	private String brick_dimensions;
	private String other_dimensions;
	private String brick_used_activities;
	private String other_states;
	private String is_bricks_supplied;
	private String machines_use;
	private String amount_of_diesel;
	private long created_by;
	@CreationTimestamp
	private Date created_date;;
	private long updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private String status;
	private String image_path;
	private String remark;
	private String participate_measurements;
	private String probe_for_measurement;
	private String survery_no;
	private String agri_residue;
	private String bricks_sizes;

	public int getIIT_brick_id_1() {
		return IIT_brick_id_1;
	}

	public void setIIT_brick_id_1(int iIT_brick_id_1) {
		IIT_brick_id_1 = iIT_brick_id_1;
	}

	public int getIIT_brick_id() {
		return IIT_brick_id;
	}

	public void setIIT_brick_id(int iIT_brick_id) {
		IIT_brick_id = iIT_brick_id;
	}

	public String getStacks_in_month() {
		return stacks_in_month;
	}

	public void setStacks_in_month(String stacks_in_month) {
		this.stacks_in_month = stacks_in_month;
	}

	public String getBricks_produced() {
		return bricks_produced;
	}

	public void setBricks_produced(String bricks_produced) {
		this.bricks_produced = bricks_produced;
	}

	public String getBricks_considered() {
		return bricks_considered;
	}

	public void setBricks_considered(String bricks_considered) {
		this.bricks_considered = bricks_considered;
	}

	public String getFuels_used() {
		return fuels_used;
	}

	public void setFuels_used(String fuels_used) {
		this.fuels_used = fuels_used;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getBrick_dimensions() {
		return brick_dimensions;
	}

	public void setBrick_dimensions(String brick_dimensions) {
		this.brick_dimensions = brick_dimensions;
	}

	public String getOther_dimensions() {
		return other_dimensions;
	}

	public void setOther_dimensions(String other_dimensions) {
		this.other_dimensions = other_dimensions;
	}

	public String getBrick_used_activities() {
		return brick_used_activities;
	}

	public void setBrick_used_activities(String brick_used_activities) {
		this.brick_used_activities = brick_used_activities;
	}

	public String getOther_states() {
		return other_states;
	}

	public void setOther_states(String other_states) {
		this.other_states = other_states;
	}

	public String getIs_bricks_supplied() {
		return is_bricks_supplied;
	}

	public void setIs_bricks_supplied(String is_bricks_supplied) {
		this.is_bricks_supplied = is_bricks_supplied;
	}

	public String getMachines_use() {
		return machines_use;
	}

	public void setMachines_use(String machines_use) {
		this.machines_use = machines_use;
	}

	public String getAmount_of_diesel() {
		return amount_of_diesel;
	}

	public void setAmount_of_diesel(String amount_of_diesel) {
		this.amount_of_diesel = amount_of_diesel;
	}

	public long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(long created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(long updated_by) {
		this.updated_by = updated_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getParticipate_measurements() {
		return participate_measurements;
	}

	public void setParticipate_measurements(String participate_measurements) {
		this.participate_measurements = participate_measurements;
	}

	public String getProbe_for_measurement() {
		return probe_for_measurement;
	}

	public void setProbe_for_measurement(String probe_for_measurement) {
		this.probe_for_measurement = probe_for_measurement;
	}

	public String getSurvery_no() {
		return survery_no;
	}

	public void setSurvery_no(String survery_no) {
		this.survery_no = survery_no;
	}

	public String getAgri_residue() {
		return agri_residue;
	}

	public void setAgri_residue(String agri_residue) {
		this.agri_residue = agri_residue;
	}

	public String getBricks_sizes() {
		return bricks_sizes;
	}

	public void setBricks_sizes(String bricks_sizes) {
		this.bricks_sizes = bricks_sizes;
	}

}
