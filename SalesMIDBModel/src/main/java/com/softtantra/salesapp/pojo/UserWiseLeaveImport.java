package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "userwise_leave_type_import_records")
public class UserWiseLeaveImport implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int leaveId;
	private int companyId;
	private double leaveBalanceCount;
	private double noOfLeaves;
	private double leavesConsumed;
	private String comment;
	private int import_id;
	private int created_by;
	private int user_id;
	private int status;
	private String leaveName;
	private String userName;
	
	public int getRecords_id() {
		return records_id;
	}
	public void setRecords_id(int records_id) {
		this.records_id = records_id;
	}
	public int getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public double getLeaveBalanceCount() {
		return leaveBalanceCount;
	}
	public void setLeaveBalanceCount(double leaveBalanceCount) {
		this.leaveBalanceCount = leaveBalanceCount;
	}
	public double getNoOfLeaves() {
		return noOfLeaves;
	}
	public void setNoOfLeaves(double noOfLeaves) {
		this.noOfLeaves = noOfLeaves;
	}
	public double getLeavesConsumed() {
		return leavesConsumed;
	}
	public void setLeavesConsumed(double leavesConsumed) {
		this.leavesConsumed = leavesConsumed;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getImport_id() {
		return import_id;
	}
	public void setImport_id(int import_id) {
		this.import_id = import_id;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
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
	public String getLeaveName() {
		return leaveName;
	}
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
