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

@Entity
@Table(name = "sm_resolve_service")
public class ResolveService implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resolve_id;
	private int service_id;
	private Long company_id;
	private Date resolve_date;
	private String lat, longi;
	private double rating;
	private String location;
	private String cust_signature_image_path;
	private String resolved_comment;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;

	// added by omkar
	private String os_remark;
	private String actual_reason;
	private String action_taken;

	private String rca;
	private String site_load_current;
	private String battery_capacity;
	private String rm_installed_qty;
	private String partys_faculty;
	private String cancellation_reason;
	private String service_type_remarks;

	private String symptom_name;
	@Transient
	private String customer_name;
	@Transient
	private String resolve_date_time;
	@Transient
	private String address;

	public String getSymptom_name() {
		return symptom_name;
	}

	public void setSymptom_name(String symptom_name) {
		this.symptom_name = symptom_name;
	}

	public String getResolve_date_time() {
		return resolve_date_time;
	}

	public void setResolve_date_time(String resolve_date_time) {
		this.resolve_date_time = resolve_date_time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public int getResolve_id() {
		return resolve_id;
	}

	public void setResolve_id(int resolve_id) {
		this.resolve_id = resolve_id;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public Date getResolve_date() {
		return resolve_date;
	}

	public void setResolve_date(Date resolve_date) {
		this.resolve_date = resolve_date;
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCust_signature_image_path() {
		return cust_signature_image_path;
	}

	public void setCust_signature_image_path(String cust_signature_image_path) {
		this.cust_signature_image_path = cust_signature_image_path;
	}

	public String getResolved_comment() {
		return resolved_comment;
	}

	public void setResolved_comment(String resolved_comment) {
		this.resolved_comment = resolved_comment;
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

	public String getOs_remark() {
		return os_remark;
	}

	public void setOs_remark(String os_remark) {
		this.os_remark = os_remark;
	}

	public String getActual_reason() {
		return actual_reason;
	}

	public void setActual_reason(String actual_reason) {
		this.actual_reason = actual_reason;
	}

	public String getAction_taken() {
		return action_taken;
	}

	public void setAction_taken(String action_taken) {
		this.action_taken = action_taken;
	}

	public String getRca() {
		return rca;
	}

	public void setRca(String rca) {
		this.rca = rca;
	}

	public String getSite_load_current() {
		return site_load_current;
	}

	public void setSite_load_current(String site_load_current) {
		this.site_load_current = site_load_current;
	}

	public String getBattery_capacity() {
		return battery_capacity;
	}

	public void setBattery_capacity(String battery_capacity) {
		this.battery_capacity = battery_capacity;
	}

	public String getRm_installed_qty() {
		return rm_installed_qty;
	}

	public void setRm_installed_qty(String rm_installed_qty) {
		this.rm_installed_qty = rm_installed_qty;
	}

	public String getPartys_faculty() {
		return partys_faculty;
	}

	public void setPartys_faculty(String partys_faculty) {
		this.partys_faculty = partys_faculty;
	}

	public String getCancellation_reason() {
		return cancellation_reason;
	}

	public void setCancellation_reason(String cancellation_reason) {
		this.cancellation_reason = cancellation_reason;
	}

	public String getService_type_remarks() {
		return service_type_remarks;
	}

	public void setService_type_remarks(String service_type_remarks) {
		this.service_type_remarks = service_type_remarks;
	}

	private int sm_symptom_code_id;
	private int fault_area_code_id;
	private int fault_code_id;
	private int sm_resolution_id;

	public int getSm_symptom_code_id() {
		return sm_symptom_code_id;
	}

	public void setSm_symptom_code_id(int sm_symptom_code_id) {
		this.sm_symptom_code_id = sm_symptom_code_id;
	}

	public int getFault_area_code_id() {
		return fault_area_code_id;
	}

	public void setFault_area_code_id(int fault_area_code_id) {
		this.fault_area_code_id = fault_area_code_id;
	}

	public int getFault_code_id() {
		return fault_code_id;
	}

	public void setFault_code_id(int fault_code_id) {
		this.fault_code_id = fault_code_id;
	}

	public int getSm_resolution_id() {
		return sm_resolution_id;
	}

	public void setSm_resolution_id(int sm_resolution_id) {
		this.sm_resolution_id = sm_resolution_id;
	}
}
