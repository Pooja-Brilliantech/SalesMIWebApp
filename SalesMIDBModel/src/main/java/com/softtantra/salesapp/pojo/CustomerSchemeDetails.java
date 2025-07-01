package com.softtantra.salesapp.pojo;

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
@Table(name = "customer_scheme_details")
public class CustomerSchemeDetails implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_scheme_detail_id;
	private int customer_scheme_master_id;
	private int customer_id;
	private int scheme_apply_type;
	private double scheme_apply_value;
	private int scheme_discount_type_id;
	private double discount_value;
	private int company_id;
	private int status;
	private int created_by;
	@CreationTimestamp
	private Date created_date;;
	private int updated_by;
	@UpdateTimestamp
	private Date updated_date;
	@Transient
	private String customer_name;
	@Transient
	private String scheme_discount_type_name;
	@Transient
	private String scheme_name;
	@Transient
	private String start_date;
	@Transient
	private String end_date;
	@Transient
	private String terms_and_conditions;
	@Transient
	private String description;
	@Transient
	private String scheme_on_name;

	public int getCustomer_scheme_detail_id() {
		return customer_scheme_detail_id;
	}

	public void setCustomer_scheme_detail_id(int customer_scheme_detail_id) {
		this.customer_scheme_detail_id = customer_scheme_detail_id;
	}

	public int getCustomer_scheme_master_id() {
		return customer_scheme_master_id;
	}

	public void setCustomer_scheme_master_id(int customer_scheme_master_id) {
		this.customer_scheme_master_id = customer_scheme_master_id;
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

	public int getScheme_apply_type() {
		return scheme_apply_type;
	}

	public void setScheme_apply_type(int scheme_apply_type) {
		this.scheme_apply_type = scheme_apply_type;
	}

	public double getScheme_apply_value() {
		return scheme_apply_value;
	}

	public void setScheme_apply_value(double scheme_apply_value) {
		this.scheme_apply_value = scheme_apply_value;
	}

	public int getScheme_discount_type_id() {
		return scheme_discount_type_id;
	}

	public void setScheme_discount_type_id(int scheme_discount_type_id) {
		this.scheme_discount_type_id = scheme_discount_type_id;
	}

	public double getDiscount_value() {
		return discount_value;
	}

	public void setDiscount_value(double discount_value) {
		this.discount_value = discount_value;
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

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getScheme_discount_type_name() {
		return scheme_discount_type_name;
	}

	public void setScheme_discount_type_name(String scheme_discount_type_name) {
		this.scheme_discount_type_name = scheme_discount_type_name;
	}

	public String getScheme_name() {
		return scheme_name;
	}

	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getTerms_and_conditions() {
		return terms_and_conditions;
	}

	public void setTerms_and_conditions(String terms_and_conditions) {
		this.terms_and_conditions = terms_and_conditions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getScheme_on_name() {
		return scheme_on_name;
	}

	public void setScheme_on_name(String scheme_on_name) {
		this.scheme_on_name = scheme_on_name;
	}

}
