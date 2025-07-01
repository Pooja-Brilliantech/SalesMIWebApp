package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sales_details")
public class SalesDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sales_details_id;
	private int sales_master_id;
	private int product_id;
	private double product_stock;
	private int current_qty;
	private double product_rate;
	private String display_pack;
	private String pack;
	private String offer_desc;
	@Column(name = "scheme_type", columnDefinition = "int default '0'", nullable = true)
	private int scheme_type;
	@Column(nullable = true)
	private int pricemaster_id;
	private double pack_rate;
	private String product_weight;

	private int item_discount_type; // 1-fixed, 2-percentage
	private double item_discounted_price;

	private double freight_rate;
	private double nsr_difference;
	private double nsr_rate;
	private double nsr_total_with_tax;
	private double nsr_total_without_tax;
	private double credit_note;
	private String pack_qty;
	private double productAmount=0;
	@Column(name = "exwRate", columnDefinition = "double default '0'", nullable = false)
	private double exwRate;

	@Transient
	private String approval_status;

	@Transient
	private String status;

	@Column(nullable = true)
	private String plant;

	@Transient
	private String product_code;

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
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

	@Column(name = "discount", columnDefinition = "double default '0'", nullable = true)
	private double discount;

	@Column(name = "offer_id", columnDefinition = "int default '0'", nullable = true)
	private int offer_id;

	@Column(name = "scheme_id", columnDefinition = "int default '0'", nullable = true)
	private int scheme_id;

	private String scheme_name;

	public String getScheme_name() {
		return scheme_name;
	}

	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
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

	public int getPricemaster_id() {
		return pricemaster_id;
	}

	public void setPricemaster_id(int pricemaster_id) {
		this.pricemaster_id = pricemaster_id;
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

	@Transient
	private BigInteger total_sales;
	@Transient
	private BigInteger total_visit;

	public BigInteger getTotal_sales() {
		return total_sales;
	}

	public void setTotal_sales(BigInteger total_sales) {
		this.total_sales = total_sales;
	}

	public BigInteger getTotal_visit() {
		return total_visit;
	}

	public void setTotal_visit(BigInteger total_visit) {
		this.total_visit = total_visit;
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

	public double getCredit_note() {
		return credit_note;
	}

	public void setCredit_note(double credit_note) {
		this.credit_note = credit_note;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPack_qty() {
		return pack_qty;
	}

	public void setPack_qty(String pack_qty) {
		this.pack_qty = pack_qty;
	}

	public String getApproval_status() {
		return approval_status;
	}

	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
	}

	public String getDisplay_pack() {
		return display_pack;
	}

	public void setDisplay_pack(String display_pack) {
		this.display_pack = display_pack;
	}

	public double getPack_rate() {
		return pack_rate;
	}

	public void setPack_rate(double pack_rate) {
		this.pack_rate = pack_rate;
	}

	public String getProduct_weight() {
		return product_weight;
	}

	public void setProduct_weight(String product_weight) {
		this.product_weight = product_weight;
	}

	public double getExwRate() {
		return exwRate;
	}

	public void setExwRate(double exwRate) {
		this.exwRate = exwRate;
	}

	public double getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(double productAmount) {
		this.productAmount = productAmount;
	}

}
