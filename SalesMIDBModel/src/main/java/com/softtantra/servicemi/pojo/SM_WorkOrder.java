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
@Table(name = "sm_work_order")
public class SM_WorkOrder implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int work_order_id;
	private int company_id;
	private String job_name;
	private String order_no;
	private double cost;
	private int quantity;
	private int asset_id;
	private String reading_asset;
	private String summary;
	private String scope_of_work;

	private int customer_id;
	private String customer_name;
	private String email;
	private String mobile;

	private String invoice_no;
	private Date invoice_date;
	private Date start_date;
	private Date end_date;
	private int order_status;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private String asset_type;
	private int productId;
	// add by swapnil
	private int serial_number_id;

	// added by swapnil 27-12
	private int service_center_id;

	@Transient
	private String serial_number;

	public int getService_center_id() {
		return service_center_id;
	}

	public void setService_center_id(int service_center_id) {
		this.service_center_id = service_center_id;
	}

	public String getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	public int getSerial_number_id() {
		return serial_number_id;
	}

	public void setSerial_number_id(int serial_number_id) {
		this.serial_number_id = serial_number_id;
	}

	public int getWorkorder_status() {
		return workorder_status;
	}

	public void setWorkorder_status(int workorder_status) {
		this.workorder_status = workorder_status;
	}

	public int getWorkorder_charges() {
		return workorder_charges;
	}

	public void setWorkorder_charges(int workorder_charges) {
		this.workorder_charges = workorder_charges;
	}

	private String field1;
	private String field2;
	private int workorder_status;
	private int workorder_charges;

	@Transient
	private String startEndDate;

	@Transient
	private long diffDays;

	@Transient
	private String end_date1;

	public long getDiffDays() {
		return diffDays;
	}

	public void setDiffDays(long diffDays2) {
		this.diffDays = diffDays2;
	}

	@Transient
	private String invoice_date1;

	@Transient
	private String created_date1;

	@Transient
	private String asset_name;

	@Transient
	private String cost1;

	@Transient
	private String customer_address;

	@Transient
	private String start_date2;

	@Transient
	private String end_date2;

	public String getStart_date2() {
		return start_date2;
	}

	public void setStart_date2(String start_date2) {
		this.start_date2 = start_date2;
	}

	public String getEnd_date2() {
		return end_date2;
	}

	public void setEnd_date2(String end_date2) {
		this.end_date2 = end_date2;
	}

	public String getCost1() {
		return cost1;
	}

	public void setCost1(String cost1) {
		this.cost1 = cost1;
	}

	public String getAsset_name() {
		return asset_name;
	}

	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}

	public String getStartEndDate() {
		return startEndDate;
	}

	public void setStartEndDate(String startEndDate) {
		this.startEndDate = startEndDate;
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

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(int asset_id) {
		this.asset_id = asset_id;
	}

	public String getReading_asset() {
		return reading_asset;
	}

	public void setReading_asset(String reading_asset) {
		this.reading_asset = reading_asset;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getScope_of_work() {
		return scope_of_work;
	}

	public void setScope_of_work(String scope_of_work) {
		this.scope_of_work = scope_of_work;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	public Date getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
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

	public String getEnd_date1() {
		return end_date1;
	}

	public void setEnd_date1(String end_date1) {
		this.end_date1 = end_date1;
	}

	public String getCreated_date1() {
		return created_date1;
	}

	public void setCreated_date1(String created_date1) {
		this.created_date1 = created_date1;
	}

	public String getInvoice_date1() {
		return invoice_date1;
	}

	public void setInvoice_date1(String invoice_date1) {
		this.invoice_date1 = invoice_date1;
	}

	public int getOrder_status() {
		return order_status;
	}

	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public String getAsset_type() {
		return asset_type;
	}

	public void setAsset_type(String asset_type) {
		this.asset_type = asset_type;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
}
