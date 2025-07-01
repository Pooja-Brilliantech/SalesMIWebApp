package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

public class ProductListMaster implements Serializable{

	// product
	private int product_id;
	private String product_code;
	private String name;
	private Double mrp;
	private String pack;
	private String details;
	private int status;
	private String packtitle;
	private String packunit;
	private int product_stock;
	private int pricemaster_id;
	private String displayName;
	private String barCode;
	private String hsn_code;
	private double uomSize;

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getHsn_code() {
		return hsn_code;
	}

	public void setHsn_code(String hsn_code) {
		this.hsn_code = hsn_code;
	}

	public String getPacktitle() {
		return packtitle;
	}

	public void setPacktitle(String packtitle) {
		this.packtitle = packtitle;
	}

	public String getPackunit() {
		return packunit;
	}

	public void setPackunit(String packunit) {
		this.packunit = packunit;
	}

	// brand
	private int brand_id;
	private String brand_name;

	// category
	private int category_id;
	private String category_name;

	// subcategory
	private int sub_category_id;
	private String sub_category_name;

	private String field1;
	private String field2;

	@CreationTimestamp
	private Date created_date;;
	private BigDecimal stock;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public BigDecimal getStock() {
		return stock;
	}

	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public int getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public int getSub_category_id() {
		return sub_category_id;
	}

	public void setSub_category_id(int sub_category_id) {
		this.sub_category_id = sub_category_id;
	}

	public String getSub_category_name() {
		return sub_category_name;
	}

	public void setSub_category_name(String sub_category_name) {
		this.sub_category_name = sub_category_name;
	}

	public int getProduct_stock() {
		return product_stock;
	}

	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}

	public int getPricemaster_id() {
		return pricemaster_id;
	}

	public void setPricemaster_id(int pricemaster_id) {
		this.pricemaster_id = pricemaster_id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public double getUomSize() {
		return uomSize;
	}

	public void setUomSize(double uomSize) {
		this.uomSize = uomSize;
	}

}
