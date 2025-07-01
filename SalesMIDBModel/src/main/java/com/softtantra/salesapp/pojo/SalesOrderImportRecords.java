package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Entity
@Table(name = "sales_order_import_records")
public class SalesOrderImportRecords implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int user_id;
	private int status;
	private String comment;
	private int import_id;
	private int company_id;
	private String customerName;
	private String customerCode;
	private String productCode;
	private String productName;
	private double totalWeightTon;
	private String address;
	private int created_by;
	@CreationTimestamp
	private Date created_date;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private double totalAmount;
	//Sales Details
	
	private double product_stock;//qty
	private double product_rate;
	private String display_pack;
	private String pack;
	private String offer_desc;
	private int pricemaster_id;
	private double pack_rate;
	private int item_discount_type; // 1-fixed, 2-percentage
	private double item_discounted_price;
	private int product_id;
	private double product_weight;//
	private String salesOrderNumber;//
	private double product_amount;
	public int getRecords_id() {
		return records_id;
	}
	public void setRecords_id(int records_id) {
		this.records_id = records_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getImport_id() {
		return import_id;
	}
	public void setImport_id(int import_id) {
		this.import_id = import_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getTotalWeightTon() {
		return totalWeightTon;
	}
	public void setTotalWeightTon(double totalWeightTon) {
		this.totalWeightTon = totalWeightTon;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getProduct_stock() {
		return product_stock;
	}
	public void setProduct_stock(double product_stock) {
		this.product_stock = product_stock;
	}
	public double getProduct_rate() {
		return product_rate;
	}
	public void setProduct_rate(double product_rate) {
		this.product_rate = product_rate;
	}
	public String getDisplay_pack() {
		return display_pack;
	}
	public void setDisplay_pack(String display_pack) {
		this.display_pack = display_pack;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public String getOffer_desc() {
		return offer_desc;
	}
	public void setOffer_desc(String offer_desc) {
		this.offer_desc = offer_desc;
	}
	public int getPricemaster_id() {
		return pricemaster_id;
	}
	public void setPricemaster_id(int pricemaster_id) {
		this.pricemaster_id = pricemaster_id;
	}
	public double getPack_rate() {
		return pack_rate;
	}
	public void setPack_rate(double pack_rate) {
		this.pack_rate = pack_rate;
	}
	public int getItem_discount_type() {
		return item_discount_type;
	}
	public void setItem_discount_type(int item_discount_type) {
		this.item_discount_type = item_discount_type;
	}
	public double getItem_discounted_price() {
		return item_discounted_price;
	}
	public void setItem_discounted_price(double item_discounted_price) {
		this.item_discounted_price = item_discounted_price;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public double getProduct_weight() {
		return product_weight;
	}
	public void setProduct_weight(double product_weight) {
		this.product_weight = product_weight;
	}
	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}
	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}
	public double getProduct_amount() {
		return product_amount;
	}
	public void setProduct_amount(double product_amount) {
		this.product_amount = product_amount;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	
	

	

}
