package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "beat_plan_template_master")
public class BeatPlanTemplateMaster implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int beat_plan_details_id;

	private String template_name;
	private int distributor_id;
	private int route_id;
	// default columns
	private int company_id;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;

	@OneToMany(mappedBy = "beatPlanTemplateMaster")
	private Set<BeatPlanTemplateDetails> beatPlanTemplatedetails;

	public Set<BeatPlanTemplateDetails> getBeatPlanTemplatedetails() {
		return beatPlanTemplatedetails;
	}

	public void setBeatPlanTemplatedetails(Set<BeatPlanTemplateDetails> beatPlanTemplatedetails) {
		this.beatPlanTemplatedetails = beatPlanTemplatedetails;
	}

	public int getBeat_plan_details_id() {
		return beat_plan_details_id;
	}

	public void setBeat_plan_details_id(int beat_plan_details_id) {
		this.beat_plan_details_id = beat_plan_details_id;
	}

	public String getTemplate_name() {
		return template_name;
	}

	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	public int getDistributor_id() {
		return distributor_id;
	}

	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}

	public int getRoute_id() {
		return route_id;
	}

	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

}
