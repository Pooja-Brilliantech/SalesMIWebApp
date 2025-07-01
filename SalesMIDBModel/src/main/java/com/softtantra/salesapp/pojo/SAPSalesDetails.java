package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sap_sales_details")
public class SAPSalesDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sap_details_id;
	private int sales_master_id;
	private double credit_note;
	private int company_id;
	private int customer_id;

	public int getSap_details_id() {
		return sap_details_id;
	}

	public void setSap_details_id(int sap_details_id) {
		this.sap_details_id = sap_details_id;
	}

	public int getSales_master_id() {
		return sales_master_id;
	}

	public void setSales_master_id(int sales_master_id) {
		this.sales_master_id = sales_master_id;
	}

	public double getCredit_note() {
		return credit_note;
	}

	public void setCredit_note(double credit_note) {
		this.credit_note = credit_note;
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

}
