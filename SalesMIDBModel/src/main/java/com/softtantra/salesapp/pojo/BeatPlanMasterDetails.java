package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "beat_plan_master_details")
public class BeatPlanMasterDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int beatPlanMasterDetailsId;
	private int beatPlanMasterId;
	private int distributor_id;
	private int route_id;
	private int status;
	private int company_id;
	private String distributrorName;
	private String routeName;
	
	public int getBeatPlanMasterDetailsId() {
		return beatPlanMasterDetailsId;
	}
	public void setBeatPlanMasterDetailsId(int beatPlanMasterDetailsId) {
		this.beatPlanMasterDetailsId = beatPlanMasterDetailsId;
	}
	public int getBeatPlanMasterId() {
		return beatPlanMasterId;
	}
	public void setBeatPlanMasterId(int beatPlanMasterId) {
		this.beatPlanMasterId = beatPlanMasterId;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getDistributrorName() {
		return distributrorName;
	}
	public void setDistributrorName(String distributrorName) {
		this.distributrorName = distributrorName;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	
	
	
	
}
