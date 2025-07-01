package com.softtantra.servicemi.pojo;

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
@Table(name = "sm_service_type")
public class SM_ServiceTypeDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int service_type_id;
	private String service_type_name;
	private String description;
	private String type;

	private double billing_rate;
	private int status;
	private int tax_id;
	private String field1;
	private String field2;
	@CreationTimestamp
	private Date created_date;;
	@UpdateTimestamp
	private Date updated_date;
	private int created_by;
	private int updated_by;
	private int company_id;

	private String instructions;
	private String customer_type;

	public String getCustomer_type() {
		return customer_type;
	}

	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	@Transient
	private String billing_rate1;

	public double getTax_per() {
		return tax_per;
	}

	public void setTax_per(double tax_per) {
		this.tax_per = tax_per;
	}

	@Transient
	private double tax_per;

	public String getBilling_rate1() {
		return billing_rate1;
	}

	public void setBilling_rate1(String billing_rate1) {
		this.billing_rate1 = billing_rate1;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "" + this.service_type_id + " " + this.service_type_name + " " + this.description + " " + this.type + " "
				+ this.billing_rate + " " + this.status + " " + this.tax_id + " " + this.field1 + " " + this.field2
				+ " " + this.created_date + " " + this.updated_date + " " + this.created_by + " " + this.updated_by;
	}

	public int getService_type_id() {
		return service_type_id;
	}

	public void setService_type_id(int service_type_id) {
		this.service_type_id = service_type_id;
	}

	public String getService_type_name() {
		return service_type_name;
	}

	public void setService_type_name(String service_type_name) {
		this.service_type_name = service_type_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getBilling_rate() {
		return billing_rate;
	}

	public void setBilling_rate(double billing_rate) {
		this.billing_rate = billing_rate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTax_id() {
		return tax_id;
	}

	public void setTax_id(int tax_id) {
		this.tax_id = tax_id;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

}
