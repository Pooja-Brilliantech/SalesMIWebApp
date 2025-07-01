package com.softtantra.servicemi.pojo;

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
@Table(name = "sm_sc_inventory")
public class SC_Inventory implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inventory_id;
	private int sid_id;
	private int material_id;
	private int company_id;
	private int actual_stock;
	private int minimum_stock;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private int asset_id;

	@Transient
	private String servicecentername;

	@Transient
	private String materialname;

	@Transient
	private String username;

	@Transient
	private String customername;

	@Transient
	private double qty;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public String getServicecentername() {
		return servicecentername;
	}

	public void setServicecentername(String servicecentername) {
		this.servicecentername = servicecentername;
	}

	public String getMaterialname() {
		return materialname;
	}

	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}

	public int getInventory_id() {
		return inventory_id;
	}

	public void setInventory_id(int inventory_id) {
		this.inventory_id = inventory_id;
	}

	public int getSid_id() {
		return sid_id;
	}

	public void setSid_id(int sid_id) {
		this.sid_id = sid_id;
	}

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

	public int getActual_stock() {
		return actual_stock;
	}

	public void setActual_stock(int actual_stock) {
		this.actual_stock = actual_stock;
	}

	public int getMinimum_stock() {
		return minimum_stock;
	}

	public void setMinimum_stock(int minimum_stock) {
		this.minimum_stock = minimum_stock;
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

	public int getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(int asset_id) {
		this.asset_id = asset_id;
	}

}
