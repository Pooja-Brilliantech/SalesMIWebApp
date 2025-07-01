package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "customer_target_import_records")
public class CustomerTargetImportRecords implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int user_id;
	private int status;
	private String comment;
	private String userName;
	private String customerName;
	private String customerCode;
	private String month;
	private String visits;
	private String orders;
	private String collection;
	private String amount;
	private String volume;
	private int import_id;
	private int company_id;
	private int finanacialYear;
	private int categoryId;
	private String categoryName;
	private String achievedTarget;
	private String totalTarget;
	public int getRecords_id() {
		return records_id;
	}
	public void setRecords_id(int records_id) {
		this.records_id = records_id;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getVisits() {
		return visits;
	}
	public void setVisits(String visits) {
		this.visits = visits;
	}
	public String getOrders() {
		return orders;
	}
	public void setOrders(String orders) {
		this.orders = orders;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public int getImport_id() {
		return import_id;
	}
	public void setImport_id(int import_id) {
		this.import_id = import_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getFinanacialYear() {
		return finanacialYear;
	}
	public void setFinanacialYear(int finanacialYear) {
		this.finanacialYear = finanacialYear;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getAchievedTarget() {
		return achievedTarget;
	}
	public void setAchievedTarget(String achievedTarget) {
		this.achievedTarget = achievedTarget;
	}
	public String getTotalTarget() {
		return totalTarget;
	}
	public void setTotalTarget(String totalTarget) {
		this.totalTarget = totalTarget;
	}
	
	
	
	
	
	
}
