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
@Table(name = "secondary_stock_grn")
public class SecondaryStockGRN implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@CreationTimestamp
	private Date created_date;
	private int created_by;
	@UpdateTimestamp
	private Date updated_date;
	@Transient
	private byte image[];
	@Transient
	private String d_location;
	
	public String getD_location() {
		return d_location;
	}
	public void setD_location(String d_location) {
		this.d_location = d_location;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public int getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getGrn_number() {
		return grn_number;
	}
	public void setGrn_number(String grn_number) {
		this.grn_number = grn_number;
	}
	public String getInvoice_number() {
		return invoice_number;
	}
	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}
	public Date getInvoice_date() {
		return invoice_date;
	}
	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}
	public Date getGrn_date() {
		return grn_date;
	}
	public void setGrn_date(Date grn_date) {
		this.grn_date = grn_date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public double getQuantity_received() {
		return quantity_received;
	}
	public void setQuantity_received(double quantity_received) {
		this.quantity_received = quantity_received;
	}
	public Date getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}
	public String getReference_number() {
		return reference_number;
	}
	public void setReference_number(String reference_number) {
		this.reference_number = reference_number;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getDistributor_id() {
		return distributor_id;
	}
	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}
	private int updated_by;
	private int company_id;
	@Column(nullable=true)
	private String product_code;
	@Column(nullable=true)
	private String product_name;
	
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
	
	@Column(nullable=true)
	private String grn_number;
	@Column(nullable=true)
	private String invoice_number;
	private Date invoice_date;
	private Date grn_date;
	@Column(nullable=true)
	private String remark;

	private int product_id=0;
	private double quantity_received=0.0d;
	private Date expiry_date;
	@Column(nullable=true)
	private String reference_number;
	private int status;
	private int distributor_location_id=0;
	private int distributor_id=0;
	@Column(nullable=true)
	private int pricemasterid=0;
	public int getPricemasterid() {
		return pricemasterid;
	}
	public void setPricemasterid(int pricemasterid) {
		this.pricemasterid = pricemasterid;
	}
	public int getDistributor_location_id() {
		return distributor_location_id;
	}
	public void setDistributor_location_id(int distributor_location_id) {
		this.distributor_location_id = distributor_location_id;
	}
	
}
