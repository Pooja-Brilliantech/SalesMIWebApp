package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "customer_target_records_details")
public class CustomerTargetRecordsDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_target_records_id;
	private int customer_target_id;
	private String customerName;
	private String customerCode;
	private int customer_id;
	private String month;
	private double visits;
	private double orders;
	private double collection;
	private double amount;
	private double volume;
	private int categoryId;
	private String categoryName;
	private double totalTarget;
	public int getCustomer_target_records_id() {
		return customer_target_records_id;
	}
	public void setCustomer_target_records_id(int customer_target_records_id) {
		this.customer_target_records_id = customer_target_records_id;
	}
	public int getCustomer_target_id() {
		return customer_target_id;
	}
	public void setCustomer_target_id(int customer_target_id) {
		this.customer_target_id = customer_target_id;
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
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public double getVisits() {
		return visits;
	}
	public void setVisits(double visits) {
		this.visits = visits;
	}
	public double getOrders() {
		return orders;
	}
	public void setOrders(double orders) {
		this.orders = orders;
	}
	public double getCollection() {
		return collection;
	}
	public void setCollection(double collection) {
		this.collection = collection;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
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
	public double getTotalTarget() {
		return totalTarget;
	}
	public void setTotalTarget(double totalTarget) {
		this.totalTarget = totalTarget;
	}
	
	
	
}
