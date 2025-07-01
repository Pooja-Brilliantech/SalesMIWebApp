package com.softtantra.salesapp.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pricemaster_import_records")
public class PriceMasterImportRecords implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int records_id;
	private String product_code;
	private String pack;
	private String mrp;
	private String comment;
	private int import_id;
	private int status;
	private int company_id;
	private int user_id;
	private String packunit;
	@Column(nullable = true)
	private String state_name;
	@Column(nullable = true)
	private String city_name;

	@Column(nullable = true)
	private String productName;
	
	@Column(nullable = true)
	private String rate;
	@Column(nullable = true)
	private String tax;
	@Column(nullable = true)
	private String countryName;
	@Column(nullable = true)
	private String piecePack;
	
	@Column(nullable = true)
	private String pieceQty;
	@Column(nullable=true)
	private String pieceWeightUnit;
	@Column(nullable=true)
	private String pieceWeight;
	@Column(nullable=true)
	private String pieceMrp;
	@Column(nullable = true)
	private String pieceUnit;
	
	private int roleId;
	private String roleName;
	public String getPieceUnit() {
		return pieceUnit;
	}

	public void setPieceUnit(String pieceUnit) {
		this.pieceUnit = pieceUnit;
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

	public int getRecords_id() {
		return records_id;
	}

	public void setRecords_id(int records_id) {
		this.records_id = records_id;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
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

	public String getPackunit() {
		return packunit;
	}

	public void setPackunit(String packunit) {
		this.packunit = packunit;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getPiecePack() {
		return piecePack;
	}

	public void setPiecePack(String piecePack) {
		this.piecePack = piecePack;
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
