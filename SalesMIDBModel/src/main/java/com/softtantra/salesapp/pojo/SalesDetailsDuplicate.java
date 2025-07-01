package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sales_details_duplicate")
public class SalesDetailsDuplicate implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sales_details_duplicate_id;
	private int sales_details_id;
	private int sales_master_id;
	private int product_id;
	private double product_stock;
	private int current_qty;
	private double product_rate;
	private double credit_note;

	@Column(name = "discount", columnDefinition = "double default '0'", nullable = true)
	private double discount;
	private String offer_desc;

	private double freight_rate;
	private double nsr_difference;
	private double nsr_rate;
	private double nsr_total_with_tax;
	private double nsr_total_without_tax;

	@Column(name = "offer_id", columnDefinition = "int default '0'", nullable = true)
	private int offer_id;

	@Column(name = "scheme_id", columnDefinition = "int default '0'", nullable = true)
	private int scheme_id;
	private String scheme_name;

	@Column(name = "scheme_type", columnDefinition = "int default '0'", nullable = true)
	private int scheme_type;

	@Column(nullable = true)
	private int pricemaster_id;

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

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public double getProduct_stock() {
		return product_stock;
	}

	public void setProduct_stock(double product_stock) {
		this.product_stock = product_stock;
	}

	public int getCurrent_qty() {
		return current_qty;
	}

	public void setCurrent_qty(int current_qty) {
		this.current_qty = current_qty;
	}

	public double getProduct_rate() {
		return product_rate;
	}

	public void setProduct_rate(double product_rate) {
		this.product_rate = product_rate;
	}

	public double getCredit_note() {
		return credit_note;
	}

	public void setCredit_note(double credit_note) {
		this.credit_note = credit_note;
	}

	public int getPricemaster_id() {
		return pricemaster_id;
	}

	public void setPricemaster_id(int pricemaster_id) {
		this.pricemaster_id = pricemaster_id;
	}

	public int getSales_details_duplicate_id() {
		return sales_details_duplicate_id;
	}

	public void setSales_details_duplicate_id(int sales_details_duplicate_id) {
		this.sales_details_duplicate_id = sales_details_duplicate_id;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getFreight_rate() {
		return freight_rate;
	}

	public void setFreight_rate(double freight_rate) {
		this.freight_rate = freight_rate;
	}

	public double getNsr_difference() {
		return nsr_difference;
	}

	public void setNsr_difference(double nsr_difference) {
		this.nsr_difference = nsr_difference;
	}

	public double getNsr_rate() {
		return nsr_rate;
	}

	public void setNsr_rate(double nsr_rate) {
		this.nsr_rate = nsr_rate;
	}

	public double getNsr_total_with_tax() {
		return nsr_total_with_tax;
	}

	public void setNsr_total_with_tax(double nsr_total_with_tax) {
		this.nsr_total_with_tax = nsr_total_with_tax;
	}

	public double getNsr_total_without_tax() {
		return nsr_total_without_tax;
	}

	public void setNsr_total_without_tax(double nsr_total_without_tax) {
		this.nsr_total_without_tax = nsr_total_without_tax;
	}

	public String getOffer_desc() {
		return offer_desc;
	}

	public void setOffer_desc(String offer_desc) {
		this.offer_desc = offer_desc;
	}

	public int getOffer_id() {
		return offer_id;
	}

	public void setOffer_id(int offer_id) {
		this.offer_id = offer_id;
	}

	public int getScheme_id() {
		return scheme_id;
	}

	public void setScheme_id(int scheme_id) {
		this.scheme_id = scheme_id;
	}

	public String getScheme_name() {
		return scheme_name;
	}

	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}

	public int getScheme_type() {
		return scheme_type;
	}

	public void setScheme_type(int scheme_type) {
		this.scheme_type = scheme_type;
	}

}