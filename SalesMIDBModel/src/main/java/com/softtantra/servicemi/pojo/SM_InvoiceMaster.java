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
@Table(name = "sm_invoice_master")
public class SM_InvoiceMaster implements Serializable{

	@Id
	// @TableGenerator(initialValue = 1000000001,name="")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long invoice_id;
	private int work_order_id;
	private int company_id;
	private int customer_id;
	private double service_total;
	private double material_total;
	private double total_amount;
	private double paid_amount;
	private int payment_status;
	/*
	 * 1-Unpaid 2-Partial 3-paid
	 */
	private int created_by;
	private int updated_by;
	@CreationTimestamp
	private Date created_date;;
	@UpdateTimestamp
	private Date updated_date;

	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	private int transaction_mode;

	@Transient
	private String order_no;
	@Transient
	private int work_order_status;

	@Transient
	private int order_status;

	public int getOrder_status() {
		return order_status;
	}

	public int getWork_order_status() {
		return work_order_status;
	}

	public void setWork_order_status(int work_order_status) {
		this.work_order_status = work_order_status;
	}

	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}

	@Transient
	private String export_created_date;

	public String getExport_created_date() {
		return export_created_date;
	}

	public void setExport_created_date(String export_created_date) {
		this.export_created_date = export_created_date;
	}

	@Transient
	private String invoice_no;

	public String getAsset_name() {
		return asset_name;
	}

	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}

	@Transient
	private String asset_name;

	@Transient
	private String customer_name;

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public long getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(long invoice_id) {
		this.invoice_id = invoice_id;
	}

	public int getTransaction_mode() {
		return transaction_mode;
	}

	public void setTransaction_mode(int transaction_mode) {
		this.transaction_mode = transaction_mode;
	}

	public double getPaid_amount() {
		return paid_amount;
	}

	public void setPaid_amount(double paid_amount) {
		this.paid_amount = paid_amount;
	}

	public int getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(int payment_status) {
		this.payment_status = payment_status;
	}

	public int getWork_order_id() {
		return work_order_id;
	}

	public void setWork_order_id(int work_order_id) {
		this.work_order_id = work_order_id;
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

	public double getService_total() {
		return service_total;
	}

	public void setService_total(double service_total) {
		this.service_total = service_total;
	}

	public double getMaterial_total() {
		return material_total;
	}

	public void setMaterial_total(double material_total) {
		this.material_total = material_total;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
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

}
