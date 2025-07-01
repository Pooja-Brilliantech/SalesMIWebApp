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
@Table(name = "sm_customer_profile")
public class SM_CustomerProfile implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_profile_id;
	private String company_name;
	private String firm_type;
	private String bsegment;
	private String locations;
	private int location_no;

	private int software_tally;

	private String license;
	private String serial_no;
	private String tally_version;
	private int no_of_users;

	private String tally_modules; // Accounting,Inventory,Banking,GST,Payroll,Jobwork
	private String gst_trainning; // 3 B,GSTR 1,GSTR 2,GSTR 3
	private String customised_modules;// Customised Invoice,Auto Back up,Address Book,Payment Follow up,S M S
										// Management,Envelop Printing

	private int software_other;
	private String other_software_names;
	private int online_banking;
	private int dbs_banking;
	private int company_id;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;

	private String other_soft_1;
	private String other_soft_2;
	private String other_soft_3;

	private int status;

	public int getDbs_banking() {
		return dbs_banking;
	}

	public void setDbs_banking(int dbs_banking) {
		this.dbs_banking = dbs_banking;
	}

	public String getOther_soft_1() {
		return other_soft_1;
	}

	public void setOther_soft_1(String other_soft_1) {
		this.other_soft_1 = other_soft_1;
	}

	public String getOther_soft_2() {
		return other_soft_2;
	}

	public void setOther_soft_2(String other_soft_2) {
		this.other_soft_2 = other_soft_2;
	}

	public String getOther_soft_3() {
		return other_soft_3;
	}

	public void setOther_soft_3(String other_soft_3) {
		this.other_soft_3 = other_soft_3;
	}

	public int getCustomer_profile_id() {
		return customer_profile_id;
	}

	public void setCustomer_profile_id(int customer_profile_id) {
		this.customer_profile_id = customer_profile_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
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

	public int getSoftware_tally() {
		return software_tally;
	}

	public void setSoftware_tally(int software_tally) {
		this.software_tally = software_tally;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getSerial_no() {
		return serial_no;
	}

	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
	}

	public String getTally_version() {
		return tally_version;
	}

	public void setTally_version(String tally_version) {
		this.tally_version = tally_version;
	}

	public int getNo_of_users() {
		return no_of_users;
	}

	public void setNo_of_users(int no_of_users) {
		this.no_of_users = no_of_users;
	}

	public String getTally_modules() {
		return tally_modules;
	}

	public void setTally_modules(String tally_modules) {
		this.tally_modules = tally_modules;
	}

	public String getGst_trainning() {
		return gst_trainning;
	}

	public void setGst_trainning(String gst_trainning) {
		this.gst_trainning = gst_trainning;
	}

	public String getCustomised_modules() {
		return customised_modules;
	}

	public void setCustomised_modules(String customised_modules) {
		this.customised_modules = customised_modules;
	}

	public int getSoftware_other() {
		return software_other;
	}

	public void setSoftware_other(int software_other) {
		this.software_other = software_other;
	}

	public String getOther_software_names() {
		return other_software_names;
	}

	public void setOther_software_names(String other_software_names) {
		this.other_software_names = other_software_names;
	}

	public int getOnline_banking() {
		return online_banking;
	}

	public void setOnline_banking(int online_banking) {
		this.online_banking = online_banking;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Transient
	private String user_name;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}
