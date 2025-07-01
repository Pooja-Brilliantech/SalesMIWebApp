package com.softtantra.salesapp.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "customer_details")
public class CustomerDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_id;

	@ColumnDefault("0")
	private int company_id;

	@Column(nullable = true)
	private String customer_name;

	@Column(nullable = true)
	private String code;

	@Column(nullable = true)
	private String email;

	@Column(nullable = true)
	private String mobile;

	@Column(nullable = true)
	private String phone;

	@Column(nullable = true)
	private String fax;

	@ColumnDefault("0")
	private int city;
	
	
	
	@ColumnDefault("0")
	private int state;
	
	@ColumnDefault("0")
	private int country_id;
	
	@ColumnDefault("0")
	private int new_city;
	
	@ColumnDefault("0")
	private int new_state;

	@Column(nullable = true)
	private String id_range;

	@Column(nullable = true)
	private String title;

	@Column(nullable = true)
	private String pan_card;

	@Column(nullable = true)
	private String customer_group;

	@Column(nullable = true)
	private String town;
	
	@ColumnDefault("0")
	private int exportedToSAP = 0;
	
	@ColumnDefault("0")
	private int sendToSAP = 0;

	@Column(nullable = true)
	private String plant;

	@Column(nullable = true)
	private String industry;

	@Column(nullable = true)
	private String companyCode;

	@Column(nullable = true)
	private String transportZone;

	@Column(nullable = true)
	private String legalStatus;

	@Column(nullable = true)
	private String customerAccountGroup;

	@Column(nullable = true)
	private String priceGroup;

	@Column(nullable = true)
	private String salesDistrict;

	@Column(nullable = true)
	private String district;

	@Column(nullable = true)
	private String dunnProcedure;

	@Column(nullable = true)
	private String customer_type;

	@Column(nullable = true)
	private String riskCategory;

	@Column(nullable = true)
	private String customer_sub_type;

	@Column(nullable = true)
	private String mobileId;

	@Column(nullable = true)
	private String image_link;

	@Column(nullable = true)
	private String firm_type;
	
	@Column(nullable = true)
	private String bsegment;
	
	@Column(nullable = true)
	private String locations;
	
	@ColumnDefault("0")
	private int location_no;

	@ColumnDefault("0")
	private int bSegmentId;
	
	@Column(nullable = true)
	private String pincode;

	@ColumnDefault("0")
	private int distributor_id;
	
	@ColumnDefault("0")
	private int route_id;

	@Column(nullable = true)
	private String billing_address;

	@Column(nullable = true)
	private String address;

	@Column(nullable = true)
	private String lat;

	@Column(nullable = true)
	private String longi;

	@ColumnDefault("0")
	private double credit_limit;

	@ColumnDefault("0")
	private int crerdit_duration;

	@ColumnDefault("0")
	private int storeType;

	@ColumnDefault("0")
	private int classType;

	@Column(nullable = true)
	private String gst_no;

	@Column(nullable = true)
	private String area;

	@Column(nullable = true)
	private String sales_group;

	@Column(nullable = true)
	private String sales_office;

	@Column(nullable = true)
	private String division;

	@Column(nullable = true)
	private String distributor_channel;

	@Column(nullable = true)
	private String sales_organization;

	@Column(nullable = true)
	private String terms_of_payment;

	@Column(nullable = true)
	private String accountGroup;

	@ColumnDefault("0")
	private int status;

	@ColumnDefault("0")
	private int created_by;

	@CreationTimestamp
	private Date created_date;

	@ColumnDefault("0")
	private int updated_by;

	@UpdateTimestamp
	private Date updated_date;

	@ColumnDefault("0")
	private int zone_id;
	
	@ColumnDefault("0")
	private int districtId;
	
	
	
	@Transient
	private double LatLongiDiff;

	@Transient
	private double LatLongiDiff1;

	@Transient
	private String customerCode;

	@Transient
	private String cType;

	@Transient
	private String comment;

	@Transient
	private String approveStatus;

	@Transient
	private BigInteger totalAmaunt;

	@Transient
	private BigInteger totalVisits;

	@Transient
	private int contacts;
	@Transient
	private String ConcatedContactInfo;
	@Transient
	private String cityId;
	@Transient
	private String stateId;
	@Transient
	private String distributor_name;
	@Transient
	private String route_name;

	@Transient
	private String country_name;

	@Transient
	private String manage_store_type_name;

	@Transient
	private String manage_class_name;

	@Transient
	private String concate_customer_id;

	@Transient
	private String description;

	@Transient
	private String completion_info;

	@Transient
	private String next_visit_date;

	@Transient
	private String remark;

	@Transient
	private String visit_location;

	@Transient
	private String user_name;

	@Transient
	private BigDecimal amount;

	@Transient
	private Double amount1;

	@Transient
	private String created_name;

	@Transient
	private String updtaed_name;

	@Transient
	private BigInteger totalOrders;

	@Transient
	private BigInteger totalCustomers;

	@Transient
	private BigInteger coveredCustomers;

	@Transient
	private BigInteger sumOfCustomers;

	@Transient
	private BigInteger unCoverdCustomers;
	@Transient
	private String shiptoparty;
	@Transient
	private String soplant;
	
	@Transient
	private String salesOrderType;
	@Transient
	private String orderLog;
	@Transient
	private String underLoadIssue;
	@Transient
	private String pricingIssue;
	@Transient
	private String materialIssue;
	@Transient
	private String creditIssue;
	@Transient
	private String mad;
	@Transient
	private String materialComment;
	@Transient
	private String zoneName;
	@Transient
	private String distributorCode;
	

	public String getDistributorCode() {
		return distributorCode;
	}

	public void setDistributorCode(String distributorCode) {
		this.distributorCode = distributorCode;
	}

	public BigInteger getTotalAmaunt() {
		return totalAmaunt;
	}

	public void setTotalAmaunt(BigInteger totalAmaunt) {
		this.totalAmaunt = totalAmaunt;
	}

	public String getCustomer_sub_type() {
		return customer_sub_type;
	}

	public void setCustomer_sub_type(String customer_sub_type) {
		this.customer_sub_type = customer_sub_type;
	}

	public String getRiskCategory() {
		return riskCategory;
	}

	public void setRiskCategory(String riskCategory) {
		this.riskCategory = riskCategory;
	}

	public double getLatLongiDiff() {
		return LatLongiDiff;
	}

	public void setLatLongiDiff(double latLongiDiff) {
		LatLongiDiff = latLongiDiff;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getcType() {
		return cType;
	}

	public void setcType(String cType) {
		this.cType = cType;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public double getLatLongiDiff1() {
		return LatLongiDiff1;
	}

	public void setLatLongiDiff1(double latLongiDiff1) {
		LatLongiDiff1 = latLongiDiff1;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getTransportZone() {
		return transportZone;
	}

	public void setTransportZone(String transportZone) {
		this.transportZone = transportZone;
	}

	public String getLegalStatus() {
		return legalStatus;
	}

	public void setLegalStatus(String legalStatus) {
		this.legalStatus = legalStatus;
	}

	public String getCustomerAccountGroup() {
		return customerAccountGroup;
	}

	public void setCustomerAccountGroup(String customerAccountGroup) {
		this.customerAccountGroup = customerAccountGroup;
	}

	public String getPriceGroup() {
		return priceGroup;
	}

	public void setPriceGroup(String priceGroup) {
		this.priceGroup = priceGroup;
	}

	public String getSalesDistrict() {
		return salesDistrict;
	}

	public void setSalesDistrict(String salesDistrict) {
		this.salesDistrict = salesDistrict;
	}

	public String getDunnProcedure() {
		return dunnProcedure;
	}

	public void setDunnProcedure(String dunnProcedure) {
		this.dunnProcedure = dunnProcedure;
	}

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}

	public String getFirm_type() {
		return firm_type;
	}

	public void setFirm_type(String firm_type) {
		this.firm_type = firm_type;
	}

	public String getBsegment() {
		return bsegment;
	}

	public void setBsegment(String bsegment) {
		this.bsegment = bsegment;
	}

	public String getLocations() {
		return locations;
	}

	public void setLocations(String locations) {
		this.locations = locations;
	}

	public int getLocation_no() {
		return location_no;
	}

	public void setLocation_no(int location_no) {
		this.location_no = location_no;
	}

	public String getConcate_customer_id() {
		return concate_customer_id;
	}

	public void setConcate_customer_id(String concate_customer_id) {
		this.concate_customer_id = concate_customer_id;
	}

	public String getManage_store_type_name() {
		return manage_store_type_name;
	}

	public void setManage_store_type_name(String manage_store_type_name) {
		this.manage_store_type_name = manage_store_type_name;
	}

	public String getManage_class_name() {
		return manage_class_name;
	}

	public void setManage_class_name(String manage_class_name) {
		this.manage_class_name = manage_class_name;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getGst_no() {
		return gst_no;
	}

	public void setGst_no(String gst_no) {
		this.gst_no = gst_no;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getStoreType() {
		return storeType;
	}

	public void setStoreType(int storeType) {
		this.storeType = storeType;
	}

	public int getClassType() {
		return classType;
	}

	public void setClassType(int classType) {
		this.classType = classType;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public Double getAmount1() {
		return amount1;
	}

	public void setAmount1(Double amount1) {
		this.amount1 = amount1;
	}

	@Transient
	private Integer total;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public int getDistributor_id() {
		return distributor_id;
	}

	public void setDistributor_id(int distributor_id) {
		this.distributor_id = distributor_id;
	}

	public int getRoute_id() {
		return route_id;
	}

	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}

	public String getBilling_address() {
		return billing_address;
	}

	public void setBilling_address(String billing_address) {
		this.billing_address = billing_address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

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

	public String getImage_link() {
		return image_link;
	}

	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}

	public String getDistributor_name() {
		return distributor_name;
	}

	public void setDistributor_name(String distributor_name) {
		this.distributor_name = distributor_name;
	}

	public String getRoute_name() {
		return route_name;
	}

	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}

	public String getConcatedContactInfo() {
		return ConcatedContactInfo;
	}

	public void setConcatedContactInfo(String concatedContactInfo) {
		ConcatedContactInfo = concatedContactInfo;
	}

	public int getContacts() {
		return contacts;
	}

	public void setContacts(int contacts) {
		this.contacts = contacts;
	}

	public int getNew_city() {
		return new_city;
	}

	public void setNew_city(int new_city) {
		this.new_city = new_city;
	}

	public int getNew_state() {
		return new_state;
	}

	public void setNew_state(int new_state) {
		this.new_state = new_state;
	}

	public double getCredit_limit() {
		return credit_limit;
	}

	public void setCredit_limit(double credit_limit) {
		this.credit_limit = credit_limit;
	}

	public int getCrerdit_duration() {
		return crerdit_duration;
	}

	public void setCrerdit_duration(int crerdit_duration) {
		this.crerdit_duration = crerdit_duration;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getCreated_name() {
		return created_name;
	}

	public void setCreated_name(String created_name) {
		this.created_name = created_name;
	}

	public String getUpdtaed_name() {
		return updtaed_name;
	}

	public void setUpdtaed_name(String updtaed_name) {
		this.updtaed_name = updtaed_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompletion_info() {
		return completion_info;
	}

	public void setCompletion_info(String completion_info) {
		this.completion_info = completion_info;
	}

	public String getNext_visit_date() {
		return next_visit_date;
	}

	public void setNext_visit_date(String next_visit_date) {
		this.next_visit_date = next_visit_date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getVisit_location() {
		return visit_location;
	}

	public void setVisit_location(String visit_location) {
		this.visit_location = visit_location;
	}

	public String getCustomer_group() {
		return customer_group;
	}

	public void setCustomer_group(String customer_group) {
		this.customer_group = customer_group;
	}

	public String getSales_group() {
		return sales_group;
	}

	public void setSales_group(String sales_group) {
		this.sales_group = sales_group;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getSales_office() {
		return sales_office;
	}

	public void setSales_office(String sales_office) {
		this.sales_office = sales_office;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getDistributor_channel() {
		return distributor_channel;
	}

	public void setDistributor_channel(String distributor_channel) {
		this.distributor_channel = distributor_channel;
	}

	public String getSales_organization() {
		return sales_organization;
	}

	public void setSales_organization(String sales_organization) {
		this.sales_organization = sales_organization;
	}

	public int getExportedToSAP() {
		return exportedToSAP;
	}

	public void setExportedToSAP(int exportedToSAP) {
		this.exportedToSAP = exportedToSAP;
	}

	public int getSendToSAP() {
		return sendToSAP;
	}

	public void setSendToSAP(int sendToSAP) {
		this.sendToSAP = sendToSAP;
	}

	public String getAccountGroup() {
		return accountGroup;
	}

	public void setAccountGroup(String accountGroup) {
		this.accountGroup = accountGroup;
	}

	public String getTerms_of_payment() {
		return terms_of_payment;
	}

	public void setTerms_of_payment(String terms_of_payment) {
		this.terms_of_payment = terms_of_payment;
	}

	public String getPan_card() {
		return pan_card;
	}

	public void setPan_card(String pan_card) {
		this.pan_card = pan_card;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigInteger getTotalCustomers() {
		return totalCustomers;
	}

	public void setTotalCustomers(BigInteger totalCustomers) {
		this.totalCustomers = totalCustomers;
	}

	public BigInteger getTotalVisits() {
		return totalVisits;
	}

	public void setTotalVisits(BigInteger totalVisits) {
		this.totalVisits = totalVisits;
	}

	public BigInteger getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(BigInteger totalOrders) {
		this.totalOrders = totalOrders;
	}

	public BigInteger getCoveredCustomers() {
		return coveredCustomers;
	}

	public void setCoveredCustomers(BigInteger coveredCustomers) {
		this.coveredCustomers = coveredCustomers;
	}

	public BigInteger getUnCoverdCustomers() {
		return unCoverdCustomers;
	}

	public void setUnCoverdCustomers(BigInteger unCoverdCustomers) {
		this.unCoverdCustomers = unCoverdCustomers;
	}

	public BigInteger getSumOfCustomers() {
		return sumOfCustomers;
	}

	public void setSumOfCustomers(BigInteger sumOfCustomers) {
		this.sumOfCustomers = sumOfCustomers;
	}

	public String getCustomer_type() {
		return customer_type;
	}

	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}

	public String getId_range() {
		return id_range;
	}

	public void setId_range(String id_range) {
		this.id_range = id_range;
	}

	public String getMobileId() {
		return mobileId;
	}

	public void setMobileId(String mobileId) {
		this.mobileId = mobileId;
	}

	public int getZone_id() {
		return zone_id;
	}

	public void setZone_id(int zone_id) {
		this.zone_id = zone_id;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	

	public int getbSegmentId() {
		return bSegmentId;
	}

	public void setbSegmentId(int bSegmentId) {
		this.bSegmentId = bSegmentId;
	}

	public String getShiptoparty() {
		return shiptoparty;
	}

	public void setShiptoparty(String shiptoparty) {
		this.shiptoparty = shiptoparty;
	}

	public String getSoplant() {
		return soplant;
	}

	public void setSoplant(String soplant) {
		this.soplant = soplant;
	}

	public String getSalesOrderType() {
		return salesOrderType;
	}

	public void setSalesOrderType(String salesOrderType) {
		this.salesOrderType = salesOrderType;
	}

	public String getOrderLog() {
		return orderLog;
	}

	public void setOrderLog(String orderLog) {
		this.orderLog = orderLog;
	}

	public String getUnderLoadIssue() {
		return underLoadIssue;
	}

	public void setUnderLoadIssue(String underLoadIssue) {
		this.underLoadIssue = underLoadIssue;
	}

	public String getPricingIssue() {
		return pricingIssue;
	}

	public void setPricingIssue(String pricingIssue) {
		this.pricingIssue = pricingIssue;
	}

	public String getMaterialIssue() {
		return materialIssue;
	}

	public void setMaterialIssue(String materialIssue) {
		this.materialIssue = materialIssue;
	}

	public String getCreditIssue() {
		return creditIssue;
	}

	public void setCreditIssue(String creditIssue) {
		this.creditIssue = creditIssue;
	}

	public String getMad() {
		return mad;
	}

	public void setMad(String mad) {
		this.mad = mad;
	}

	public String getMaterialComment() {
		return materialComment;
	}

	public void setMaterialComment(String materialComment) {
		this.materialComment = materialComment;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	
	
}
