package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "lead_has_products")
public class LeadHasProducts implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lead_product_id;
	@ColumnDefault(value="0")
	private int user_id;
	@ColumnDefault(value="0")
	private int status;
	@ColumnDefault(value="0")
	private int created_by;
	@CreationTimestamp
	private Date created_date;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	@ColumnDefault(value="0")
	private int lead_id;
	@ColumnDefault(value="0")
	private int product_id;
	@ColumnDefault(value="0")
	private int company_id;
	
	@ColumnDefault(value="0")
	private int quantity;
	private String uom;
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	@Transient
	private String product_name;
	
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getLead_product_id() {
		return lead_product_id;
	}
	public void setLead_product_id(int lead_product_id) {
		this.lead_product_id = lead_product_id;
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
	public int getLead_id() {
		return lead_id;
	}
	public void setLead_id(int lead_id) {
		this.lead_id = lead_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}

}
