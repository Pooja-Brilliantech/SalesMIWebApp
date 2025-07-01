package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "challen_details")
public class ChallenDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int challen_details_id;
	private int challen_id;
	private int qty;
	private double amount;
	private int product_id;
	private int sales_details_id;
	private int sales_master_id;
	private double discount;

	public int getChallen_details_id() {
		return challen_details_id;
	}

	public void setChallen_details_id(int challen_details_id) {
		this.challen_details_id = challen_details_id;
	}

	public int getChallen_id() {
		return challen_id;
	}

	public void setChallen_id(int challen_id) {
		this.challen_id = challen_id;
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

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

}
