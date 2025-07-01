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
@Table(name = "sm_outstanding_details")
public class SM_OutstandingDetails implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int outstanding_id;
	private int company_id;
	private int customer_id;
	private double total_bill;
	private double paid_bill;
	private int status;
	private Date date;
	@CreationTimestamp
	private Date created_date;;
	private int created_by;
	@UpdateTimestamp
	private Date updated_date;
	private int updated_by;

	private int invoice_id;

	private int invoice_status;// 0-partial 1-full

	@Transient
	private String tallyinvoice_number;

	public String getCustomer_code() {
		return customer_code;
	}

	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}

	@Transient
	private String customer_name;
	@Transient
	private String customer_code;
	@Transient
	private Double amount;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Transient
	private String update_date;

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public String getTallyinvoice_number() {
		return tallyinvoice_number;
	}

	public void setTallyinvoice_number(String tallyinvoice_number) {
		this.tallyinvoice_number = tallyinvoice_number;
	}

	public int getInvoice_status() {
		return invoice_status;
	}

	public void setInvoice_status(int invoice_status) {
		this.invoice_status = invoice_status;
	}

	public int getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}

	public int getOutstanding_id() {
		return outstanding_id;
	}

	public void setOutstanding_id(int outstanding_id) {
		this.outstanding_id = outstanding_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public double getTotal_bill() {
		return total_bill;
	}

	public void setTotal_bill(double total_bill) {
		this.total_bill = total_bill;
	}

	public double getPaid_bill() {
		return paid_bill;
	}

	public void setPaid_bill(double paid_bill) {
		this.paid_bill = paid_bill;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
