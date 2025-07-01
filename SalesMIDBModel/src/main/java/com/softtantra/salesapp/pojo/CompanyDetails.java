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
@Table(name = "company_details")
public class CompanyDetails implements Serializable {

	private static final long serialVersionUID = -1635141761965908928L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int company_id;
	private String company_name;
	private String company_email_id;
	private String mobile;
	private String company_phone;
	private String address;
	private String first_name;
	private String last_name;
	private String phone;
	private int package_id;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	private String timezone;
	private String billing_address;
	private int no_of_user_allow;
	private String img_url;
	private String field1;
	private String field2;
	private String gst_no;
	private String terms_conditions;
	private Date expiry_date;
	private String user_email;
	private String start_month;
	private String end_month;
	private int country_id = 0;
	private int state_id = 0;
	private int city_id = 0;

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public int getState_id() {
		return state_id;
	}

	public void setState_id(int state_id) {
		this.state_id = state_id;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public String getStart_month() {
		return start_month;
	}

	public void setStart_month(String start_month) {
		this.start_month = start_month;
	}

	public String getEnd_month() {
		return end_month;
	}

	public void setEnd_month(String end_month) {
		this.end_month = end_month;
	}

	public String getGst_no() {
		return gst_no;
	}

	public void setGst_no(String gst_no) {
		this.gst_no = gst_no;
	}

	public String getTerms_conditions() {
		return terms_conditions;
	}

	public void setTerms_conditions(String terms_conditions) {
		this.terms_conditions = terms_conditions;
	}

	private int servicetype;
	private int iscarryforwardleave;
	private int isPremiumSupport = 0;

	public int getIsPremiumSupport() {
		return isPremiumSupport;
	}

	public void setIsPremiumSupport(int isPremiumSupport) {
		this.isPremiumSupport = isPremiumSupport;
	}

	@Column(name = "monthlytargetamount", columnDefinition = "int default '0'")
	private int monthlytargetamount;

	public int getIscarryforwardleave() {
		return iscarryforwardleave;
	}

	public void setIscarryforwardleave(int iscarryforwardleave) {
		this.iscarryforwardleave = iscarryforwardleave;
	}

	@Column(name = "yearlytargetamount", columnDefinition = "int default '0'")
	private int yearlytargetamount;

	@Transient
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getServicetype() {
		return servicetype;
	}

	public void setServicetype(int servicetype) {
		this.servicetype = servicetype;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_email_id() {
		return company_email_id;
	}

	public void setCompany_email_id(String company_email_id) {
		this.company_email_id = company_email_id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCompany_phone() {
		return company_phone;
	}

	public void setCompany_phone(String company_phone) {
		this.company_phone = company_phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPackage_id() {
		return package_id;
	}

	public void setPackage_id(int package_id) {
		this.package_id = package_id;
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

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getBilling_address() {
		return billing_address;
	}

	public void setBilling_address(String billing_address) {
		this.billing_address = billing_address;
	}

	public int getNo_of_user_allow() {
		return no_of_user_allow;
	}

	public void setNo_of_user_allow(int no_of_user_allow) {
		this.no_of_user_allow = no_of_user_allow;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public int getMonthlytargetamount() {
		return monthlytargetamount;
	}

	public void setMonthlytargetamount(int monthlytargetamount) {
		this.monthlytargetamount = monthlytargetamount;
	}

	public int getYearlytargetamount() {
		return yearlytargetamount;
	}

	public void setYearlytargetamount(int yearlytargetamount) {
		this.yearlytargetamount = yearlytargetamount;
	}

	private int location;
	private int location_retry;

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getLocation_retry() {
		return location_retry;
	}

	public void setLocation_retry(int location_retry) {
		this.location_retry = location_retry;
	}

	public Date getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	@Override
	public String toString() {
		return "CompanyDetails [company_id=" + company_id + ", company_name=" + company_name + ", company_email_id="
				+ company_email_id + ", mobile=" + mobile + ", company_phone=" + company_phone + ", address=" + address
				+ ", first_name=" + first_name + ", last_name=" + last_name + ", phone=" + phone + ", package_id="
				+ package_id + ", status=" + status + ", created_by=" + created_by + ", created_date=" + created_date
				+ ", updated_by=" + updated_by + ", updated_date=" + updated_date + ", timezone=" + timezone
				+ ", billing_address=" + billing_address + ", no_of_user_allow=" + no_of_user_allow + ", img_url="
				+ img_url + ", field1=" + field1 + ", field2=" + field2 + ", gst_no=" + gst_no + ", terms_conditions="
				+ terms_conditions + ", servicetype=" + servicetype + ", iscarryforwardleave=" + iscarryforwardleave
				+ ", monthlytargetamount=" + monthlytargetamount + ", yearlytargetamount=" + yearlytargetamount
				+ ", password=" + password + ", location=" + location + ", location_retry=" + location_retry + "]";
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

}
