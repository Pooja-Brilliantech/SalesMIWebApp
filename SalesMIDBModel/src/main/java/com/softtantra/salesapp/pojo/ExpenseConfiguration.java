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
@Table(name = "expense_configuration")
public class ExpenseConfiguration implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expense_config_id;
	private int company_id;

	private String expense_type_id; // 1-Local,2-Outstation
	private int user_role;
	private String expense_mode_type;
	private double max_Allowed_Amt;
	private int isDefault=0;
	private int isfixed=0;
	private int isDistance=0;
	private int country_id;
	private int state_id;
	private int city_id;
	private String expenseCode;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private String cityType;
	@Transient

	private int role_id;
	private Double amt;
	private String modeOfTravel;
	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	public int getExpense_config_id() {
		return expense_config_id;
	}

	public void setExpense_config_id(int expense_config_id) {
		this.expense_config_id = expense_config_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	

	public String getExpense_type_id() {
		return expense_type_id;
	}

	public void setExpense_type_id(String expense_type_id) {
		this.expense_type_id = expense_type_id;
	}

	public int getUser_role() {
		return user_role;
	}

	public void setUser_role(int user_role) {
		this.user_role = user_role;
	}

	public String getExpense_mode_type() {
		return expense_mode_type;
	}

	public void setExpense_mode_type(String expense_mode_type) {
		this.expense_mode_type = expense_mode_type;
	}

	public double getMax_Allowed_Amt() {
		return max_Allowed_Amt;
	}

	public void setMax_Allowed_Amt(double max_Allowed_Amt) {
		this.max_Allowed_Amt = max_Allowed_Amt;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	public int getIsDistance() {
		return isDistance;
	}

	public void setIsDistance(int isDistance) {
		this.isDistance = isDistance;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public int getState_id() {
		return state_id;
	}

	public void setState_id(int state_id) {
		this.state_id = state_id;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
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
	@Transient
	private String countryName;
	@Transient
	private String stateName;
	@Transient
	private String cityName;
	@Transient
	private String isDefault1;
	@Transient
	private String isDistance1;
	@Transient
	private String isfixed1;
	public String getIsfixed1() {
		return isfixed1;
	}

	public void setIsfixed1(String isfixed1) {
		this.isfixed1 = isfixed1;
	}
	@Transient
	private String expenseType;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getIsDefault1() {
		return isDefault1;
	}

	public void setIsDefault1(String isDefault1) {
		this.isDefault1 = isDefault1;
	}

	public String getIsDistance1() {
		return isDistance1;
	}

	public void setIsDistance1(String isDistance1) {
		this.isDistance1 = isDistance1;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public String getCityType() {
		return cityType;
	}

	public void setCityType(String cityType) {
		this.cityType = cityType;
	}

	public String getExpenseCode() {
		return expenseCode;
	}

	public void setExpenseCode(String expenseCode) {
		this.expenseCode = expenseCode;
	}

	public String getModeOfTravel() {
		return modeOfTravel;
	}

	public void setModeOfTravel(String modeOfTravel) {
		this.modeOfTravel = modeOfTravel;
	}

	public int getIsfixed() {
		return isfixed;
	}

	public void setIsfixed(int isfixed) {
		this.isfixed = isfixed;
	}
	
	
}