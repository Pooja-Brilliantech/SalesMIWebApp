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
@Table(name = "primary_stock_details_history")
public class PrimaryStockDetailsHistory implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int primary_stock_details_history_id;
	private int primary_stock_details_id=0;
	private int company_id;
	private int product_id=0;
	private double quantity=0.0d;
	private Date minimum_stock_updated_date;
	private int status;
	public String getCompany_location_code() {
		return company_location_code;
	}

	public void setCompany_location_code(String company_location_code) {
		this.company_location_code = company_location_code;
	}

	@CreationTimestamp
	private Date created_date;;
	private int created_by;
	@UpdateTimestamp
	private Date updated_date;
	private int updated_by;
	@Column(nullable=true)
	private String pack;
	private int pricemaster_id=0;
	private int company_location_id=0;
	@Column(nullable=true)
	private String company_location_name;
	@Column(nullable=true)
	private String company_location_code;
	private double minimum_stock=0.0d;

	public int getCompany_location_id() {
		return company_location_id;
	}

	public void setCompany_location_id(int company_location_id) {
		this.company_location_id = company_location_id;
	}

	public String getCompany_location_name() {
		return company_location_name;
	}

	public void setCompany_location_name(String company_location_name) {
		this.company_location_name = company_location_name;
	}

	public int getPrimary_stock_details_history_id() {
		return primary_stock_details_history_id;
	}

	public void setPrimary_stock_details_history_id(int primary_stock_details_history_id) {
		this.primary_stock_details_history_id = primary_stock_details_history_id;
	}

	public int getPrimary_stock_details_id() {
		return primary_stock_details_id;
	}

	public void setPrimary_stock_details_id(int primary_stock_details_id) {
		this.primary_stock_details_id = primary_stock_details_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Date getMinimum_stock_updated_date() {
		return minimum_stock_updated_date;
	}

	public void setMinimum_stock_updated_date(Date minimum_stock_updated_date) {
		this.minimum_stock_updated_date = minimum_stock_updated_date;
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

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public int getPricemaster_id() {
		return pricemaster_id;
	}

	public void setPricemaster_id(int pricemaster_id) {
		this.pricemaster_id = pricemaster_id;
	}

	public double getMinimum_stock() {
		return minimum_stock;
	}

	public void setMinimum_stock(double minimum_stock) {
		this.minimum_stock = minimum_stock;
	}

}
