package com.softtantra.servicemi.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sm_customer_outstanding")
public class SM_Customer_Outstanding implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_outstanding_id;
	private int customer_id;
	private double outstanding_amount;

	public int getCustomer_outstanding_id() {
		return customer_outstanding_id;
	}

	public void setCustomer_outstanding_id(int customer_outstanding_id) {
		this.customer_outstanding_id = customer_outstanding_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public double getOutstanding_amount() {
		return outstanding_amount;
	}

	public void setOutstanding_amount(double outstanding_amount) {
		this.outstanding_amount = outstanding_amount;
	}

}
