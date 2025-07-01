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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "sales_master")
public class SalesMaster implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sales_master_id;
	private int company_id;
	private int customer_id;
	private int user_id;
	private int status;

	private String remark;
	private int created_by;
	//@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	//@UpdateTimestamp
	private Date updated_date;
	private String address;
	private String signature;

	private String discount_flag;

	private String sales_type;// Sales_person , Retailer_person

	private int TallyStatus=0;
	private String TallyError;

	private String cust_name;
	private String cust_mobileno;

	/// added by savita
	private int approveRejectStatus; // 1-pending 2-1st level approved 3-2nd level approved

	private int total_discount_type; // 1-fixed, 2-percentage

	private double discounted_price;

	private double discount;
	private String cashDiscountOrQuaterlyDiscount;
	// added by omkar
	private double deviation;

	// added for freight rate calculation applicable or not
	private String freight_applicable;

	// added for checking the approval status
	@Transient
	private String approval_status;

	private double total_weight_in_ton;

	private int exportedToSAP = 0;

	private int assigned_truck_id;

	private String lat;
	private String longi;
	private double totalamount;
	
	@Column(nullable = true)
	private String orderImportDate;

	@ColumnDefault(value="0")
	private double freightRate = 0;

	private String mobile_id;
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLongi() {
		return longi;
	}

	public void setLongi(String longi) {
		this.longi = longi;
	}

	@Column(nullable = true)
	private String shipToParty;
	@Column(nullable = true)
	private String soldToParty;
	@Column(nullable = true)
	private String sapOrderId;

	@Column(nullable = true)
	private String poNumber;
	@Column(nullable = true)
	private Date poDate;
	@Column(nullable = true)
	private Date deliveryDate;
	@Column(nullable = true)
	private String orderReason;

	@Column(nullable = true)
	private String plant;
	@Column(nullable = true)
	private String poImage;
	
	private long sapSyncNumber=0;

	@Column(nullable = true)
	private Date sapSentDate;
	@Column(nullable = true)
	private String sapInvoiceNumber;
	@Transient
	private String pricingIssue;
	@Transient
	private String creditIssue;
	@Transient
	private String materialIssue;
	@Transient
	private String underLoad;
	
	
	@Column(nullable = true)
	private String message;
	@Column(nullable = true)
	private String salesOrderNumber;
	
	@Column(nullable = true)
	private String invoiceMessage;
	
	
	public String getSapInvoiceNumber() {
		return sapInvoiceNumber;
	}

	public void setSapInvoiceNumber(String sapInvoiceNumber) {
		this.sapInvoiceNumber = sapInvoiceNumber;
	}

	public String getPoImage() {
		return poImage;
	}

	private int isOrderClubbed=0;
	
	
	
	public void setPoImage(String poImage) {
		this.poImage = poImage;
	}

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Date getPoDate() {
		return poDate;
	}

	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getOrderReason() {
		return orderReason;
	}

	public void setOrderReason(String orderReason) {
		this.orderReason = orderReason;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getTotal_discount_type() {
		return total_discount_type;
	}

	public void setTotal_discount_type(int total_discount_type) {
		this.total_discount_type = total_discount_type;
	}

	public double getDiscounted_price() {
		return discounted_price;
	}

	public void setDiscounted_price(double discounted_price) {
		this.discounted_price = discounted_price;
	}

	public int getApproveRejectStatus() {
		return approveRejectStatus;
	}

	public void setApproveRejectStatus(int approveRejectStatus) {
		this.approveRejectStatus = approveRejectStatus;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_mobileno() {
		return cust_mobileno;
	}

	public void setCust_mobileno(String cust_mobileno) {
		this.cust_mobileno = cust_mobileno;
	}

	public int getTallyStatus() {
		return TallyStatus;
	}

	public void setTallyStatus(int tallyStatus) {
		TallyStatus = tallyStatus;
	}

	public String getTallyError() {
		return TallyError;
	}

	public void setTallyError(String tallyError) {
		TallyError = tallyError;
	}

	public String getSales_type() {
		return sales_type;
	}

	public void setSales_type(String sales_type) {
		this.sales_type = sales_type;
	}

	public String getDiscount_flag() {
		return discount_flag;
	}

	public void setDiscount_flag(String discount_flag) {
		this.discount_flag = discount_flag;
	}

	@Transient
	private String customer_name;
	@Transient
	private String distributor_name;
	@Transient
	private double total_amount;

	@Transient
	private double archive_target;
	@Transient
	private double actual_target;

	@Transient
	private int monthlytargetamount;

	@Transient
	private String despatch_document_number;

	@Transient
	private String despatched_through;

	@Transient
	private String contact_no;

	@Transient
	private String terms_of_delivery;
	@Transient
	private String other_references;
	@Transient
	private String destination;

	public int getMonthlytargetamount() {
		return monthlytargetamount;
	}

	public void setMonthlytargetamount(int monthlytargetamount) {
		this.monthlytargetamount = monthlytargetamount;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getDistributor_name() {
		return distributor_name;
	}

	public void setDistributor_name(String distributor_name) {
		this.distributor_name = distributor_name;
	}

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

	public double getArchive_target() {
		return archive_target;
	}

	public void setArchive_target(double archive_target) {
		this.archive_target = archive_target;
	}

	public double getActual_target() {
		return actual_target;
	}

	public void setActual_target(double actual_target) {
		this.actual_target = actual_target;
	}

	public String getDespatch_document_number() {
		return despatch_document_number;
	}

	public void setDespatch_document_number(String despatch_document_number) {
		this.despatch_document_number = despatch_document_number;
	}

	public String getDespatched_through() {
		return despatched_through;
	}

	public void setDespatched_through(String despatched_through) {
		this.despatched_through = despatched_through;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getTerms_of_delivery() {
		return terms_of_delivery;
	}

	public void setTerms_of_delivery(String terms_of_delivery) {
		this.terms_of_delivery = terms_of_delivery;
	}

	public String getOther_references() {
		return other_references;
	}

	public void setOther_references(String other_references) {
		this.other_references = other_references;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getDeviation() {
		return deviation;
	}

	public void setDeviation(double deviation) {
		this.deviation = deviation;
	}

	public String getFreight_applicable() {
		return freight_applicable;
	}

	public void setFreight_applicable(String freight_applicable) {
		this.freight_applicable = freight_applicable;
	}

	public String getApproval_status() {
		return approval_status;
	}

	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
	}

	public double getTotal_weight_in_ton() {
		return total_weight_in_ton;
	}

	public void setTotal_weight_in_ton(double total_weight_in_ton) {
		this.total_weight_in_ton = total_weight_in_ton;
	}

	public int getAssigned_truck_id() {
		return assigned_truck_id;
	}

	public void setAssigned_truck_id(int assigned_truck_id) {
		this.assigned_truck_id = assigned_truck_id;
	}

	public int getExportedToSAP() {
		return exportedToSAP;
	}

	public void setExportedToSAP(int exportedToSAP) {
		this.exportedToSAP = exportedToSAP;
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

	public double getFreightRate() {
		return freightRate;
	}

	public void setFreightRate(double freightRate) {
		this.freightRate = freightRate;
	}

	public String getCashDiscountOrQuaterlyDiscount() {
		return cashDiscountOrQuaterlyDiscount;
	}

	public void setCashDiscountOrQuaterlyDiscount(String cashDiscountOrQuaterlyDiscount) {
		this.cashDiscountOrQuaterlyDiscount = cashDiscountOrQuaterlyDiscount;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getSapSyncNumber() {
		return sapSyncNumber;
	}

	public void setSapSyncNumber(long sapSyncNumber) {
		this.sapSyncNumber = sapSyncNumber;
	}

	public int getIsOrderClubbed() {
		return isOrderClubbed;
	}

	public void setIsOrderClubbed(int isOrderClubbed) {
		this.isOrderClubbed = isOrderClubbed;
	}

	public String getMobile_id() {
		return mobile_id;
	}

	public void setMobile_id(String mobile_id) {
		this.mobile_id = mobile_id;
	}

	public Date getSapSentDate() {
		return sapSentDate;
	}

	public void setSapSentDate(Date sapSentDate) {
		this.sapSentDate = sapSentDate;
	}

	public String getPricingIssue() {
		return pricingIssue;
	}

	public void setPricingIssue(String pricingIssue) {
		this.pricingIssue = pricingIssue;
	}

	public String getCreditIssue() {
		return creditIssue;
	}

	public void setCreditIssue(String creditIssue) {
		this.creditIssue = creditIssue;
	}

	public String getMaterialIssue() {
		return materialIssue;
	}

	public void setMaterialIssue(String materialIssue) {
		this.materialIssue = materialIssue;
	}

	public String getUnderLoad() {
		return underLoad;
	}

	public void setUnderLoad(String underLoad) {
		this.underLoad = underLoad;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getInvoiceMessage() {
		return invoiceMessage;
	}

	public void setInvoiceMessage(String invoiceMessage) {
		this.invoiceMessage = invoiceMessage;
	}

	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public String getOrderImportDate() {
		return orderImportDate;
	}

	public void setOrderImportDate(String orderImportDate) {
		this.orderImportDate = orderImportDate;
	}

}
