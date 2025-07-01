package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "target_details")
public class TargetDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int target_id;
	
	private int company_id;
	
	@ColumnDefault(value="0")
	private int user_id;
	
	@ColumnDefault(value="0")
	private int status;
	
	@ColumnDefault(value="0")
	private int created_by;
	
	@CreationTimestamp
	private Date created_date;
	
	@ColumnDefault(value="0")
	private int updated_by;
	
	@UpdateTimestamp
	private Date updated_date;
	
	@ColumnDefault(value="0")
	private int target_type;// 1=Daily,2=Weekly,3=Monthly
	
	@ColumnDefault(value="0")
	private double visits;
	
	@ColumnDefault(value="0")
	private double orders;
	
	@ColumnDefault(value="0.0")
	private double paymentTarget;
	
	@ColumnDefault(value="0.0")
	private double amount;
	
	private Date from_date;
	
	private Date to_date;
	
	@ColumnDefault(value="0.0")
	private double collection;
	
	@ColumnDefault(value="0.0")
	private double volume_target;
	
	@Column(nullable=true)
	private String role;
	
	@Column(nullable=true)
	private String month;
	
	@ColumnDefault(value="0")
	private int city;
	
	@ColumnDefault(value="0")
	private int state;
	
	@ColumnDefault(value="0")
	private int country_id;
	
	@ColumnDefault(value="0")
	private int finanacialYear;
	
	@ColumnDefault(value="0")
	private int categoryId;
	
	private int roleId;
	
	private String daysNumber;
	private String weekNumber;
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public double getCollection() {
		return collection;
	}

	public void setCollection(double collection) {
		this.collection = collection;
	}

	public double getVolume_target() {
		return volume_target;
	}

	public void setVolume_target(double volume_target) {
		this.volume_target = volume_target;
	}

	public int getTarget_id() {
		return target_id;
	}

	public void setTarget_id(int target_id) {
		this.target_id = target_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
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

	public int getTarget_type() {
		return target_type;
	}

	public void setTarget_type(int target_type) {
		this.target_type = target_type;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getFrom_date() {
		return from_date;
	}

	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}

	public Date getTo_date() {
		return to_date;
	}

	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}

	public double getPaymentTarget() {
		return paymentTarget;
	}

	public void setPaymentTarget(double paymentTarget) {
		this.paymentTarget = paymentTarget;
	}

	public int getFinanacialYear() {
		return finanacialYear;
	}

	public void setFinanacialYear(int finanacialYear) {
		this.finanacialYear = finanacialYear;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getDaysNumber() {
		return daysNumber;
	}

	public void setDaysNumber(String daysNumber) {
		this.daysNumber = daysNumber;
	}

	public String getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(String weekNumber) {
		this.weekNumber = weekNumber;
	}
	
	

}
