package com.softtantra.servicemi.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sm_outstanding_import")
public class SM_OutstandingImport implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int user_id;
	private int status;
	private String comment;
	private int import_id;
	private int company_id;
	private String invoice_id;
	private String credit_limit;
	private String credit_date;
	private String customer_name;
	private String total_bill;
	private String odate;

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

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getTotal_bill() {
		return total_bill;
	}

	public void setTotal_bill(String total_bill) {
		this.total_bill = total_bill;
	}

	public String getOdate() {
		return odate;
	}

	public void setOdate(String odate) {
		this.odate = odate;
	}

	public String getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}

	public String getCredit_limit() {
		return credit_limit;
	}

	public void setCredit_limit(String credit_limit) {
		this.credit_limit = credit_limit;
	}

	public String getCredit_date() {
		return credit_date;
	}

	public void setCredit_date(String credit_date) {
		this.credit_date = credit_date;
	}

}
