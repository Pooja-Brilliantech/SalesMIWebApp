package com.softtantra.servicemi.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "sm_material")
public class SM_Material implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int material_id;
	private int company_id;
	private String material_name;
	private String material_code;
	private double cost;
	private double billing_rate;
	private int tax_id;
	private String description;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private int asset_id;

	// new field for KCM
	private String base_unit_of_measure;
	private String gst_group_code;
	private String hsn_hac_code;
	private String blocked;

	public String getBase_unit_of_measure() {
		return base_unit_of_measure;
	}

	public void setBase_unit_of_measure(String base_unit_of_measure) {
		this.base_unit_of_measure = base_unit_of_measure;
	}

	public String getGst_group_code() {
		return gst_group_code;
	}

	public void setGst_group_code(String gst_group_code) {
		this.gst_group_code = gst_group_code;
	}

	public String getHsn_hac_code() {
		return hsn_hac_code;
	}

	public void setHsn_hac_code(String hsn_hac_code) {
		this.hsn_hac_code = hsn_hac_code;
	}

	public String getBlocked() {
		return blocked;
	}

	public void setBlocked(String blocked) {
		this.blocked = blocked;
	}

	public int getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(int asset_id) {
		this.asset_id = asset_id;
	}

	private String field1;
	private String field2;

	public int getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(int material_id) {
		this.material_id = material_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getMaterial_name() {
		return material_name;
	}

	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}

	public String getMaterial_code() {
		return material_code;
	}

	public void setMaterial_code(String material_code) {
		this.material_code = material_code;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getBilling_rate() {
		return billing_rate;
	}

	public void setBilling_rate(double billing_rate) {
		this.billing_rate = billing_rate;
	}

	public int getTax_id() {
		return tax_id;
	}

	public void setTax_id(int tax_id) {
		this.tax_id = tax_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

}
