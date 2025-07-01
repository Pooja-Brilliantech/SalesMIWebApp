package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_import_records")
public class ProductImportRecords implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private int user_id;
	private int import_id;
	private int company_id;
	private int status;
	
	@Column(nullable = true)
	private String comment;	

	@Column(nullable = true)
	private String product_code;
	
	@Column(nullable = true)
	private String product_name;
	
	@Column(nullable = true)
	private String packtitle;
	
	@Column(nullable = true)
	private String brand;
	
	@Column(nullable = true)
	private String categoery;
	
	@Column(nullable = true)
	private String sub_categoery;
	
	@Column(nullable = true)
	private String hsncode;
	
	@Column(nullable = true)
	private String barCode;
	
	private double productWeight;
	private double uomSize;
	
	//price master column
	@Column(nullable = true)
	private String item_code;
	
	@Column(nullable = true)
	private String pack;
	
	@Column(nullable = true)
	private String packunit;
	
	private double mrp =0.0;
	private double rate=0.0;
	
	@Column(nullable = true)
	private String tax;
	
	private double msp=0.0;
	
	@Column(nullable = true)
	private String countryName;
	
	@Column(nullable = true)
	private String stateName;
	
	@Column(nullable = true)
	private String cityName;
	
	@Column(nullable = true)
	private String piecePack;
	
	@Column(nullable = true)
	private String pieceUnit;
	
	@Column(nullable = true)
	private String pieceQty;
	
	@Column(nullable=true)
	private String pieceWeightUnit;
	
	@Column(nullable=true)
	private String pieceWeight;
	
	@Column(nullable=true)
	private String pieceMrp;
	
	@Column(nullable=true)
	private String roleName;
	
	@Column(nullable=true)
	private String isValuePack;
	
	@Column(nullable=true)
	private String material_group;
	
	@Column(nullable=true)
	private String division;

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

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getPacktitle() {
		return packtitle;
	}

	public void setPacktitle(String packtitle) {
		this.packtitle = packtitle;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategoery() {
		return categoery;
	}

	public void setCategoery(String categoery) {
		this.categoery = categoery;
	}

	public String getSub_categoery() {
		return sub_categoery;
	}

	public void setSub_categoery(String sub_categoery) {
		this.sub_categoery = sub_categoery;
	}

	public String getHsncode() {
		return hsncode;
	}

	public void setHsncode(String hsncode) {
		this.hsncode = hsncode;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public double getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(double productWeight) {
		this.productWeight = productWeight;
	}

	public double getUomSize() {
		return uomSize;
	}

	public void setUomSize(double uomSize) {
		this.uomSize = uomSize;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
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

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public double getMsp() {
		return msp;
	}

	public void setMsp(double msp) {
		this.msp = msp;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPiecePack() {
		return piecePack;
	}

	public void setPiecePack(String piecePack) {
		this.piecePack = piecePack;
	}

	public String getPieceUnit() {
		return pieceUnit;
	}

	public void setPieceUnit(String pieceUnit) {
		this.pieceUnit = pieceUnit;
	}

	public String getPieceQty() {
		return pieceQty;
	}

	public void setPieceQty(String pieceQty) {
		this.pieceQty = pieceQty;
	}

	public String getPieceWeightUnit() {
		return pieceWeightUnit;
	}

	public void setPieceWeightUnit(String pieceWeightUnit) {
		this.pieceWeightUnit = pieceWeightUnit;
	}

	public String getPieceWeight() {
		return pieceWeight;
	}

	public void setPieceWeight(String pieceWeight) {
		this.pieceWeight = pieceWeight;
	}

	public String getPieceMrp() {
		return pieceMrp;
	}

	public void setPieceMrp(String pieceMrp) {
		this.pieceMrp = pieceMrp;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getIsValuePack() {
		return isValuePack;
	}

	public void setIsValuePack(String isValuePack) {
		this.isValuePack = isValuePack;
	}

	public String getMaterial_group() {
		return material_group;
	}

	public void setMaterial_group(String material_group) {
		this.material_group = material_group;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

}
