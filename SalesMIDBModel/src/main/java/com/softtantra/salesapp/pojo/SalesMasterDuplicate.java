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
@Table(name = "sales_master_duplicate")
public class SalesMasterDuplicate implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sales_master_duplicate_id;

	private String discount_flag;

	private int approveRejectStatus;

	private int sales_master_id;
	private int company_id;
	private int customer_id;
	private int user_id;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private String address;

	private String signature;
	private double total_weight_in_ton;

	@Transient
	private String approval_status;
	@Column(nullable = true)
	private String shipToParty;
	@Column(nullable = true)
	private String soldToParty;
	@Column(nullable = true)
	private String sapOrderId;

	private String freight_applicable;

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getSales_master_id() {
		return sales_master_id;
	}

	public void setSales_master_id(int sales_master_id) {
		this.sales_master_id = sales_master_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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

	public double getTotal_weight_in_ton() {
		return total_weight_in_ton;
	}

	public void setTotal_weight_in_ton(double total_weight_in_ton) {
		this.total_weight_in_ton = total_weight_in_ton;
	}

	public String getApproval_status() {
		return approval_status;
	}

	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
	}

	public int getSales_master_duplicate_id() {
		return sales_master_duplicate_id;
	}

	public void setSales_master_duplicate_id(int sales_master_duplicate_id) {
		this.sales_master_duplicate_id = sales_master_duplicate_id;
	}

	public String getDiscount_flag() {
		return discount_flag;
	}

	public void setDiscount_flag(String discount_flag) {
		this.discount_flag = discount_flag;
	}

	public int getApproveRejectStatus() {
		return approveRejectStatus;
	}

	public void setApproveRejectStatus(int approveRejectStatus) {
		this.approveRejectStatus = approveRejectStatus;
	}

	public String getShipToParty() {
		return shipToParty;
	}

	public void setShipToParty(String shipToParty) {
		this.shipToParty = shipToParty;
	}

	public String getSoldToParty() {
		return soldToParty;
	}

	public void setSoldToParty(String soldToParty) {
		this.soldToParty = soldToParty;
	}

	public String getSapOrderId() {
		return sapOrderId;
	}

	public void setSapOrderId(String sapOrderId) {
		this.sapOrderId = sapOrderId;
	}

	public String getFreight_applicable() {
		return freight_applicable;
	}

	public void setFreight_applicable(String freight_applicable) {
		this.freight_applicable = freight_applicable;
	}

}