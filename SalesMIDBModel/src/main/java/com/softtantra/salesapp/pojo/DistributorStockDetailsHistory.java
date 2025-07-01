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
@Table(name = "distributor_stock_details_history")
public class DistributorStockDetailsHistory implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int distributor_stock_details_history_id;
	private int stock_id=0;
	private int company_id;
	private int product_id=0;
	private int distributor_id=0;
	private double quantity=0.0d; // actual stock
	private double minimum_stock=0.0d; // minimum stock
	private Date minimum_stock_updated_date; // actual sctock updated date
	private int status;
	@CreationTimestamp
	private Date created_date;;
	private int created_by;
	@UpdateTimestamp
	private Date updated_date;
	private int updated_by;
	@Column(nullable=true)
	private String pack;
	@Column(nullable=true)
	private String packunit;
	private int pricemaster_id=0;
	private double closing_stock=0.0d;
	private double diff_stock=0.0d;
	private int actual_stock_updated_type=0;
	@Column(nullable=true)
	private int distributorLocationId=0;
	@Column(nullable=true)
	private String item_code;


	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	/*
	 * 1.Stock increase manually 2.Stock decrease manually 3.invoice generated
	 * -stock decrease 4.primary order-stock increase
	 *
	 */
	private double opening_stock;
	private String description_stock_update;

	public double getOpening_stock() {
		return opening_stock;
	}

	public void setOpening_stock(double opening_stock) {
		this.opening_stock = opening_stock;
	}

	public double getClosing_stock() {
		return closing_stock;
	}

	public void setClosing_stock(double closing_stock) {
		this.closing_stock = closing_stock;
	}

	public double getDiff_stock() {
		return diff_stock;
	}

	public void setDiff_stock(double diff_stock) {
		this.diff_stock = diff_stock;
	}

	public void setDiff_stock(int diff_stock) {
		this.diff_stock = diff_stock;
	}

	public int getActual_stock_updated_type() {
		return actual_stock_updated_type;
	}

	public void setActual_stock_updated_type(int actual_stock_updated_type) {
		this.actual_stock_updated_type = actual_stock_updated_type;
	}

	public String getDescription_stock_update() {
		return description_stock_update;
	}

	public void setDescription_stock_update(String description_stock_update) {
		this.description_stock_update = description_stock_update;
	}

	public int getDistributor_stock_details_history_id() {
		return distributor_stock_details_history_id;
	}

	public void setDistributor_stock_details_history_id(int distributor_stock_details_history_id) {
		this.distributor_stock_details_history_id = distributor_stock_details_history_id;
	}

	public int getStock_id() {
		return stock_id;
	}

	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
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

	public int getDistributor_id() {
		return distributor_id;
	}

	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getMinimum_stock() {
		return minimum_stock;
	}

	public void setMinimum_stock(double minimum_stock) {
		this.minimum_stock = minimum_stock;
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

	public String getPackunit() {
		return packunit;
	}

	public void setPackunit(String packunit) {
		this.packunit = packunit;
	}

	public int getPricemaster_id() {
		return pricemaster_id;
	}

	public void setPricemaster_id(int pricemaster_id) {
		this.pricemaster_id = pricemaster_id;
	}

	public int getDistributorLocationId() {
		return distributorLocationId;
	}

	public void setDistributorLocationId(int distributorLocationId) {
		this.distributorLocationId = distributorLocationId;
	}

}
