package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "beat_plan_template_details")
public class BeatPlanTemplateDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int beat_plan_details_id;
	private int customer_id;
	private String unit;
	private double collection_target;
	private int sequence;
	private String sales_target_type;
	private int day_no;
	private double sales_target;

	public double getSales_target() {
		return sales_target;
	}

	public void setSales_target(double sales_target) {
		this.sales_target = sales_target;
	}

	@ManyToOne
	@JoinColumn(name = "beat_plan_template_id", nullable = false)
	private BeatPlanTemplateMaster beatPlanTemplateMaster;

	public BeatPlanTemplateDetails() {
	}

	public BeatPlanTemplateDetails(int customer_id, String unit, double collection_target, int sequence,
			String sales_target_type, int day_no, BeatPlanTemplateMaster beatPlanTemplateMaster, double sales_target) {
		this.customer_id = customer_id;
		this.unit = unit;
		this.collection_target = collection_target;
		this.sequence = sequence;
		this.sales_target_type = sales_target_type;
		this.day_no = day_no;
		this.beatPlanTemplateMaster = beatPlanTemplateMaster;
		this.sales_target = sales_target;

	}

	public int getBeat_plan_details_id() {
		return beat_plan_details_id;
	}

	public void setBeat_plan_details_id(int beat_plan_details_id) {
		this.beat_plan_details_id = beat_plan_details_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getCollection_target() {
		return collection_target;
	}

	public void setCollection_target(double collection_target) {
		this.collection_target = collection_target;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getSales_target_type() {
		return sales_target_type;
	}

	public void setSales_target_type(String sales_target_type) {
		this.sales_target_type = sales_target_type;
	}

	public int getDay_no() {
		return day_no;
	}

	public void setDay_no(int day_no) {
		this.day_no = day_no;
	}

	public BeatPlanTemplateMaster getBeatPlanTemplateMaster() {
		return beatPlanTemplateMaster;
	}

	public void setBeatPlanTemplateMaster(BeatPlanTemplateMaster beatPlanTemplateMaster) {
		this.beatPlanTemplateMaster = beatPlanTemplateMaster;
	}

}
