package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "primary_order_invoice_details")
public class PrimaryOrderInvoiceDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int primary_order_invoice_details_id;
	private int primary_order_invoice_id;
	private int primary_order_details_id;
	private int primary_order_id;
	private int product_id;
	private int quantity;
	private String pack;
	private int status;
	@Column(nullable = true)
	private int pricemaster_id;
	@Column(name = "discount", columnDefinition = "double default '0'", nullable = true)
	private double discount;
	@Column(name = "rate", columnDefinition = "double default '0'", nullable = true)
	private double rate;
	@Column(name = "total", columnDefinition = "double default '0'", nullable = true)
	private double total;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;

	public int getPrimary_order_invoice_details_id() {
		return primary_order_invoice_details_id;
	}

	public void setPrimary_order_invoice_details_id(int primary_order_invoice_details_id) {
		this.primary_order_invoice_details_id = primary_order_invoice_details_id;
	}

	public int getPrimary_order_invoice_id() {
		return primary_order_invoice_id;
	}

	public void setPrimary_order_invoice_id(int primary_order_invoice_id) {
		this.primary_order_invoice_id = primary_order_invoice_id;
	}

	public int getPrimary_order_details_id() {
		return primary_order_details_id;
	}

	public void setPrimary_order_details_id(int primary_order_details_id) {
		this.primary_order_details_id = primary_order_details_id;
	}

	public int getPrimary_order_id() {
		return primary_order_id;
	}

	public void setPrimary_order_id(int primary_order_id) {
		this.primary_order_id = primary_order_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPricemaster_id() {
		return pricemaster_id;
	}

	public void setPricemaster_id(int pricemaster_id) {
		this.pricemaster_id = pricemaster_id;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
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

}
