package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "distributor_stock_history")
public class DistributorStockHistory implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stock_history_id;
	private int stock_id;
	private double quantity;
	@CreationTimestamp
	private Date created_date;;
	private int created_by;

	public int getStock_history_id() {
		return stock_history_id;
	}

	public void setStock_history_id(int stock_history_id) {
		this.stock_history_id = stock_history_id;
	}

	public int getStock_id() {
		return stock_id;
	}

	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
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

}
