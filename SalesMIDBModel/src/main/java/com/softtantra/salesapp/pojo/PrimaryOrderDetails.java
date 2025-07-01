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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "primary_order_details")
public class PrimaryOrderDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int primary_order_details_id;
	private int primary_order_id;
	private int product_id;
	private int quantity;
	private String pack;
	private String offer_desc;
	private int status;
	@Column(name = "scheme_type", columnDefinition = "int default '0'", nullable = true)
	private int scheme_type;
	@Column(nullable = true)
	private int pricemaster_id;

	@Column(name = "discount", columnDefinition = "double default '0'", nullable = true)
	private double discount;

	@Column(name = "rate", columnDefinition = "double default '0'", nullable = true)
	private double rate;

	@Column(name = "total", columnDefinition = "double default '0'", nullable = true)
	private double total;

	@Column(name = "offer_id", columnDefinition = "int default '0'", nullable = true)
	private int offer_id;

	@Column(name = "scheme_id", columnDefinition = "int default '0'", nullable = true)
	private int scheme_id;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;

	private String scheme_name;
	@Transient
	private int distributor_id;
	@Transient
	private String distributor_name;
	@Transient
	private String product_name;
	@Transient
	private double mrp1;

	@Transient
	private double mrp2;

	@Transient
	private double mrp3;

	@Transient
	private String formated_date;

	@Transient
	private String onlyPack;

	@Transient
	private String onlyUnit;

	@Transient
	private String product_code;

	@Transient
	private String displayName;

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

	public String getOffer_desc() {
		return offer_desc;
	}

	public void setOffer_desc(String offer_desc) {
		this.offer_desc = offer_desc;
	}

	public int getScheme_type() {
		return scheme_type;
	}

	public void setScheme_type(int scheme_type) {
		this.scheme_type = scheme_type;
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

	public int getDistributor_id() {
		return distributor_id;
	}

	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
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

	public double getMrp1() {
		return mrp1;
	}

	public void setMrp1(double mrp1) {
		this.mrp1 = mrp1;
	}

	public double getMrp2() {
		return mrp2;
	}

	public void setMrp2(double mrp2) {
		this.mrp2 = mrp2;
	}

	public double getMrp3() {
		return mrp3;
	}

	public void setMrp3(double mrp3) {
		this.mrp3 = mrp3;
	}

	public String getFormated_date() {
		return formated_date;
	}

	public void setFormated_date(String formated_date) {
		this.formated_date = formated_date;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getOnlyPack() {
		return onlyPack;
	}

	public void setOnlyPack(String onlyPack) {
		this.onlyPack = onlyPack;
	}

	public String getOnlyUnit() {
		return onlyUnit;
	}

	public void setOnlyUnit(String onlyUnit) {
		this.onlyUnit = onlyUnit;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
