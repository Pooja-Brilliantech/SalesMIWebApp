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
@Table(name = "pricemaster_details")
public class NewPriceMaster implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pricemaster_id;
	private String product_code;
	private int product_id;
	private String itemcode;	
	
	@Column(nullable=true)
	private String pack;
	
	@Column(nullable=true)
	private String productName;
	
	@Column(nullable=true)
	private String role_id;
	
	@ColumnDefault("0")
	private Double mrp;
	
	@ColumnDefault("0")
	private int created_by;
	
	@CreationTimestamp
	private Date created_date;;
	
	@ColumnDefault("0")
	private int updated_by;
	
	@UpdateTimestamp
	private Date updated_date;
	
	@ColumnDefault("1")
	private int status;
	
	@ColumnDefault("0")
	private int company_id;
	
	@ColumnDefault("0")
	private double pack_weight;
	
	@Column(nullable=true)
	private String pack_weight_unit;
	
	@Column(nullable=true)
	private String packunit;
	
	@Column(nullable=true)
	private String packtitle;
	
	@Column(nullable=true)
	private String piece_pack;
	
	@Column(nullable=true)
	private String piece_unit;
	
	@ColumnDefault("0")
	private int piece_qty;
	
	@ColumnDefault("0")
	private Double piece_mrp;
	
	@Column(nullable=true)
	private String piece_description;
	
	@ColumnDefault("0")
	private int activestatus;

	@ColumnDefault("0")
	private int tax;
	
	@ColumnDefault("0")
	private double rate;
	
	@ColumnDefault("0")
	private double msp;

	@Column(nullable=true)
	private String displaypackname;

	@Column(nullable=true)
	private String pieceWeightUnit;
	
	@ColumnDefault("0")
	private double pieceWeight;

	// columns for SAP Integration
	@Column(nullable = true)
	private String customer_code;
	
	@ColumnDefault("0")
	private String customer_group;
	
	@ColumnDefault("0")
	private int material_group;
	
	@ColumnDefault("0")
	private Long valid_to;
	
	@ColumnDefault("0")
	private Long valid_from;
	
	@Column(nullable = true)
	private String sales_group;
	
	@ColumnDefault("0")
	private int distributor_id;
	
	@ColumnDefault("0")
	private int isvaluepack;
	
	@ColumnDefault("0")
	private int country_id;
	
	@ColumnDefault("0")
	private int state_id;
	
	@ColumnDefault("0")
	private int city_id;
	
	@Transient
	private String tax_key;
	
	@Transient
	private String country_name;
	
	@Transient
	private String state_name;
	
	@Transient
	private String city_name;

	@Transient
	private String role_name;

	@Transient
	private String product_name;
	
	@Transient
	private String brand_name;
	
	@Transient
	private String category_name;
	
	@Transient
	private String sub_category_name;
	
	@Transient
	private String hsncode;
	
	@Transient
	private String barcode;
	
	@Transient
	private String division;
	
	@Transient
	private double uomSize = 1.0d;

	@Transient
	private String piecename_withdescription;
	
	public String getCustomer_code() {
		return customer_code;
	}

	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}

	public String getCustomer_group() {
		return customer_group;
	}

	public void setCustomer_group(String customer_group) {
		this.customer_group = customer_group;
	}

	public int getMaterial_group() {
		return material_group;
	}

	public void setMaterial_group(int material_group) {
		this.material_group = material_group;
	}

	public Long getValid_to() {
		return valid_to;
	}

	public void setValid_to(Long valid_to) {
		this.valid_to = valid_to;
	}

	public Long getValid_from() {
		return valid_from;
	}

	public void setValid_from(Long valid_from) {
		this.valid_from = valid_from;
	}

	public String getSales_group() {
		return sales_group;
	}

	public void setSales_group(String sales_group) {
		this.sales_group = sales_group;
	}

	public int getDistributor_id() {
		return distributor_id;
	}

	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getDisplaypackname() {
		return displaypackname;
	}

	public void setDisplaypackname(String displaypackname) {
		this.displaypackname = displaypackname;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getMsp() {
		return msp;
	}

	public void setMsp(double msp) {
		this.msp = msp;
	}

	public String getPiecename_withdescription() {
		return piecename_withdescription;
	}

	public void setPiecename_withdescription(String piecename_withdescription) {
		this.piecename_withdescription = piecename_withdescription;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}	

	public int getIsvaluepack() {
		return isvaluepack;
	}

	public void setIsvaluepack(int isvaluepack) {
		this.isvaluepack = isvaluepack;
	}

	public String getPiece_pack() {
		return piece_pack;
	}

	public void setPiece_pack(String piece_pack) {
		this.piece_pack = piece_pack;
	}

	public String getPiece_unit() {
		return piece_unit;
	}

	public void setPiece_unit(String piece_unit) {
		this.piece_unit = piece_unit;
	}

	public int getPiece_qty() {
		return piece_qty;
	}

	public void setPiece_qty(int piece_qty) {
		this.piece_qty = piece_qty;
	}

	public Double getPiece_mrp() {
		return piece_mrp;
	}

	public void setPiece_mrp(Double piece_mrp) {
		this.piece_mrp = piece_mrp;
	}

	public String getPiece_description() {
		return piece_description;
	}

	public void setPiece_description(String piece_description) {
		this.piece_description = piece_description;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public int getActivestatus() {
		return activestatus;
	}

	public void setActivestatus(int activestatus) {
		this.activestatus = activestatus;
	}

	public int getPricemaster_id() {
		return pricemaster_id;
	}

	public void setPricemaster_id(int pricemaster_id) {
		this.pricemaster_id = pricemaster_id;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
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

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getPackunit() {
		return packunit;
	}

	public void setPackunit(String packunit) {
		this.packunit = packunit;
	}

	public int getState_id() {
		return state_id;
	}

	public void setState_id(int state_id) {
		this.state_id = state_id;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getPieceWeightUnit() {
		return pieceWeightUnit;
	}

	public void setPieceWeightUnit(String pieceWeightUnit) {
		this.pieceWeightUnit = pieceWeightUnit;
	}

	public double getPieceWeight() {
		return pieceWeight;
	}

	public void setPieceWeight(double pieceWeight) {
		this.pieceWeight = pieceWeight;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPacktitle() {
		return packtitle;
	}

	public void setPacktitle(String packtitle) {
		this.packtitle = packtitle;
	}

	public double getPack_weight() {
		return pack_weight;
	}

	public void setPack_weight(double pack_weight) {
		this.pack_weight = pack_weight;
	}

	public String getPack_weight_unit() {
		return pack_weight_unit;
	}

	public void setPack_weight_unit(String pack_weight_unit) {
		this.pack_weight_unit = pack_weight_unit;
	}

	public String getTax_key() {
		return tax_key;
	}

	public void setTax_key(String tax_key) {
		this.tax_key = tax_key;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getSub_category_name() {
		return sub_category_name;
	}

	public void setSub_category_name(String sub_category_name) {
		this.sub_category_name = sub_category_name;
	}

	public String getHsncode() {
		return hsncode;
	}

	public void setHsncode(String hsncode) {
		this.hsncode = hsncode;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public double getUomSize() {
		return uomSize;
	}

	public void setUomSize(double uomSize) {
		this.uomSize = uomSize;
	}
	
}
