package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sun.istack.Nullable;

@Entity
@Table(name = "beat_plan_master")
public class BeatPlanMaster implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int beatPlanMasterId;
	private int distributor_id;
	private int route_id;
	private Date beatPlanDate;
	private String journeyType;
	private String modeOfTravel;
	private String startLat;
	private String startLong;
	private String endLat;
	private String endLongi;
	private String remark;
	private String approvalStatus;
	private int company_id;
	@CreationTimestamp
	private Date created_date;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private int user_id;
	private int status;
	private int created_by;
	private int callTarget=0;
	private String coTravellers;
	private int expenseMasterId=0;
	@Transient
	private long pending;
	@Transient
	private long approved;
	@Transient
	private long rejected;
	@Transient
	private long submitted;
	@Nullable
	private String city;
	
	@Transient
	private boolean isUpdated =  false;
	
	
	public long getPending() {
		return pending;
	}
	public void setPending(long pending) {
		this.pending = pending;
	}
	public long getApproved() {
		return approved;
	}
	public void setApproved(long approved) {
		this.approved = approved;
	}
	public long getRejected() {
		return rejected;
	}
	public void setRejected(long rejected) {
		this.rejected = rejected;
	}
	public long getSubmitted() {
		return submitted;
	}
	public void setSubmitted(long submitted) {
		this.submitted = submitted;
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
	public Date getBeatPlanDate() {
		return beatPlanDate;
	}
	public void setBeatPlanDate(Date beatPlanDate) {
		this.beatPlanDate = beatPlanDate;
	}
	public String getJourneyType() {
		return journeyType;
	}
	public void setJourneyType(String journeyType) {
		this.journeyType = journeyType;
	}
	public String getModeOfTravel() {
		return modeOfTravel;
	}
	public void setModeOfTravel(String modeOfTravel) {
		this.modeOfTravel = modeOfTravel;
	}
	public String getStartLat() {
		return startLat;
	}
	public void setStartLat(String startLat) {
		this.startLat = startLat;
	}
	public String getStartLong() {
		return startLong;
	}
	public void setStartLong(String startLong) {
		this.startLong = startLong;
	}
	public String getEndLat() {
		return endLat;
	}
	public void setEndLat(String endLat) {
		this.endLat = endLat;
	}
	public String getEndLongi() {
		return endLongi;
	}
	public void setEndLongi(String endLongi) {
		this.endLongi = endLongi;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	public int getCallTarget() {
		return callTarget;
	}
	public void setCallTarget(int callTarget) {
		this.callTarget = callTarget;
	}
	public int getRoute_id() {
		return route_id;
	}
	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}
	public String getCoTravellers() {
		return coTravellers;
	}
	public void setCoTravellers(String coTravellers) {
		this.coTravellers = coTravellers;
	}
	public int getExpenseMasterId() {
		return expenseMasterId;
	}
	public void setExpenseMasterId(int expenseMasterId) {
		this.expenseMasterId = expenseMasterId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public boolean isUpdated() {
		return isUpdated;
	}
	public void setUpdated(boolean isUpdated) {
		this.isUpdated = isUpdated;
	}
	
	
}
