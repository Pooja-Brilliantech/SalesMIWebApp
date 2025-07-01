package com.softtantra.servicemi.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sm_invoice_details")
public class SM_InvoiceDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int invoice_detail_id;

	private long invoice_id;
	private int company_id;
	private int quantity;
	private double rate;
	private double tax;
	private double amount;
	private int service_id;
	private int material_id;
	private double discount;

	public long getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(long invoice_id) {
		this.invoice_id = invoice_id;
	}

	public int getInvoice_detail_id() {
		return invoice_detail_id;
	}

	public void setInvoice_detail_id(int invoice_detail_id) {
		this.invoice_detail_id = invoice_detail_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public int getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(int material_id) {
		this.material_id = material_id;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

}
