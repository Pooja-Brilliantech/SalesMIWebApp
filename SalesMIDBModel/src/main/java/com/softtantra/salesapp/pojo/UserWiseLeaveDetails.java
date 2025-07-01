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

@Entity
@Table(name = "user_wise_leave_details")
public class UserWiseLeaveDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int user_id;
	private int leaveId;
	private int companyId;
	private double leaveBalanceCount;
	private double noOfLeaves;
	private double leavesConsumed;
	private String empCode;
	@Transient
	private String userName;
	@Transient
	private String leaveName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLeaveName() {
		return leaveName;
	}
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
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
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	
	
	
	
}
