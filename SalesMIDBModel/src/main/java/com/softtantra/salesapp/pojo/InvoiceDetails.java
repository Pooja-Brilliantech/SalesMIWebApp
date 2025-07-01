package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "invoice_details")
public class InvoiceDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int invoice_details_id;
	private int invoice_id;
	private int qty;
	private double amount;
	private int product_id;
	private int sales_details_id;
	private int sales_master_id;
	private double discount;

	@Transient
	private String invoicenumber;

	public String getInvoicenumber() {
		return invoicenumber;
	}

	public void setInvoicenumber(String invoicenumber) {
		this.invoicenumber = invoicenumber;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getInvoice_details_id() {
		return invoice_details_id;
	}

	public void setInvoice_details_id(int invoice_details_id) {
		this.invoice_details_id = invoice_details_id;
	}

	public int getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getSales_details_id() {
		return sales_details_id;
	}

	public void setSales_details_id(int sales_details_id) {
		this.sales_details_id = sales_details_id;
	}

	public int getSales_master_id() {
		return sales_master_id;
	}

	public void setSales_master_id(int sales_master_id) {
		this.sales_master_id = sales_master_id;
	}

}
