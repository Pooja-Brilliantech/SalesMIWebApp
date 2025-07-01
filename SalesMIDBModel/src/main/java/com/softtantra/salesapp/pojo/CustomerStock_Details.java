package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "customerstock_details")
public class CustomerStock_Details implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stock_id;
	private long company_id;
	private int customer_id;
	private int distributor_id;
	private int product_id;
	private int pricemaster_id;
	private double product_stock;
	private int status;// (1-Active ,0-Inactive)
	private long created_by;
	private Date stockUpdatedDate;
	private Date stockDate;
	@CreationTimestamp
	private Date created_date;;
	private long updated_by;
	@UpdateTimestamp
	private Date updated_date;
	@Transient
	private String customer_name;
	@Transient
	private String distributor_name;

	@Transient
	private String product_name;

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getDistributor_name() {
		return distributor_name;
	}

	public void setDistributor_name(String distributor_name) {
		this.distributor_name = distributor_name;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getStock_id() {
		return stock_id;
	}

	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}

	public long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getDistributor_id() {
		return distributor_id;
	}

	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}

	public double getProduct_stock() {
		return product_stock;
	}

	public void setProduct_stock(double product_stock) {
		this.product_stock = product_stock;
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

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getPricemaster_id() {
		return pricemaster_id;
	}

	public void setPricemaster_id(int pricemaster_id) {
		this.pricemaster_id = pricemaster_id;
	}

	public long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(long created_by) {
		this.created_by = created_by;
	}

	public long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(long updated_by) {
		this.updated_by = updated_by;
	}

	@Transient
	private String c_date;

	public String getC_date() {
		return c_date;
	}

	public void setC_date(String c_date) {
		this.c_date = c_date;
	}

	public Date getStockUpdatedDate() {
		return stockUpdatedDate;
	}

	public void setStockUpdatedDate(Date stockUpdatedDate) {
		this.stockUpdatedDate = stockUpdatedDate;
	}

	public Date getStockDate() {
		return stockDate;
	}

	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

}
