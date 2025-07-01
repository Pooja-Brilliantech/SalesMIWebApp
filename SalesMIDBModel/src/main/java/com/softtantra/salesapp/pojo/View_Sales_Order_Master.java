package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

public class View_Sales_Order_Master implements Serializable{

	// sales_master
	private int sales_master_id;
	private int company_id;
	private String address1;
	private int user_id;
	private int status;
	@CreationTimestamp
	private Date created_date;;
	private int created_by;
	private String signature;
	@Transient
	private String created_date1;

	@Transient
	private String remark;
	private String cdQdApplicable;
	// customer_details
	private int customer_id;
	private String address;
	private String phone;
	private String email;
	private String sapInvoiceNumber;
	// user_details
	private String first_name;
	private String last_name;

	private String poNumber;

	private Date poDate;

	private Date deliveryDate;

	private String orderReason;

	private String plant;

	private String poImage;

	private String salesOrderType;
	private String sapSentDate;
	private String salesOrderNumber;
	@Transient
	private String class_type;

	@Transient
	private String store_type;

	@Transient
	private String gst_no;

	@Transient
	private String mobile;

	private String sapOrderId;

	private String shipToParty;
		
	private String code;
	
	private String loadDetails;
		
	private String shipToPartyCustomerName;
	
	private String invoiceNumber;
		
	private int isOrderClubbed=0;	
	
	@Transient
	private String deviation;
	private String totalWeightInTon;
	
	
	public String getDeviation() {
		return deviation;
	}

	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}

	public String getShipToParty() {
		return shipToParty;
	}
	
	public void setShipToParty(String shipToParty) {
		this.shipToParty = shipToParty;
	}
	public String getPoImage() {
		return poImage;
	}

	public void setPoImage(String poImage) {
		this.poImage = poImage;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getOrderReason() {
		return orderReason;
	}

	public void setOrderReason(String orderReason) {
		this.orderReason = orderReason;
	}

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getClass_type() {
		return class_type;
	}

	public void setClass_type(String class_type) {
		this.class_type = class_type;
	}

	public String getStore_type() {
		return store_type;
	}

	public void setStore_type(String store_type) {
		this.store_type = store_type;
	}

	public String getGst_no() {
		return gst_no;
	}

	public void setGst_no(String gst_no) {
		this.gst_no = gst_no;
	}

	public String getCreated_date1() {
		return created_date1;
	}

	public void setCreated_date1(String created_date1) {
		this.created_date1 = created_date1;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
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

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSapOrderId() {
		return sapOrderId;
	}

	public void setSapOrderId(String sapOrderId) {
		this.sapOrderId = sapOrderId;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getPoDate() {
		return poDate;
	}

	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}

	public String getCdQdApplicable() {
		return cdQdApplicable;
	}

	public void setCdQdApplicable(String cdQdApplicable) {
		this.cdQdApplicable = cdQdApplicable;
	}

	public String getSalesOrderType() {
		return salesOrderType;
	}

	public void setSalesOrderType(String salesOrderType) {
		this.salesOrderType = salesOrderType;
	}

	public String getShipToPartyCustomerName() {
		return shipToPartyCustomerName;
	}

	public void setShipToPartyCustomerName(String shipToPartyCustomerName) {
		this.shipToPartyCustomerName = shipToPartyCustomerName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getIsOrderClubbed() {
		return isOrderClubbed;
	}

	public void setIsOrderClubbed(int isOrderClubbed) {
		this.isOrderClubbed = isOrderClubbed;
	}

	public String getSapInvoiceNumber() {
		return sapInvoiceNumber;
	}

	public void setSapInvoiceNumber(String sapInvoiceNumber) {
		this.sapInvoiceNumber = sapInvoiceNumber;
	}

	public String getLoadDetails() {
		return loadDetails;
	}

	public void setLoadDetails(String loadDetails) {
		this.loadDetails = loadDetails;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getSapSentDate() {
		return sapSentDate;
	}

	public void setSapSentDate(String sapSentDate) {
		this.sapSentDate = sapSentDate;
	}

	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public String getTotalWeightInTon() {
		return totalWeightInTon;
	}

	public void setTotalWeightInTon(String totalWeightInTon) {
		this.totalWeightInTon = totalWeightInTon;
	}

}
