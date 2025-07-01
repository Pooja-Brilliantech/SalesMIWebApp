package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Entity
@Table(name = "expense_configuration_import_records")
public class ExpenseConfigurationImportRecords implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int status;
	private String comment;
	private int import_id;
	private int company_id;
	private String expense_type_id; 
	private String user_role;
	private String expense_mode_type;
	private double max_Allowed_Amt;
	private String isDefault;
	private String isfixed;
	private String isDistance;
	private String expenseCode;
	private int created_by;
	private double amt;
	private String modeOfTravel;
	@CreationTimestamp
	private Date created_date;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private String cityType;
	public int getRecords_id() {
		return records_id;
	}
	public void setRecords_id(int records_id) {
		this.records_id = records_id;
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
	public String getExpense_type_id() {
		return expense_type_id;
	}
	public void setExpense_type_id(String expense_type_id) {
		this.expense_type_id = expense_type_id;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
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
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getIsfixed() {
		return isfixed;
	}
	public void setIsfixed(String isfixed) {
		this.isfixed = isfixed;
	}
	public String getIsDistance() {
		return isDistance;
	}
	public void setIsDistance(String isDistance) {
		this.isDistance = isDistance;
	}
	public String getExpenseCode() {
		return expenseCode;
	}
	public void setExpenseCode(String expenseCode) {
		this.expenseCode = expenseCode;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}
	public String getModeOfTravel() {
		return modeOfTravel;
	}
	public void setModeOfTravel(String modeOfTravel) {
		this.modeOfTravel = modeOfTravel;
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
	public String getCityType() {
		return cityType;
	}
	public void setCityType(String cityType) {
		this.cityType = cityType;
	}
	
	
}
