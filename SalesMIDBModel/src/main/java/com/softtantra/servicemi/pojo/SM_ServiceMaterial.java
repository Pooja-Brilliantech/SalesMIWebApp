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
@Table(name = "sm_service_material")
public class SM_ServiceMaterial implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int service_material_id;
	private int company_id;
	private int service_id;
	private int customer_id;
	private int material_id;
	private int quantity;
	private double rate;
	private int created_by;
	private int updated_by;
	@CreationTimestamp
	private Date created_date;;
	@UpdateTimestamp
	private Date updated_date;
	private int kcmupload_status;

	@Transient
	private String material_name;
	@Transient
	private String created_date_string;

	@Transient
	private String tax_per;

	@Transient
	private String rate1;

	public String getRate1() {
		return rate1;
	}

	public void setRate1(String rate1) {
		this.rate1 = rate1;
	}

	public String getTax_per() {
		return tax_per;
	}

	public void setTax_per(String tax_per) {
		this.tax_per = tax_per;
	}

	public String getCreated_date_string() {
		return created_date_string;
	}

	public void setCreated_date_string(String created_date_string) {
		this.created_date_string = created_date_string;
	}

	public String getMaterial_name() {
		return material_name;
	}

	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}

	public int getService_material_id() {
		return service_material_id;
	}

	public void setService_material_id(int service_material_id) {
		this.service_material_id = service_material_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(int material_id) {
		this.material_id = material_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public int getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(int updated_by) {
		this.updated_by = updated_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public int getKcmupload_status() {
		return kcmupload_status;
	}

	public void setKcmupload_status(int kcmupload_status) {
		this.kcmupload_status = kcmupload_status;
	}

}
