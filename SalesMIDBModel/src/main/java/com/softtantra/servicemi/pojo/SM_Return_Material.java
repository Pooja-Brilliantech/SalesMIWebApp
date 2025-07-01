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

//secondary stock information i.e userwise  stock details for the material

@Entity
@Table(name = "sm_return_material")
public class SM_Return_Material implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int return_material_id;
	private int request_material_id;
	private int material_id;
	private int company_id;
	private int return_qty;
	private int reason_type;/// 0=good items, 1=bad items
	private int user_id;
	private int customer_id;
	private String customer_name;
	private int status; // 0-Pending , 1-Aprroved , 2-Rejected
	private Date return_date;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private String comment;
	private int sid_id;

	public int getSid_id() {
		return sid_id;
	}

	public void setSid_id(int sid_id) {
		this.sid_id = sid_id;
	}

	public int getRequest_material_id() {
		return request_material_id;
	}

	public void setRequest_material_id(int request_material_id) {
		this.request_material_id = request_material_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getReturn_material_id() {
		return return_material_id;
	}

	public void setReturn_material_id(int return_material_id) {
		this.return_material_id = return_material_id;
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

	public int getReturn_qty() {
		return return_qty;
	}

	public void setReturn_qty(int return_qty) {
		this.return_qty = return_qty;
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

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
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

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public int getReason_type() {
		return reason_type;
	}

	public void setReason_type(int reason_type) {
		this.reason_type = reason_type;
	}

	@Transient
	private String serviceCenter_name;

	public String getServiceCenter_name() {
		return serviceCenter_name;
	}

	public void setServiceCenter_name(String serviceCenter_name) {
		this.serviceCenter_name = serviceCenter_name;
	}

	@Transient
	private String material_name;

	public String getMaterial_name() {
		return material_name;
	}

	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}

}
